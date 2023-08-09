package com.example.guanWang.service;



import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.model.NewsBrieflyInitial;
import com.example.guanWang.model.NewsLinkModel;
import com.example.guanWang.model.NewsPreDetail;
import com.example.guanWang.model.NewsShow;

import java.util.List;

public interface NewsService {

    /**
     * 查询培训信息
     * @param startDate
     * @param endDate
     * @param keyword
     * @return
     */
    List<NewsBrieflyInitial> getNewsByDateAndKeyword(String startDate, String endDate, String keyword,Long newsType);

    List<NewsShow> getNewsByType(Long newsType);
    /**
     * 删除培训简要信息
     * @param newsId
     * @return
     */
    int delete(Long newsId);

   int deleteBatchNews(List<Long> idList);

    NewsParam queryNewsContent(Long id);

    NewsPreDetail queryNewsPreContent(Long id);

    NewsLinkModel getNewsSort(Long id);

    int addContent(NewsAddParam newsContentParam);

    int updateContent(NewsParam newsContentParam);

}
