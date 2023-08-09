package com.example.guanWang.mapper;

import com.example.guanWang.model.SeriesModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsSeriesMapper {
    int create(SeriesModel seriesModel);

    List<SeriesModel> list(@Param("keyword") String keyword);

    List<SeriesModel> listAll();

    int delete(Long id);

    int update(SeriesModel seriesModel);
}
