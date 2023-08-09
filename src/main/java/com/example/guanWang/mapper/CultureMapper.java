package com.example.guanWang.mapper;

import com.example.guanWang.model.Culture;

import java.util.List;

public interface CultureMapper {

    Culture queryCulture(Long id);

    List<Culture> list(String keyword);

    int create(Culture culture);

    int update(Culture culture);

    int delete(Long id);
}
