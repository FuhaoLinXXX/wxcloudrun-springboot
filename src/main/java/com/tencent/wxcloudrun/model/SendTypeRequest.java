package com.tencent.wxcloudrun.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SendTypeRequest {

    /**
     * 推送类型
     * */
    private String type;

    /**
     * 接收人的openId
     * */
    private List<String> openId;

}
