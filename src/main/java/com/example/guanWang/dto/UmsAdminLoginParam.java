package com.example.guanWang.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;


/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Data
@EqualsAndHashCode(callSuper = false)//https://blog.csdn.net/qq_27093465/article/details/90056695
public class UmsAdminLoginParam {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String code;
    @NotEmpty
    private String uuid = "";
}
