package com.example.guanWang.service;

import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.dto.NewsAddParam;
import com.example.guanWang.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getMessageByDateAndKeyword(String startDate, String endDate, String keyword);

    int delete(Long messageId);

    int deleteBatchMessage(List<Long> idList);

    CommonResult addContent(Message message);
}
