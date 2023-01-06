package com.tencent.wxcloudrun.controller;


import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.OBJ;
import com.tencent.wxcloudrun.service.SimpleWeather;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableScheduling
public class weather {


    @Resource
    private SimpleWeather simpleWeather;

    @Scheduled(cron = "0/2 * * * * ?")
    @GetMapping(value = "/api/weather")
    ApiResponse get() {
        //OBJ obj = simpleWeather.queryWeather();
        System.out.println("aaaaa");
        return ApiResponse.ok();
    }


}
