package com.example.guanWang.mapper;

import com.example.guanWang.model.ConceptIdea;

import java.util.List;

public interface ConceptIdeaMapper {

    List<ConceptIdea> list(String keyword);

    int create(ConceptIdea conceptIdea);

    int update(ConceptIdea conceptIdea);

    int delete(Long id);
}
