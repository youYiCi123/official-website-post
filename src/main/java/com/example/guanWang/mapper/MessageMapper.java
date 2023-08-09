package com.example.guanWang.mapper;

import com.example.guanWang.model.Message;
import com.example.guanWang.model.NewsBrieflyInitial;

import java.util.List;

public interface MessageMapper {

    int add(Message message);

    List<Message> getMessageByDateAndKeyword(String startDate, String endDate, String keyword);

    int delete(Long messageId);

    int deleteBatchMessage(List<Long> idList);
}
