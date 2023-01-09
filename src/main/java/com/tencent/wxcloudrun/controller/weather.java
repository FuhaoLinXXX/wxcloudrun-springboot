package com.tencent.wxcloudrun.controller;


import com.alibaba.fastjson.JSON;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.OBJ;
import com.tencent.wxcloudrun.model.SendTypeRequest;
import com.tencent.wxcloudrun.service.SimpleWeather;
import com.tencent.wxcloudrun.service.impl.WxChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

@RestController
@EnableScheduling
public class weather {


    @Resource
    private SimpleWeather simpleWeather;

//    @Scheduled(cron = "0/2 * * * * ?")
//    @GetMapping(value = "/api/weather")
//    ApiResponse get() {
//        OBJ obj = simpleWeather.queryWeather();
//        String s = JSON.toJSONString(obj);
//        System.out.println(s);
//        return ApiResponse.ok(s);
//    }

    //上下文，用于策略模式获取对应策略
    @Autowired
    private ApplicationContext applicationContext;

    //表示每个月星期一到星期五下午4点50分执行
    @Scheduled(cron = "0 50 16 ? * MON-FRI")
    public void sendOffWork() throws ExecutionException, InterruptedException {
        System.out.println("开始发送下班提醒");
        //参数一发送类型，参数二是推送的对象OpenId
        SendTypeRequest sendTypeRequest = new SendTypeRequest("OffWorkSend","oFgYz6QOgko2Dx1tPTIkNc8KJTvM");
        WxChatService chatService = applicationContext.getBean(sendTypeRequest.getType(),WxChatService.class);
        chatService.sendTest(sendTypeRequest);
    }

}
