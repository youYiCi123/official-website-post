package com.example.guanWang.service;

import com.example.guanWang.model.Culture;
import com.example.guanWang.model.History;

import java.util.List;

public interface CultureService {

    Culture queryCulture(Long id);

    List<Culture> list(String keyword, Integer pageSize, Integer pageNum);

    int create(Culture culture);

    int update(Long id, Culture culture);

    int delete(Long id);
}
