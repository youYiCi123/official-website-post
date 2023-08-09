package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.ProdSeriesMapper;
import com.example.guanWang.model.SeriesModel;
import com.example.guanWang.service.ProdSeriesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdSeriesServiceImpl implements ProdSeriesService {

    @Autowired
    private ProdSeriesMapper prodSeriesMapper;

    @Override
    public List<SeriesModel> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SeriesModel> prodSeries= prodSeriesMapper.list(keyword);
        return prodSeries;
    }

    @Override
    public List<SeriesModel> listAll() {
        return prodSeriesMapper.listAll();
    }

    @Override
    public int create(SeriesModel seriesModel) {
        Long jobId= IdGenerator.nextId();
        String str = String.valueOf(jobId);
        String substring = str.substring(str.length() - 5);
        Long aLong = Long.valueOf(substring);
        seriesModel.setId(aLong);
        return prodSeriesMapper.create(seriesModel);
    }

    @Override
    public int update(Long id, SeriesModel seriesModel) {
        seriesModel.setId(id);
        return prodSeriesMapper.update(seriesModel);
    }

    @Override
    public int delete(Long id) {
        return prodSeriesMapper.delete(id);
    }
}
