package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.ConceptIdeaMapper;
import com.example.guanWang.mapper.ServiceIdeaMapper;
import com.example.guanWang.model.ConceptIdea;
import com.example.guanWang.service.ConceptIdeaService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptIdeaServiceImpl implements ConceptIdeaService {

    @Autowired
    private ConceptIdeaMapper conceptIdeaMapper;

    @Override
    public List<ConceptIdea> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return  conceptIdeaMapper.list(keyword);
    }

    @Override
    public int create(ConceptIdea conceptIdea) {
        Long jobId= IdGenerator.nextId();
        String str = String.valueOf(jobId);
        String substring = str.substring(str.length() - 5);
        Long aLong = Long.valueOf(substring);
        conceptIdea.setId(aLong);
        return conceptIdeaMapper.create(conceptIdea);
    }

    @Override
    public int update(Long id, ConceptIdea conceptIdea) {
        conceptIdea.setId(id);
        return conceptIdeaMapper.update(conceptIdea);
    }

    @Override
    public int delete(Long id) {
        return conceptIdeaMapper.delete(id);
    }
}
