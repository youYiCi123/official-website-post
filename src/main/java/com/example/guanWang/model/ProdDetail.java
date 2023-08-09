package com.example.guanWang.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProdDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    private  Long  id;
    private String prodName;
    private String pic;
    private String albumPics;//相册
    private String principle;//原理
    private String feature;//特点
    private String registerCard;//注册证号
    private String model;//规格型号
    private String expireDate;//有效期
    private String pack;//
}
