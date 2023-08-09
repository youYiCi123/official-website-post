package com.example.guanWang.controller;

import com.example.guanWang.api.common.CommonPage;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.DepParam;
import com.example.guanWang.model.Dep;
import com.example.guanWang.model.DepIdToName;
import com.example.guanWang.model.DepUser;
import com.example.guanWang.model.depUserRelation;
import com.example.guanWang.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dep")
public class depController {

   @Autowired
    private DepService depService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Dep> register(@Validated @RequestBody DepParam depParam) {
        Dep dep = depService.add(depParam);
        if (dep == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(dep);
    }

    @RequestMapping(value = "/getDepByDeptId", method = RequestMethod.GET)
    @ResponseBody
    public Dep getDepByDeptId(Long deptId){
       Dep dep;
       dep = depService.getDepByDeptId(deptId);
       return dep;
    }


    @RequestMapping(value = "/allInfo", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<Dep>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Dep> depList = depService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(depList));
    }

    @RequestMapping(value = "/getDepIdToName", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<DepIdToName>> getDepIdToName(){
        return  CommonResult.success(depService.getDepIdToName());
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<DepUser>> details(@RequestParam(value = "deptId", required = false) Long deptId,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<DepUser> depUserList = depService.details(deptId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(depUserList));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody Dep dep) {
        int count = depService.update(id, dep);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        int count = depService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    //查询所有部门和人员信息级联关系
    @RequestMapping(value = "/selectUserRelation", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<depUserRelation>> selectUserRelation() {
           List<depUserRelation> depUserRelationList= depService.selectUserRelation();
            return CommonResult.success(depUserRelationList);
    }
}
