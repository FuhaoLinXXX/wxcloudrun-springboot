package com.tencent.wxcloudrun.model;

import java.util.Date;

public class Future {
    /**
     * 日期
     */
    private Date date;
    /**
     * 温度，最低温/最高温
     */
    private String temperature;
    /**
     * 天气情况
     */
    private String weather;

    /**
     * 风向
     */
    private String direct;

    public Future() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
}
