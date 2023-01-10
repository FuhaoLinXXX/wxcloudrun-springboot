package com.tencent.wxcloudrun.service;

import com.tencent.wxcloudrun.model.Future;
import com.tencent.wxcloudrun.model.OBJ;
import com.tencent.wxcloudrun.model.SendTypeRequest;
import com.tencent.wxcloudrun.model.WeChatTemplateMsg;
import com.tencent.wxcloudrun.service.impl.WxChatService;
import com.tencent.wxcloudrun.util.WxSendMessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Service("OffWorkSend")
public class OffWorkSend implements WxChatService {
    @Autowired
    protected WxSendMessageUtils wxSendMessageUtils;

    @Autowired
    protected SimpleWeather simpleWeather;

    @Override
    public void sendTest(SendTypeRequest send) throws ExecutionException, InterruptedException {

        // 模板Id
        String templateId = "aBJIELFZuHArbyreg00eLIgov3KiufdyAT6IhOKZvKI";
        // 模板参数
        Map<String, WeChatTemplateMsg> sendMag = new HashMap<String, WeChatTemplateMsg>();
        OBJ obj = simpleWeather.queryWeather();
        sendMag.put("info", new WeChatTemplateMsg(obj.getWeather().getInfo(), "#b89485"));
        sendMag.put("temperature", new WeChatTemplateMsg(obj.getWeather().getTemperature(), "#b89485"));
        sendMag.put("humidity", new WeChatTemplateMsg(obj.getWeather().getHumidity(), "#b89485"));
        sendMag.put("direct", new WeChatTemplateMsg(obj.getWeather().getDirect(), "#b89485"));
        sendMag.put("power", new WeChatTemplateMsg(obj.getWeather().getPower(), "#b89485"));
        sendMag.put("aqi", new WeChatTemplateMsg(obj.getWeather().getAqi(), "#b89485"));
        Future future = obj.getFutures().get(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String data = sdf.format(future.getDate());
        sendMag.put("date", new WeChatTemplateMsg(data, "#FFC0CB"));
        sendMag.put("FutureTemperature", new WeChatTemplateMsg(future.getTemperature(), "#FFC0CB"));
        sendMag.put("FutureWeather", new WeChatTemplateMsg(future.getWeather(), "#FFC0CB"));
        sendMag.put("FutureDirect", new WeChatTemplateMsg(future.getDirect(), "#FFC0CB"));


        // 发送
        List<String> openId = send.getOpenId();
        for (int i = 0; i < openId.size(); i++) {
            wxSendMessageUtils.send(openId.get(i), templateId, sendMag);
        }
    }
}
