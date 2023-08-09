package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.dto.IntroParam;
import com.example.guanWang.mapper.IntroMapper;
import com.example.guanWang.service.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntroServiceImpl implements IntroService {

    @Autowired
    private IntroMapper introMapper;

    @Override
    public IntroParam queryHonorContent() {
        return introMapper.queryHonorContent();
    }

    @Override
    public int addContent(IntroParam introParam) {
        Long prodId= IdGenerator.nextId();
        introParam.setId(prodId);
        return introMapper.addContent(introParam);
    }

    @Override
    public int updateContent(IntroParam introParam) {
        return introMapper.updateContent(introParam);
    }
}
