package com.example.guanWang.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UmsAdminShow implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String username;

    private Integer sex;

    private String password;

    private String email;

    private String nickName;

    private String depId;

    private String depName;

    private String duty;

    private String phoneNumber;

    private String address;

    private Date createTime;

    private Integer status;

    private String note;
}
