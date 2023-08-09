package com.example.guanWang.model;

import lombok.Data;

@Data
public class NewsLinkModel {
    private String preLink;
    //前一个新闻主题
    private String preTheme;

    private String postLink;
    //后一个新闻主题
    private String postTheme;
}
