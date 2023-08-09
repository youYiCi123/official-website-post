package com.example.guanWang.mapper;


import com.example.guanWang.model.NewsBrieflyInitial;
import com.example.guanWang.model.NewsLink;
import com.example.guanWang.model.NewsLinkModel;
import com.example.guanWang.model.NewsShow;

import java.util.List;

public interface NewsMapper {

    List<NewsBrieflyInitial> getTrainByDateAndKeyword(String startDate, String endDate, String keyword,Long newsType);

    List<NewsShow> getNewsByType(Long newsType);

    int setViewCount(Long id);

    List<NewsLink> getNewsSort(Long id);

    int delete(Long newsId);

    int add(NewsBrieflyInitial newsBrieflyInitial);

    int update(NewsBrieflyInitial newsBrieflyInitial);

    int deleteBatchTrain(List<Long> idList);

}
