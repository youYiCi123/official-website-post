package com.example.guanWang.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class JobTableShow {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String recruitName;
    private Integer recruitCount;
    private Date releaseDate;
    private String salary;
}
