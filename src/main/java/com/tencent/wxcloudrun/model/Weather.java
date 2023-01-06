package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
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

}
