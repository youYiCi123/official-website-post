package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.model.SeriesModel;
import com.example.guanWang.model.SolutionSeriesModel;
import com.example.guanWang.model.SolutionWithChild;
import com.example.guanWang.service.ProdSeriesService;
import com.example.guanWang.service.SolutionSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/solutionSeries")
public class SolutionSeriesController {
    @Autowired
    private SolutionSeriesService solutionSeriesService;

    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SolutionSeriesModel>> list(@PathVariable Long parentId,
                                                              @RequestParam(value = "keyWord", required = false) String keyWord,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SolutionSeriesModel> roleList = solutionSeriesService.list(parentId, keyWord, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    //获取一级菜单
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SolutionSeriesModel>> listAll() {
        List<SolutionSeriesModel> seriesList = solutionSeriesService.listAll();
        return CommonResult.success(seriesList);
    }

    @RequestMapping(value = "/create/{parentId}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@PathVariable Long parentId,@RequestBody SolutionSeriesModel solutionSeriesModel) {
        solutionSeriesModel.setParentId(parentId);
        if(parentId==0){
            solutionSeriesModel.setSeriesLevel(0);
        }else{
            solutionSeriesModel.setSeriesLevel(1);
        }
        int count = solutionSeriesService.create(solutionSeriesModel);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody SolutionSeriesModel solutionSeriesModel) {
        int count = solutionSeriesService.update(solutionSeriesModel);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = solutionSeriesService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SolutionWithChild>> listWithChildren() {
        List<SolutionWithChild> list = solutionSeriesService.listWithChildren();
        return CommonResult.success(list);
    }

}
