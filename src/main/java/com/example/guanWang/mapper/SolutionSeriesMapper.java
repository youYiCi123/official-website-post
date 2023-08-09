package com.example.guanWang.mapper;

import com.example.guanWang.model.SolutionSeriesModel;

import java.util.List;

public interface SolutionSeriesMapper {
    List<SolutionSeriesModel> list(Long parentId,String keyWord);

    List<SolutionSeriesModel> listAll();

    int create(SolutionSeriesModel seriesModel);

    int update(SolutionSeriesModel seriesModel);

    int delete(Long id);
}
