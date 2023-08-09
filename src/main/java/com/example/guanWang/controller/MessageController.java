package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.api.common.StringUtils;
import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.model.Message;
import com.example.guanWang.model.NewsBrieflyInitial;
import com.example.guanWang.service.MessageService;
import com.example.guanWang.service.NewsService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/getAllMessage", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAllMessage(@RequestParam(defaultValue = "") String[] date,
                                   @RequestParam(value = "keyword", required = false) String keyword,
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
        List<Message> NewsBrieflyList=messageService.getMessageByDateAndKeyword(startDate, endDate,keyword);
        return CommonResult.success(CommonPage.restPage(NewsBrieflyList),"请求成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        //删除培训信息
        int count=messageService.delete(id);
        if(count<0)
            return CommonResult.failed("删除培训信息错误");
        return CommonResult.success();
    }

    @RequestMapping(value = "/handleBatchDelete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteBatch(@RequestBody Long[] multipleSelectionId) {
        List<Long> idList= Arrays.stream(multipleSelectionId).collect(Collectors.toList());
        //删除多个培训信息
        int count=messageService.deleteBatchMessage(idList);
        if(count<0)
            return CommonResult.failed("删除培训信息错误");
        return CommonResult.success();
    }

    @RequestMapping(value = "/addContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addContent(@RequestBody Message message) {
        return messageService.addContent(message);
    }

}
