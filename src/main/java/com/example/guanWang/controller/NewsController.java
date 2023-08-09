package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.dto.NewsShowHomeParam;
import com.example.guanWang.model.NewsBrieflyInitial;
import com.example.guanWang.model.NewsLinkModel;
import com.example.guanWang.model.NewsPreDetail;
import com.example.guanWang.model.NewsShow;
import com.example.guanWang.service.NewsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    //官网首页上展示
    @RequestMapping(value = "/getAllNewsByType", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAllNewsByType(@RequestParam(value = "newsType", defaultValue = "0") Long newsType,
                                   @RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = "created_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<NewsShow> NewsShowList=newsService.getNewsByType(newsType);
        List<NewsShowHomeParam> newsList = new ArrayList<>();
        NewsShowList.stream().forEach(t->{
            NewsShowHomeParam newsShowHomeParam = new NewsShowHomeParam();
            newsShowHomeParam.setId(t.getId());
            newsShowHomeParam.setTheme(t.getTheme());
            newsShowHomeParam.setIntroduce(t.getIntroduce());
            newsShowHomeParam.setPic(t.getPic());
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//2019-01-11
            String date = sdf.format(t.getCreatedTime());
            String[] split = date.split("-");
            newsShowHomeParam.setMonthYear(date.substring(0,date.lastIndexOf("-")));
            newsShowHomeParam.setDateOfMonth(split[1]);
            newsShowHomeParam.setDateOfDay(split[2]);
            newsList.add(newsShowHomeParam);
        });
        return CommonResult.success(CommonPage.restPage(newsList),"请求成功");
    }


    @RequestMapping(value = "/getAllNews", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAllNews(@RequestParam(defaultValue = "") String[] date,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "newsType", defaultValue = "0") Long newsType,
                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        String startDate = null;
        String endDate = null;
        if (date.length == 2) {
            startDate = date[0];
            endDate = date[1];
        }
        String orderBy = "created_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        List<NewsBrieflyInitial> NewsBrieflyList=newsService.getNewsByDateAndKeyword(startDate, endDate,keyword,newsType);
        return CommonResult.success(CommonPage.restPage(NewsBrieflyList),"请求成功");
    }

    //后台查询
    @RequestMapping(value = "/queryContent/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<NewsParam> queryTrainContent(@PathVariable Long id){
        return CommonResult.success(newsService.queryNewsContent(id));
    }

    //前台页面展示
    @RequestMapping(value = "/queryNewsContent/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<NewsPreDetail> queryNewsContent(@PathVariable Long id){
        return CommonResult.success(newsService.queryNewsPreContent(id));
    }

    //前台页面展示
    @RequestMapping(value = "/getNewsSort/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<NewsLinkModel> getNewsSort(@PathVariable Long id){
        return CommonResult.success(newsService.getNewsSort(id));
    }

    @RequestMapping(value = "/addContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addContent(@RequestBody NewsAddParam trainContentParam) {
        int count = newsService.addContent(trainContentParam);
        if(count!=0){
            return CommonResult.success();
        }else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/updateContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateContent(@RequestBody NewsParam trainContentParam){
        int count=newsService.updateContent(trainContentParam);
        if(count!=0)
            return CommonResult.success();
        else
            return CommonResult.failed();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        //删除培训信息
        int count=newsService.delete(id);
        if(count<0)
            return CommonResult.failed("删除培训信息错误");
        return CommonResult.success();
    }

    @RequestMapping(value = "/handleBatchDelete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody Long[] multipleSelectionId) {
        List<Long> idList= Arrays.stream(multipleSelectionId).collect(Collectors.toList());
        //删除多个培训信息
        int count=newsService.deleteBatchNews(idList);
        if(count<0)
            return CommonResult.failed("删除培训信息错误");
        return CommonResult.success();
    }

}
