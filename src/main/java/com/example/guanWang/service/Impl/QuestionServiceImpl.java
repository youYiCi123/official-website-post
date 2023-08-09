package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.QuestionMapper;
import com.example.guanWang.mapper.SolutionSeriesMapper;
import com.example.guanWang.model.Question;
import com.example.guanWang.model.QuestionHot;
import com.example.guanWang.service.QuestionService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> list(String solutionSeries,String keyWord, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return questionMapper.list(solutionSeries,keyWord);
    }

    @Override
    public List<Question> getListByFirstLevel(String firstLevel, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return questionMapper.getListByFirstLevel(firstLevel);
    }

    @Override
    public List<QuestionHot> getQuestionHot(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return questionMapper.getQuestionHot();
    }

    @Override
    public int addQuestionView(Long id) {
        return questionMapper.addQuestionView(id);
    }

    @Override
    public List<Question> getQuestionListByType(String id) {
        return questionMapper.getQuestionListByType(id);
    }

    @Override
    public int create(Question question) {
        Long jobId = IdGenerator.nextId();
        String str = String.valueOf(jobId);
        String substring = str.substring(str.length() - 5);
        Long aLong = Long.valueOf(substring);
        question.setId(aLong);
        return questionMapper.create(question);
    }

    @Override
    public int update(Question question) {
        return questionMapper.update(question);
    }

    @Override
    public int delete(Long id) {
        return questionMapper.delete(id);
    }
}
