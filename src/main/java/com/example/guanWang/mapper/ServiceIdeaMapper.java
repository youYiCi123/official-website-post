package com.example.guanWang.mapper;

import com.example.guanWang.model.ServiceIdea;

import java.util.List;

public interface ServiceIdeaMapper {
    List<ServiceIdea> list(String keyword);

    List<ServiceIdea> listAll();

    int create(ServiceIdea serviceIdea);

    int update(ServiceIdea serviceIdea);

    int delete(Long id);
}
