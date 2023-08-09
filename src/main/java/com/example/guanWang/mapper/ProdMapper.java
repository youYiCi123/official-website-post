package com.example.guanWang.mapper;

import com.example.guanWang.model.NewsBrieflyInitial;
import com.example.guanWang.model.ProdBriefly;
import com.example.guanWang.model.ProdPreShow;
import com.example.guanWang.model.ProdRecommend;

import java.util.List;

public interface ProdMapper {

    List<ProdRecommend> getRecommendProd(Long prodId, Long prodType);

    List<ProdBriefly> getProdByKeywordAndType(String keyword, Long prodType);

    int delete(Long newsId);

    int add(ProdBriefly prodBriefly);

    int update(ProdBriefly prodBriefly);

    int deleteBatchProd(List<Long> idList);

}
