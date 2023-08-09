package com.example.guanWang.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProdBriefly implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String prodName;
    private Integer viewCount;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long prodType;
}
