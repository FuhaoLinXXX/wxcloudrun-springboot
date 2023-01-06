package com.tencent.wxcloudrun.model;

public class Weather {

    /**
     *天气情况，如：晴、多云
     */
    private String info;
    /**
     *天气标识id，可参考小接口2
     */
    private String wid;
    /**
     *温度，可能为空
     */
    private String temperature;

    /**
     *湿度，可能为空
     */
    private String humidity;
    /**
     *风向，可能为空
     */
    private String direct;
    /**
     *风力，可能为空
     */
    private String power;
    /**
     *空气质量指数，可能为空
     */
    private String aqi;



    public Weather() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

}
