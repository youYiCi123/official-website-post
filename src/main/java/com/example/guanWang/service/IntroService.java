package com.example.guanWang.service;

import com.example.guanWang.dto.IntroParam;

public interface IntroService {

    IntroParam queryHonorContent();

    int addContent(IntroParam introParam);

    int updateContent( IntroParam introParam);
}
