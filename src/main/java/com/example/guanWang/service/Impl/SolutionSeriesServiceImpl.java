package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.SolutionSeriesMapper;
import com.example.guanWang.mapper.SolutionWithChildMapper;
import com.example.guanWang.model.SolutionSeriesModel;
import com.example.guanWang.model.SolutionWithChild;
import com.example.guanWang.service.SolutionSeriesService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolutionSeriesServiceImpl implements SolutionSeriesService {

    @Autowired
    private SolutionSeriesMapper solutionSeriesMapper;

    @Autowired
    private SolutionWithChildMapper solutionWithChildMapper;

    @Override
    public List<SolutionSeriesModel> list(Long parentId, String keyWord,Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return solutionSeriesMapper.list(parentId,keyWord);
    }

    @Override
    public List<SolutionSeriesModel> listAll() {
        return solutionSeriesMapper.listAll();
    }

    @Override
    public int create(SolutionSeriesModel seriesModel) {
        Long jobId = IdGenerator.nextId();
        String str = String.valueOf(jobId);
        String substring = str.substring(str.length() - 5);
        Long aLong = Long.valueOf(substring);
        seriesModel.setId(aLong);
        seriesModel.setCreatedTime(new Date());
        return solutionSeriesMapper.create(seriesModel);
    }

    @Override
    public int update(SolutionSeriesModel seriesModel) {
        return solutionSeriesMapper.update(seriesModel);
    }

    @Override
    public int delete(Long id) {
        return solutionSeriesMapper.delete(id);
    }

    @Override
    public List<SolutionWithChild> listWithChildren() {
        return solutionWithChildMapper.listWithChildren();
    }

}
