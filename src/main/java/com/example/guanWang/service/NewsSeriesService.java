package com.example.guanWang.service;

import com.example.guanWang.model.SeriesModel;
import com.example.guanWang.model.UmsRole;

import java.util.List;

public interface NewsSeriesService {

    List<SeriesModel> list(String keyword, Integer pageSize, Integer pageNum);

    List<SeriesModel> listAll();

    int create(SeriesModel seriesModel);

    int update(Long id, SeriesModel seriesModel);

    int delete(Long id);
}
