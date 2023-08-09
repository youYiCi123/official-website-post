package com.example.guanWang.controller;



import com.aliyuncs.exceptions.ClientException;
import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.OssCallbackResult;
import com.example.guanWang.dto.OssPolicyResult;
import com.example.guanWang.dto.OssTokenVO;
import com.example.guanWang.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理Controller
 * Created by macro on 2018/4/26.
 */
@Controller
@RequestMapping("/aliyun/oss")
public class OssController {
    @Autowired
    private OssService ossService;

//     "Oss上传签名生成"
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

//    "Oss上传成功回调"
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }

    @RequestMapping(value = "getStsToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<OssTokenVO> getToken() throws ClientException {
        return CommonResult.success(ossService.getToken());
    }
}
