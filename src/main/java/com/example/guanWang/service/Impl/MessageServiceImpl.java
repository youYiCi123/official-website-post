package com.example.guanWang.service.Impl;

import com.example.guanWang.api.common.CommonResult;
import com.example.guanWang.api.common.StringUtils;
import com.example.guanWang.mapper.MessageMapper;
import com.example.guanWang.mapper.NewsMapper;
import com.example.guanWang.model.Message;
import com.example.guanWang.service.MessageService;
import com.example.guanWang.service.RedisService;
import net.dreamlu.mica.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getMessageByDateAndKeyword(String startDate, String endDate, String keyword) {
        return messageMapper.getMessageByDateAndKeyword(startDate,  endDate,  keyword);
    }

    @Override
    public int delete(Long messageId) {
        return messageMapper.delete(messageId);
    }

    @Override
    public int deleteBatchMessage(List<Long> idList) {
        return messageMapper.deleteBatchMessage(idList);
    }

    @Override
    public CommonResult addContent(Message message) {
        //获取ip地址，要求同一个ip下只能在同一天下发送1次
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = StringUtils.getIp(request);
        // 保存
        if(!redisService.sIsMember("messageIp",ip)){
            redisService.sAdd("messageIp", (long) 60 * 60*1, ip);
        }else{
            return  CommonResult.failed("同一个用户一小时只能发送一次信息");
        }
        message.setId(StringUtil.getUUID());
        message.setCreatedTime(new Date());
        int count= messageMapper.add(message);
        if(count>0){
            return CommonResult.success("发送成功");
        }else{
            return CommonResult.success("发送失败");
        }
    }
}
