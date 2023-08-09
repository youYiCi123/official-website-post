package com.example.guanWang.mapper;

import com.example.guanWang.model.History;

import java.util.List;

public interface HistoryMapper {

    History queryHistory(Long id);

    List<History> allList();

    List<History> list(String keyword);

    int create(History history);

    int update(History history);

    int delete(Long id);
}
