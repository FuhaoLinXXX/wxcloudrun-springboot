package com.tencent.wxcloudrun.model;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "weather.lfh")
public class YmlProperties {


    private String API_URL;

    private String API_KEY;

    private String CITY_NAME;

    private String APP_ID;

    private String SECRET;

    private String TOKEN;

    private String AES_KEY;
}
