/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.example.guanWang.service;

import com.example.guanWang.api.common.EncryptUtils;
import com.example.guanWang.api.common.PageUtil;
import com.example.guanWang.api.common.StringUtils;
import com.example.guanWang.api.security.bo.AdminUserDetails;
import com.example.guanWang.dto.OnlineUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Zheng Jie
 * @date 2019年10月26日21:56:27
 */
@Service
@Slf4j
public class OnlineUserService {

    @Autowired
    private  RedisService redisService;

    @Value("${jwt.online-key}")
    private String onlineKey;
    @Value("${jwt.expiration}")
    private Long TokenValidityInSeconds;
    public OnlineUserService(RedisService redisService) {
        this.redisService = redisService;
    }

    /**
     * 保存在线用户信息
     * @param adminUserDetails /
     * @param token /
     */
    public void save(AdminUserDetails adminUserDetails, String token){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String dept = adminUserDetails.getDep().getDepName();
        String ip = StringUtils.getIp(request);
        String browser = StringUtils.getBrowser(request);
        String address = StringUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(adminUserDetails.getUsername(), adminUserDetails.getUmsAdmin().getNickName(), dept, browser , ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        redisService.set(onlineKey + token, onlineUserDto, TokenValidityInSeconds/10);
    }

    /**
     * 查询全部数据
     * @param filter /
     * @return /
     */
    public Map<String, Object> getAll(String filter, Integer pageSize, Integer pageNum){
        List<OnlineUserDto> onlineUserDtos = getAll(filter);
        List<OnlineUserDto> one= PageUtil.toPage(pageNum,pageSize, onlineUserDtos);
        return PageUtil.toPage(
                one,
                onlineUserDtos.size()
        );
    }

    /**
     * 查询全部数据，不分页
     * @param filter /
     * @return /
     */
    public List<OnlineUserDto> getAll(String filter){
        List<String> keys = redisService.scan(onlineKey + "*");
        Collections.reverse(keys);
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            OnlineUserDto onlineUserDto = (OnlineUserDto) redisService.get(key);
            if(StringUtils.isNotBlank(filter)){
                if(onlineUserDto.toString().contains(filter)){
                    onlineUserDtos.add(onlineUserDto);
                }
            } else {
                onlineUserDtos.add(onlineUserDto);
            }
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }

    /**
     * 踢出用户
     * @param key /
     */
    public void kickOut(String key){
        if(!key.contains("online-token-"))
        key = onlineKey + key;
        redisService.del(key);
    }

    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        String key = onlineKey + token;
        redisService.del(key);
    }
    
    /**
     * 查询用户
     * @param key /
     * @return /
     */
    public OnlineUserDto getOne(String key) {
        return (OnlineUserDto)redisService.get(key);
    }

    /**
     * 检测用户是否在之前已经登录，已经登录踢下线
     * @param userName 用户名
     */
    public void checkLoginOnUser(String userName, String igoreToken){
        List<OnlineUserDto> onlineUserDtos = getAll(userName);
        if(onlineUserDtos ==null || onlineUserDtos.isEmpty()){
            return;
        }
        for(OnlineUserDto onlineUserDto : onlineUserDtos){
            if(onlineUserDto.getUserName().equals(userName)){
                try {
                    String token = EncryptUtils.desDecrypt(onlineUserDto.getKey());
                    if(StringUtils.isNotBlank(igoreToken)&&!igoreToken.equals(token)){
                        this.kickOut(token);
                    }else if(StringUtils.isBlank(igoreToken)){
                        this.kickOut(token);
                    }
                } catch (Exception e) {
                    log.error("checkUser is error",e);
                }
            }
        }
    }

    /**
     * 根据用户名强退用户
     * @param username /
     */
    @Async
    public void kickOutForUsername(String username) throws Exception {
        List<OnlineUserDto> onlineUsers = getAll(username);
        for (OnlineUserDto onlineUser : onlineUsers) {
            if (onlineUser.getUserName().equals(username)) {
                String token =EncryptUtils.desDecrypt(onlineUser.getKey());
                kickOut(token);
            }
        }
    }
}
