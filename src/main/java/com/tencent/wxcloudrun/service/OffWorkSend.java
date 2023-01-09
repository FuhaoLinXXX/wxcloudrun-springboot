package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.OBJ;
import com.tencent.wxcloudrun.model.SendTypeRequest;
import com.tencent.wxcloudrun.model.WeChatTemplateMsg;
import com.tencent.wxcloudrun.service.impl.WxChatService;
import com.tencent.wxcloudrun.util.WxSendMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class OffWorkSend implements WxChatService {
    @Autowired
    protected WxSendMessageUtils wxSendMessageUtils;

    @Autowired
    protected SimpleWeather simpleWeather;
    @Override
    public void sendTest(SendTypeRequest send) throws ExecutionException, InterruptedException {

        // 模板Id
        String templateId = "WiOdyauoRf28_tOsTpxcA1U4JthTqedkOB_Hc1BtKps";
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        OBJ obj = simpleWeather.queryWeather();
        sendMag.put("info",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
        sendMag.put("temperature",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
        sendMag.put("humidity",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
        sendMag.put("direct",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
        sendMag.put("power",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
        sendMag.put("aqi",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
//        sendMag.put("date",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
//        sendMag.put("temperature",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
//        sendMag.put("weather",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));
//        sendMag.put("direct",new WeChatTemplateMsg(obj.getWeather().getInfo(),"#b89485"));

        // 发送
        wxSendMessageUtils.send(send.getOpenId(), templateId, sendMag);
        
    }
}
