package com.example.guanWang.service;

import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.dto.ProdParam;
import com.example.guanWang.model.NewsBrieflyInitial;
import com.example.guanWang.model.ProdBriefly;
import com.example.guanWang.model.ProdPreShow;
import com.example.guanWang.model.ProdRecommend;

import java.util.List;

public interface ProdService {

    List<ProdPreShow> getProdByType( Long prodType);

    List<ProdRecommend> getRecommendProd(Long prodId, Long prodType);

    List<ProdBriefly> getProdByKeywordAndType(String keyword, Long prodType);

    int delete(Long prodId);

    int deleteBatchProd(List<Long> idList);

    ProdParam queryProdContent(Long id);

    int addContent(ProdParam prodParam);

    int updateContent(ProdParam prodParam);
}
