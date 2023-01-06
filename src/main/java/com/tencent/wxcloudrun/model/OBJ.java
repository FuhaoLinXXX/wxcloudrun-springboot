package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.util.List;
@Data
public class OBJ {


    private Weather Weather;
    private List<Future> futures;

}
