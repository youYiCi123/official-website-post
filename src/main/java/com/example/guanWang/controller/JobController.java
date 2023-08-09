package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.dto.NewsParam;
import com.example.guanWang.model.*;
import com.example.guanWang.service.JobPostService;
import com.example.guanWang.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobPostService jobService;

    @RequestMapping(value = "/jobList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Job>> jobList() {
        List<Job> roleList = jobService.jobList();
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<JobTableShow>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<JobTableShow> roleList = jobService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = jobService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/deleteBatch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody Long[] multipleSelectionId) {
        List<Long> idList= Arrays.stream(multipleSelectionId).collect(Collectors.toList());
        int count = jobService.deleteBatch(idList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/queryContent/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Job> queryJobContent(@PathVariable Long id){
        return CommonResult.success(jobService.queryJobContent(id));
    }


    @RequestMapping(value = "/addContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addContent(@RequestBody Job job) {
        int count = jobService.addContent(job);
        if(count!=0){
            return CommonResult.success();
        }else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/updateContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateContent(@RequestBody Job job){
        int count=jobService.updateContent(job);
        if(count!=0)
            return CommonResult.success();
        else
            return CommonResult.failed();
    }
}
