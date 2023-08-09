package com.example.guanWang.service;



import com.aliyuncs.exceptions.ClientException;
import com.example.guanWang.dto.OssCallbackResult;
import com.example.guanWang.dto.OssPolicyResult;
import com.example.guanWang.dto.OssTokenVO;

import javax.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理Service
 * Created by macro on 2018/5/17.
 */
public interface OssService {
    /**
     * Oss上传策略生成
     */
    OssPolicyResult policy();
    /**
     * Oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);

    OssTokenVO getToken() throws ClientException;
}
