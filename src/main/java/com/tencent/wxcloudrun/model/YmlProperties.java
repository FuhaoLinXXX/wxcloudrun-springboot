package com.tencent.wxcloudrun.model;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weather.lfh")
public class YmlProperties {


    private String API_URL;

    private String API_KEY;

    private String CITY_NAME;

    @Override
    public String toString() {
        return "YmlProperties{" +
                "API_URL='" + API_URL + '\'' +
                ", API_KEY='" + API_KEY + '\'' +
                ", CITY_NAME='" + CITY_NAME + '\'' +
                '}';
    }

    public String getAPI_URL() {
        return API_URL;
    }

    public void setAPI_URL(String API_URL) {
        this.API_URL = API_URL;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getCITY_NAME() {
        return CITY_NAME;
    }

    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }

    public YmlProperties() {
    }
}
