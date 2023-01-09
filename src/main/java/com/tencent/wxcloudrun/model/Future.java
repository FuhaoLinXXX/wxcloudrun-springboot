package com.tencent.wxcloudrun.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
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
}
