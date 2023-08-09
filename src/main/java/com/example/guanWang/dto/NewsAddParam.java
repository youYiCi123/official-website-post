package com.example.guanWang.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class NewsAddParam {
    private Long newsId;
    private String userName;
    private String theme;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long newsType;
    private String introduce;
    private String pic;
    private String content;
}
