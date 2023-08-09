package com.example.guanWang.mapper;

import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.model.Dep;
import com.example.guanWang.model.Job;
import com.example.guanWang.model.JobTableShow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobMapper {

    List<Job> jobList();

    List<JobTableShow> list(@Param("keyword") String keyword);

    int delete(Long id);

    int deleteBatch(@Param("idList") List<Long> idList);

    int addContent(Job job);

    Job queryJobContent(Long id);

    int updateContent(Job job);

}
