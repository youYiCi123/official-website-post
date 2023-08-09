package com.example.guanWang.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NewsDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    //新闻主题
    private String theme;
    //创建人
    private Long createdAdminId;
    //创建人姓名
    private Long createdAdminName;
    //创建日期
    private Date createdTime;
    //培训内容
    private Integer viewCount;
}
