package com.example.guanWang.dto;

import lombok.Data;

@Data
public class NewsShowHomeParam {
    private Long id;
    private String theme;
    private String monthYear;
    private String dateOfDay;
    private String dateOfMonth;
    private String introduce;
    private String pic;
}
