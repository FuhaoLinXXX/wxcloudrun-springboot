package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.model.SendTypeRequest;

import java.util.concurrent.ExecutionException;

public interface WxChatService {

    void sendTest(SendTypeRequest send) throws ExecutionException, InterruptedException;
}
