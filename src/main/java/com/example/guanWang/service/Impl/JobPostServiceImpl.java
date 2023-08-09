package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.mapper.DepMapper;
import com.example.guanWang.mapper.JobMapper;
import com.example.guanWang.model.*;
import com.example.guanWang.service.JobPostService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobPostServiceImpl implements JobPostService {

    @Autowired
    private JobMapper jobMapper;

    @Override
    public List<Job> jobList() {
        List<Job> depList= jobMapper.jobList();
        return depList;
    }

    @Override
    public List<JobTableShow> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobTableShow> depList= jobMapper.list(keyword);
        return depList;
    }

    @Override
    public int delete(Long id) {
        return jobMapper.delete(id);
    }

    @Override
    public int deleteBatch(List<Long> idList) {
        return jobMapper.deleteBatch(idList);
    }

    @Override
    public int addContent(Job job) {
        Long jobId= IdGenerator.nextId();
        job.setId(jobId);
        return jobMapper.addContent(job);
    }

    @Override
    public Job queryJobContent(Long id) {
        return jobMapper.queryJobContent(id);
    }

    @Override
    public int updateContent(Job job) {
        return jobMapper.updateContent(job);
    }

}
