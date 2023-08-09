package com.example.guanWang.service;

import com.example.guanWang.model.ServiceIdea;

import java.util.List;

public interface ServiceIdeaService {
    List<ServiceIdea> list(String keyword, Integer pageSize, Integer pageNum);

    List<ServiceIdea> listAll();

    int create(ServiceIdea serviceIdea);

    int update(Long id, ServiceIdea serviceIdea);

    int delete(Long id);
}
