package com.tencent.wxcloudrun.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatGetUserInfoController {

    @Autowired
    private WxMpService wxMpService;



    @GetMapping("/getUserInfo")
    public void getUserInfo(HttpServletResponse response) throws IOException {
        //构造网页授权url
        String redirect_uri= "http://springboot-xi3x-25530-6-1316441931.sh.run.tcloudbase.com/wechat/getRedirectUserInfo";//回调的url
        String url = wxMpService.oauth2buildAuthorizationUrl(redirect_uri, WxConsts.OAuth2Scope.SNSAPI_USERINFO, null);
        System.out.println("URL:" + url);
        response.sendRedirect(url);
//        return url;
    }

    /**
     * 网页授权
     * 1 第一步：用户同意授权，获取code
     * <p>
     * 2 第二步：通过code换取网页授权access_token
     * <p>
     * 3 第三步：刷新access_token（如果需要）
     * <p>
     * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
     *
     * @param code
     * @return
     */
    @GetMapping("/getRedirectUserInfo")
    @ResponseBody
    public String getRedirectUserInfo(String code) throws WxErrorException, IOException {
        //1 第一步：用户同意授权，获取code
        log.info("返回code: {}", code);
        //2 第二步：通过code换取网页授权access_token
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        //4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken,null);
        log.info("获取到的用户信息： {}", wxMpUser.toString());
        return wxMpUser.toString();
    }

}

