package com.example.guanWang.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
//import com.example.demo.api.CommonPage;
//import com.example.demo.api.CommonResult;
//import com.example.demo.api.config.bean.LoginCodeEnum;
//import com.example.demo.api.config.bean.LoginProperties;
//import com.example.demo.dto.UmsAdminLoginParam;
//import com.example.demo.dto.UmsAdminParam;
//import com.example.demo.dto.UpdateAdminPasswordParam;
//import com.example.demo.file.vo.RPanUserVO;
//import com.example.demo.mapper.UmsAdminRoleRelationMapper;
//import com.example.demo.mapper.UmsRoleMapper;
//import com.example.demo.model.*;
//import com.example.demo.service.*;
import com.example.guanWang.api.bean.LoginCodeEnum;
import com.example.guanWang.api.bean.LoginProperties;
import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.UmsAdminLoginParam;
import com.example.guanWang.dto.UmsAdminParam;
import com.example.guanWang.dto.UpdateAdminPasswordParam;
import com.example.guanWang.mapper.UmsAdminRoleRelationMapper;
import com.example.guanWang.mapper.UmsRoleMapper;
import com.example.guanWang.model.*;
import com.example.guanWang.service.DepService;
import com.example.guanWang.service.RedisService;
import com.example.guanWang.service.UmsAdminService;
import com.example.guanWang.service.UmsRoleService;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 后台用户管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@RequestMapping("/admin")
public class UmsAdminController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RedisService redisService;

    @Autowired
    private DepService depService;

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Resource
    private LoginProperties loginProperties;

    private static final String VerificationCode="Verification-Code";



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        // 查询验证码
        String code = (String) redisService.get(umsAdminLoginParam.getUuid());
        // 清除验证码
        redisService.del(umsAdminLoginParam.getUuid());
        if (StringUtils.isBlank(code)) {
            return CommonResult.failed("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(umsAdminLoginParam.getCode()) || !umsAdminLoginParam.getCode().equalsIgnoreCase(code)) {
            return CommonResult.failed("验证码错误");
        }
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ResponseBody
    @GetMapping("/code")
    public CommonResult getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = VerificationCode + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisService.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return CommonResult.success(imgResult);
    }



//    @GetMapping("/user")
//    @ResponseBody
//    public CommonResult<RPanUserVO> info(Principal principal) {
//        if(principal==null){
//            return CommonResult.unauthorized(null);
//        }
//        String username = principal.getName();
//        String loginId=username+"_loginId";
//        String userIdAndDepId=(String)redisService.get(loginId);
//        long userId = Long.parseLong(userIdAndDepId.
//                substring(0, userIdAndDepId.lastIndexOf(",")));
////        long userId=(long)(int)redisService.get(loginId);
//        return CommonResult.success(iUserService.info(userId));
//    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (refreshToken == null) {
            return CommonResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal) {
        if(principal==null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        data.put("nickName",umsAdmin.getNickName());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        String userIdAndDepId=umsAdmin.getId().toString()+','+umsAdmin.getDepId();
        return CommonResult.success(data);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout() {
        return CommonResult.success(null);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "depId", required = false) String depId,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(keyword,depId, pageSize, pageNum);
        List<DepIdToName> depIdToNameList = depService.getDepIdToName();
        for(UmsAdmin umsAdmin:adminList){
            Optional<DepIdToName> depIdToName = depIdToNameList.stream().filter(t -> t.getId().equals(umsAdmin.getDepId())).findFirst();
            depIdToName.ifPresent(t->umsAdmin.setDepName(t.getDepName()));
        }
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id) {
        UmsAdmin admin = adminService.getItem(id);
        return CommonResult.success(admin);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdminShow admin) {
        int count = adminService.update(id, admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return CommonResult.success(status);
        } else if (status == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (status == -2) {
            return CommonResult.failed("找不到该用户");
        } else if (status == -3) {
            return CommonResult.failed("旧密码错误");
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            //删除count角色用户数
            List<Long> roleIds = umsAdminRoleRelationMapper.selectById(id);
            roleService.subAdminCount(roleIds);
            //删除用户id和角色的对应关系
            umsAdminRoleRelationMapper.deleteByAdminId(id);
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody Long[] multipleSelectionId) {
        List<Long> idList= Arrays.stream(multipleSelectionId).collect(Collectors.toList());
        int count = adminService.deleteBatch(idList);
        if (count > 0) {
            //修改用户角色对应的count用户数
            List<UmsAdminRoleRelation> oldRoleIds=umsAdminRoleRelationMapper.selectRoleListByAdminId(idList);

            Map<Long, Long> needSubRoleMap = oldRoleIds.stream().collect(Collectors.groupingBy(UmsAdminRoleRelation::getRoleId, Collectors.counting()));
            List<RoleGroupCount> RoleGroupCountList=new ArrayList<>();
            for (Map.Entry<Long, Long> longLongEntry : needSubRoleMap.entrySet()) {
                RoleGroupCount roleGroupCount = new RoleGroupCount();
                roleGroupCount.setRoleId(longLongEntry.getKey());
                roleGroupCount.setCount(longLongEntry.getValue());
                RoleGroupCountList.add(roleGroupCount);
            }
            umsRoleMapper.subAdminCountByClass(RoleGroupCountList);

            //删除用户角色对应关系
            umsAdminRoleRelationMapper.deleteByAdminIds(idList);

            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsAdminShow umsAdmin = new UmsAdminShow();
        umsAdmin.setStatus(status);
        int count = adminService.update(id,umsAdmin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
}
