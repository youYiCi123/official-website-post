package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.IntroParam;
import com.example.guanWang.service.IntroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/intro")
public class IntroController {

    @Autowired
    private IntroService introService;

    @RequestMapping(value = "/queryContent", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<IntroParam> queryProdContent(){
        return CommonResult.success(introService.queryHonorContent());
    }

    @RequestMapping(value = "/addContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult addContent(@RequestBody IntroParam introParam) {
        int count = introService.addContent(introParam);
        if(count!=0){
            return CommonResult.success();
        }else {
            return CommonResult.failed();
        }
    }

    @RequestMapping(value = "/updateContent", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateContent(@RequestBody IntroParam introParam){
        int count=introService.updateContent(introParam);
        if(count!=0)
            return CommonResult.success();
        else
            return CommonResult.failed();
    }


}
