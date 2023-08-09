package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.ServiceIdeaMapper;
import com.example.guanWang.model.ServiceIdea;
import com.example.guanWang.service.ServiceIdeaService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceIdeaServiceImpl implements ServiceIdeaService {

    @Autowired
    private ServiceIdeaMapper serviceIdeaMapper;

    @Override
    public List<ServiceIdea> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return  serviceIdeaMapper.list(keyword);
    }

    @Override
    public List<ServiceIdea> listAll() {
        return serviceIdeaMapper.listAll();
    }

    @Override
    public int create(ServiceIdea serviceIdea) {
        Long jobId= IdGenerator.nextId();
        String str = String.valueOf(jobId);
        String substring = str.substring(str.length() - 5);
        Long aLong = Long.valueOf(substring);
        serviceIdea.setId(aLong);
        return serviceIdeaMapper.create(serviceIdea);
    }

    @Override
    public int update(Long id, ServiceIdea serviceIdea) {
        serviceIdea.setId(id);
        return serviceIdeaMapper.update(serviceIdea);
    }

    @Override
    public int delete(Long id) {
        return serviceIdeaMapper.delete(id);
    }
}
