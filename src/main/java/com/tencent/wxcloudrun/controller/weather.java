package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.OBJ;
import com.tencent.wxcloudrun.model.SendTypeRequest;
import com.tencent.wxcloudrun.service.SimpleWeather;
import com.tencent.wxcloudrun.service.impl.WxChatService;
import com.tencent.wxcloudrun.util.WeChetAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@EnableScheduling
public class weather {


    //上下文，用于策略模式获取对应策略
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    protected RestTemplate restTemplate;
    //表示每个月星期一到星期五下午4点50分执行
    @Scheduled(cron = "0 05 15 ? * MON-FRI")
    @GetMapping("/get")
    public void sendOffWork() throws ExecutionException, InterruptedException {
        System.out.println("开始发送");
        //参数一发送类型，参数二是推送的对象OpenId
        List<String> list = new ArrayList<>();
        list.add("oFgYz6QOgko2Dx1tPTIkNc8KJTvM");
        list.add("oFgYz6T4HJimti7Im0KnHLaK8s5Q");
        SendTypeRequest sendTypeRequest = new SendTypeRequest("OffWorkSend",list);
        WxChatService chatService = applicationContext.getBean(sendTypeRequest.getType(),WxChatService.class);
        chatService.sendTest(sendTypeRequest);
    }

}
