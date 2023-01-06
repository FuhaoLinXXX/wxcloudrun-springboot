package com.tencent.wxcloudrun.model;

import java.util.List;

public class OBJ {


    private Weather Weather;
    private List<Future> futures;

    public OBJ() {
    }

    public Weather getWeather() {
        return Weather;
    }

    public void setWeather(Weather weather) {
        Weather = weather;
    }

    public List<Future> getFutures() {
        return futures;
    }

    public void setFutures(List<Future> futures) {
        this.futures = futures;
    }
}
