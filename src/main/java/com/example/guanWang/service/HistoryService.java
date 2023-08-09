package com.example.guanWang.service;

import com.example.guanWang.model.History;
import com.example.guanWang.model.Job;
import com.example.guanWang.model.UmsRole;

import java.util.List;

public interface HistoryService {

    History queryHistory(Long id);

    List<History> allList();

    List<History> list(String keyword, Integer pageSize, Integer pageNum);

    int create(History history);

    int update(Long id, History history);

    int delete(Long id);

}
