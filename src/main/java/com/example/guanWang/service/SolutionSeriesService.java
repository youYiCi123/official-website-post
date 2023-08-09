package com.example.guanWang.service;

import com.example.guanWang.model.SolutionSeriesModel;
import com.example.guanWang.model.SolutionWithChild;

import java.util.List;

public interface SolutionSeriesService {

    List<SolutionSeriesModel> list(Long parentId,String keyWord, Integer pageSize, Integer pageNum);

    List<SolutionSeriesModel> listAll();

    int create(SolutionSeriesModel seriesModel);

    int update(SolutionSeriesModel seriesModel);

    int delete(Long id);

    List<SolutionWithChild> listWithChildren();
}
