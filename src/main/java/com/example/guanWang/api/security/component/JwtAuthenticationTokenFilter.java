package com.example.guanWang.api.security.component;

import cn.hutool.core.util.StrUtil;
import com.example.guanWang.api.security.commen.JwtTokenUtil;
import com.example.guanWang.dto.OnlineUserDto;
import com.example.guanWang.service.OnlineUserService;
import com.example.guanWang.service.UmsAdminCacheService;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JWT登录授权过滤器
 * Created by macro on 2018/4/26.
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UmsAdminCacheService umsAdminCacheService;

    @Autowired
    private OnlineUserService onlineUserService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.online-key}")
    private String onlineKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
    String token = resolveToken(request);
    // 对于 Token 为空的不需要去查 Redis
    if (StrUtil.isNotBlank(token)) {
        OnlineUserDto onlineUserDto = null;
        boolean cleanUserCache = false;
        try {
            onlineUserDto = onlineUserService.getOne(this.onlineKey + token);
        } catch (ExpiredJwtException e) {
            LOGGER.error(e.getMessage());
            cleanUserCache = true;
        } finally {
            if (cleanUserCache || Objects.isNull(onlineUserDto)) {
                umsAdminCacheService.delAdmin(String.valueOf(jwtTokenUtil.getClaimsFromToken(token).get(JwtTokenUtil.CLAIM_KEY_USERNAME)));
            }
        }
        if (onlineUserDto != null && StringUtils.hasText(token)) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(onlineUserDto.getUserName());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Token 续期
            jwtTokenUtil.refreshHeadToken(token);
        }
    }
        chain.doFilter(request, response);
    }

    /**
     * 初步检测Token
     *
     * @param request /
     * @return /
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(this.tokenHeader);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(this.tokenHead)){
            // 去掉令牌前缀
            return bearerToken.replace(this.tokenHead, "");
        }else{
            LOGGER.info("非法Token：{}", bearerToken);
        }
        return null;
    }
}
