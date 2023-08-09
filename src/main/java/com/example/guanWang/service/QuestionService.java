package com.example.guanWang.service;

import com.example.guanWang.model.Question;
import com.example.guanWang.model.QuestionHot;

import java.util.List;

public interface QuestionService {

    List<Question> list(String solutionSeries,String keyWord, Integer pageSize, Integer pageNum);

    List<Question> getListByFirstLevel(String firstLevel, Integer pageSize, Integer pageNum);

    List<QuestionHot> getQuestionHot(Integer pageSize, Integer pageNum);

    int addQuestionView(Long id);

    List<Question>  getQuestionListByType(String id);

    int create(Question question);

    int update(Question question);

    int delete(Long id);
}
