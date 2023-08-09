package com.example.guanWang.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class ProdRecommend {
    @JsonSerialize(using = ToStringSerializer.class)
    private  Long  id;
    private String prodName;
    private String pic;
}
