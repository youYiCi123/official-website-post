package com.example.guanWang.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class ProdParam {
    private  Long  id;
    private String prodName;
    private String pic;
    private String albumPics;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long prodType;
    private String principle;
    private String feature;
    private String registerCard;
    private String model;
    private String expireDate;
    private String pack;
}
