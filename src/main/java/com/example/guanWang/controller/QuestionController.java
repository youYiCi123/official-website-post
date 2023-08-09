package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.ProdParam;
import com.example.guanWang.model.Question;
import com.example.guanWang.model.QuestionHot;
import com.example.guanWang.model.SolutionSeriesModel;
import com.example.guanWang.service.QuestionService;
import com.example.guanWang.service.SolutionSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Question>> list( @RequestParam(value = "solutionSeries", required = false) String solutionSeries,
                                                    @RequestParam(value = "keyWord", required = false) String keyWord,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Question> roleList = questionService.list(solutionSeries,keyWord, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    //官网首页面根据一级菜单获取前游览量高的四体据
    @RequestMapping(value = "/getListByFirstLevel", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Question>> getListByFirstLevel(
                                                    @RequestParam(value = "firstLevel", required = false) String firstLevel,
                                                    @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Question> roleList = questionService.getListByFirstLevel(firstLevel, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    //点击增加游览次数
    @RequestMapping(value = "/addQuestionView/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult addQuestionView(@PathVariable Long id) {
        int count = questionService.addQuestionView(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    //获取热门信息
    @RequestMapping(value = "/getQuestionHot", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<QuestionHot>> getQuestionHot(
            @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<QuestionHot> questionHots = questionService.getQuestionHot(pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(questionHots));
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody Question question) {
        int count = questionService.create(question);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/getQuestionListByType", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Question>> getQuestionListByType(@RequestParam(value = "combineId") String combineId) {
        return CommonResult.success(CommonPage.restPage(questionService.getQuestionListByType(combineId)));
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@RequestBody Question question) {
        int count = questionService.update(question);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = questionService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}
