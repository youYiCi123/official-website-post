package com.example.guanWang.service;


//import com.example.demo.dto.UmsAdminParam;
//import com.example.demo.dto.UpdateAdminPasswordParam;
//import com.example.demo.model.*;
import com.example.guanWang.dto.UmsAdminParam;
import com.example.guanWang.dto.UpdateAdminPasswordParam;
import com.example.guanWang.model.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台用户管理Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    List<String> getAllUserPhone();

   int saveBatch(List<UmsAdmin> umsAdmins);
    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    UmsAdmin getItem(Long id);

 /**
  * 根据用户id获取用户名
  * @param id
  * @return
  */
   String getUserNameById(Long id);

   List<UserNameAndId> getAllUserName();

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UmsAdmin> list(String keyword, String depId, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, UmsAdminShow admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);

    int deleteBatch(List<Long> idList);

    /**
     * 修改用户角色关系
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对应角色
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改密码
     */
    int updatePassword(UpdateAdminPasswordParam updatePasswordParam);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取缓存服务
     */
    UmsAdminCacheService getCacheService();
}
