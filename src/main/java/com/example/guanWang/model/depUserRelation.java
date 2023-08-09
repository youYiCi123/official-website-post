package com.example.guanWang.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class depUserRelation {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long depId;

    private String depName;

    List<UmsAdmin> userRelationList;

}

