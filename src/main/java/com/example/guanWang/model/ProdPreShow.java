package com.example.guanWang.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class ProdPreShow {
    @JsonSerialize(using = ToStringSerializer.class)
    private  Long  id;
    private String prodName;
    private String pic;
    private String feature;//特点
    private String principle;//原理
}
