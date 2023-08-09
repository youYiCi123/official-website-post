package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.ProdParam;
import com.example.guanWang.model.ProdBriefly;
import com.example.guanWang.model.ProdPreShow;
import com.example.guanWang.model.ProdRecommend;
import com.example.guanWang.service.ProdService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/prod")
public class ProdController {

    @Autowired
    private ProdService prodService;

    //网站前台产品列表
    @RequestMapping(value = "/getAllProdByType", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAllProdByType(@RequestParam(value = "prodType", defaultValue = "0") Long prodType,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProdPreShow> prodPreShowList = prodService.getProdByType(prodType);
        return CommonResult.success(CommonPage.restPage(prodPreShowList), "请求成功");
    }

    //网站同品推荐
    @RequestMapping(value = "/getRecommendProd", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getRecommendProd(@RequestParam(value = "prodId", defaultValue = "0") Long prodId,
                                         @RequestParam(value = "prodType", defaultValue = "0") Long prodType,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "8") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProdRecommend> ProdRecommendList = prodService.getRecommendProd(prodId,prodType);
        return CommonResult.success(CommonPage.restPage(ProdRecommendList), "请求成功");
    }

    @RequestMapping(value = "/getAllProd", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAllProd(@RequestParam(value = "keyword", required = false) String keyword,
                                   @RequestParam(value = "prodType", defaultValue = "0") Long prodType,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProdBriefly> prodBrieflyList = prodService.getProdByKeywordAndType(keyword, prodType);
        return CommonResult.success(CommonPage.restPage(prodBrieflyList), "请求成功");
    }

    @RequestMapping(value = "/addContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addContent(@RequestBody ProdParam prodAddParam) {
        int count = prodService.addContent(prodAddParam);
        if (count != 0) {
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/queryContent/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ProdParam> queryProdContent(@PathVariable Long id) {
        return CommonResult.success(prodService.queryProdContent(id));
    }

    @RequestMapping(value = "/updateContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateContent(@RequestBody ProdParam prodParam) {
        int count = prodService.updateContent(prodParam);
        if (count != 0)
            return CommonResult.success();
        else
            return CommonResult.failed();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        //删除培训信息
        int count = prodService.delete(id);
        if (count < 0)
            return CommonResult.failed("删除培训信息错误");
        return CommonResult.success();
    }

    @RequestMapping(value = "/handleBatchDelete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody Long[] multipleSelectionId) {
        List<Long> idList = Arrays.stream(multipleSelectionId).collect(Collectors.toList());
        //删除多个培训信息
        int count = prodService.deleteBatchProd(idList);
        if (count < 0)
            return CommonResult.failed("删除培训信息错误");
        return CommonResult.success();
    }
}
