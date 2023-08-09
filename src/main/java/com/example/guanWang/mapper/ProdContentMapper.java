package com.example.guanWang.mapper;

import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.dto.ProdParam;
import com.example.guanWang.model.ProdPreShow;

import java.util.List;

public interface ProdContentMapper {

    List<ProdPreShow> getProdByType(Long prodType);

    ProdParam queryProdContent(Long id);

    int updateContent(ProdParam prodParam);

    int addContent(ProdParam prodParam);

    int delete(Long prodId);

    int deleteBatchProd(List<Long> idList);
}
