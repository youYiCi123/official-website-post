package com.example.guanWang.service;

import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.model.Dep;
import com.example.guanWang.model.Job;
import com.example.guanWang.model.JobTableShow;

import java.util.List;

public interface JobPostService {

    List<Job> jobList();
    /**
     * 根据职位名分页查询部门
     */
    List<JobTableShow> list(String keyword, Integer pageSize, Integer pageNum);
    /**
     * 删除指定职位
     */
    int delete(Long id);

    int deleteBatch(List<Long> idList);

    int addContent(Job job);

    Job queryJobContent(Long id);

    int updateContent(Job job);

}
