package com.tencent.wxcloudrun.controller;

import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucky winner
 * 微信服务器token验证
 */
@RestController
@RequestMapping("/wechat")
public class WechatCertificationTokenController {

    @Autowired
    private WxMpService wxMpService;

    /**
     * 接口配置信息
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping("/login")
    public String login(String signature, String timestamp, String nonce, String echostr) {
        /*
         * 注意这里的参数配置顺序
         * timestamp, nonce, signature
         * 检查签名
         * */
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            return null;
        }
        return echostr;
    }



}