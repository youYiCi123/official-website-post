package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.NewsSeriesMapper;
import com.example.guanWang.model.SeriesModel;
import com.example.guanWang.model.UmsRole;
import com.example.guanWang.model.UmsRoleExample;
import com.example.guanWang.service.NewsSeriesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsSeriesServiceImpl implements NewsSeriesService {

    @Autowired
    private NewsSeriesMapper newsSeriesMapper;

    @Override
    public List<SeriesModel> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<SeriesModel> prodSeries= newsSeriesMapper.list(keyword);
        return prodSeries;
    }

    @Override
    public List<SeriesModel> listAll() {
        return newsSeriesMapper.listAll();
    }

    @Override
    public int create(SeriesModel seriesModel) {
        Long jobId= IdGenerator.nextId();
        String str = String.valueOf(jobId);
        String substring = str.substring(str.length() - 5);
        Long aLong = Long.valueOf(substring);
        seriesModel.setId(aLong);
        return newsSeriesMapper.create(seriesModel);
    }

    @Override
    public int update(Long id, SeriesModel seriesModel) {
        seriesModel.setId(id);
        return newsSeriesMapper.update(seriesModel);
    }

    @Override
    public int delete(Long id) {
        return newsSeriesMapper.delete(id);
    }
}
