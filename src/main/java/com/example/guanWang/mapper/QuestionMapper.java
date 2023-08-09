package com.example.guanWang.mapper;

import com.example.guanWang.model.Question;
import com.example.guanWang.model.QuestionHot;

import java.util.List;

public interface QuestionMapper {
    List<Question> list(String solutionSeries,String keyWord);

    List<Question> getListByFirstLevel(String firstLevel);

    List<QuestionHot> getQuestionHot();

    int addQuestionView(Long id);

    List<Question> getQuestionListByType(String id);

    int create(Question question);

    int update(Question question);

    int delete(Long id);
}
