package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.model.ServiceIdea;
import com.example.guanWang.service.ServiceIdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/serviceIdea")
public class ServiceIdeaController {

    @Autowired
    private ServiceIdeaService serviceIdeaService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<ServiceIdea>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<ServiceIdea> roleList = serviceIdeaService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<ServiceIdea>> listAll() {
        List<ServiceIdea> seriesList = serviceIdeaService.listAll();
        return CommonResult.success(seriesList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody ServiceIdea serviceIdea) {
        int count = serviceIdeaService.create(serviceIdea);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody ServiceIdea serviceIdea) {
        int count = serviceIdeaService.update(id, serviceIdea);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = serviceIdeaService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

}
