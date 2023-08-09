package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.HistoryMapper;
import com.example.guanWang.model.History;
import com.example.guanWang.model.Job;
import com.example.guanWang.service.HistoryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public History queryHistory(Long id) {
        return historyMapper.queryHistory(id);
    }


    @Override
    public List<History> allList() {
        List<History> historyList= historyMapper.allList();
        return historyList;
    }

    @Override
    public List<History> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<History> depList= historyMapper.list(keyword);
        return depList;
    }

    @Override
    public int create(History history) {
        Long historyId= IdGenerator.nextId();
        history.setId(historyId);
        return historyMapper.create(history);
    }

    @Override
    public int update(Long id, History history) {
        history.setId(id);
        return historyMapper.update(history);
    }

    @Override
    public int delete(Long id) {
        return historyMapper.delete(id);
    }
}
