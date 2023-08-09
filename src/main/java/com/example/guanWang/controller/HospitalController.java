package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.model.Hospital;
import com.example.guanWang.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Hospital> register(@Validated @RequestBody Hospital hospital) {
        Hospital hospital1 = hospitalService.add(hospital);
        if (hospital1 == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(hospital1);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Hospital>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Hospital> hospitalList = hospitalService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(hospitalList));
    }

    @RequestMapping(value = "/cityHospitalList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Hospital>> cityHospitalList(@RequestParam(value = "city") String city,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Hospital> hospitalList = hospitalService.cityHospitalList(city, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(hospitalList));
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable String id, @RequestBody Hospital hospital) {
        int count = hospitalService.update(id, hospital);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable String id) {
        int count = hospitalService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
