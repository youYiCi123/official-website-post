package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.model.SeriesModel;
import com.example.guanWang.model.UmsRole;
import com.example.guanWang.service.NewsSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/newsSeries")
public class NewsSeriesController {

    @Autowired
    private NewsSeriesService newsSeriesService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SeriesModel>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SeriesModel> roleList = newsSeriesService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<SeriesModel>> listAll() {
        List<SeriesModel> seriesList = newsSeriesService.listAll();
        return CommonResult.success(seriesList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody SeriesModel seriesModel) {
        int count = newsSeriesService.create(seriesModel);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody SeriesModel seriesModel) {
        int count = newsSeriesService.update(id, seriesModel);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = newsSeriesService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}
