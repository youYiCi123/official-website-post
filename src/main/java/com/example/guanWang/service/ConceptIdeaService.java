package com.example.guanWang.service;

import com.example.guanWang.model.ConceptIdea;

import java.util.List;

public interface ConceptIdeaService {
    List<ConceptIdea> list(String keyword, Integer pageSize, Integer pageNum);
    
    int create(ConceptIdea conceptIdea);

    int update(Long id, ConceptIdea conceptIdea);

    int delete(Long id);
}
