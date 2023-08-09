package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.IdGenerator;
import com.example.guanWang.dto.ProdParam;
import com.example.guanWang.mapper.ProdContentMapper;
import com.example.guanWang.mapper.ProdMapper;
import com.example.guanWang.model.ProdBriefly;
import com.example.guanWang.model.ProdPreShow;
import com.example.guanWang.model.ProdRecommend;
import com.example.guanWang.model.UmsAdmin;
import com.example.guanWang.service.ProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProdServiceImpl implements ProdService {

    @Autowired
    private ProdMapper prodMapper;

    @Autowired
    private ProdContentMapper prodContentMapper;

    @Override
    public List<ProdPreShow> getProdByType(Long prodType) {
        return prodContentMapper.getProdByType(prodType);
    }

    @Override
    public List<ProdRecommend> getRecommendProd(Long prodId,Long prodType) {
        return prodMapper.getRecommendProd(prodId,prodType);
    }

    @Override
    public List<ProdBriefly> getProdByKeywordAndType(String keyword, Long prodType) {
        return prodMapper.getProdByKeywordAndType(keyword,prodType);
    }

    @Override
    public int delete(Long prodId) {
        int count = prodMapper.delete(prodId);
        if (count > 0) {
            return prodContentMapper.delete(prodId);
        }
        return -1;
    }

    @Override
    public int deleteBatchProd(List<Long> idList) {
        int count = prodMapper.deleteBatchProd(idList);
        if (count > 0) {
            return prodContentMapper.deleteBatchProd(idList);
        }
        return -1;
    }

    @Override
    public ProdParam queryProdContent(Long id) {
        return prodContentMapper.queryProdContent(id);
    }

    @Override
    public int addContent(ProdParam prodParam) {
        Long prodId= IdGenerator.nextId();
        String str = String.valueOf(prodId);
        String substring = str.substring(str.length() - 5);
        Long prodLong = Long.valueOf(substring);
        prodParam.setId(prodLong);
        int count= prodContentMapper.addContent(prodParam);
        if(count>0){
            ProdBriefly prodBriefly=new ProdBriefly();
            prodBriefly.setId(prodId);
            prodBriefly.setProdName(prodParam.getProdName());
            prodBriefly.setProdType(prodParam.getProdType());
            return prodMapper.add(prodBriefly);
        }
        return 0;
    }

    @Override
    public int updateContent(ProdParam prodParam) {
        int count=prodContentMapper.updateContent(prodParam);
        if(count>0){
            ProdBriefly prodBriefly=new ProdBriefly();
            prodBriefly.setId(prodParam.getId());
            prodBriefly.setProdType(prodParam.getProdType());
            prodBriefly.setProdName(prodParam.getProdName());
            return prodMapper.update(prodBriefly);
        }
        return 0;
    }
}
