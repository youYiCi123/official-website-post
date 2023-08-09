package com.example.guanWang.mapper;

import com.example.guanWang.dto.IntroParam;

public interface IntroMapper {

    IntroParam queryHonorContent();

    int addContent(IntroParam introParam);

    int updateContent( IntroParam introParam);
}
