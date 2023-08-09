package com.example.guanWang.mapper;

import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.model.NewsLinkModel;
import com.example.guanWang.model.NewsPreDetail;

import java.util.List;

public interface NewsContentMapper {

    NewsParam queryNewsContent(Long id);

    NewsPreDetail queryNewsPreContent(Long id);



    int updateContent(NewsParam trainContentParam);

    int addContent(NewsParam trainContentParam);

    int delete(Long newsId);

    int deleteBatchNews(List<Long> idList);
}
