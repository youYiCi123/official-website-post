package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.mapper.CultureMapper;
import com.example.guanWang.mapper.HistoryMapper;
import com.example.guanWang.model.Culture;
import com.example.guanWang.model.History;
import com.example.guanWang.service.CultureService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CultureServiceImpl implements CultureService {

    @Autowired
    private CultureMapper cultureMapper;

    @Override
    public Culture queryCulture(Long id) {
        return cultureMapper.queryCulture(id);
    }


    @Override
    public List<Culture> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Culture> depList= cultureMapper.list(keyword);
        return depList;
    }

    @Override
    public int create(Culture culture) {
        Long historyId= IdGenerator.nextId();
        culture.setId(historyId);
        return cultureMapper.create(culture);
    }

    @Override
    public int update(Long id, Culture culture) {
        culture.setId(id);
        return cultureMapper.update(culture);
    }

    @Override
    public int delete(Long id) {
        return cultureMapper.delete(id);
    }
}
