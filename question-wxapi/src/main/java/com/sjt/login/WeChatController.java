package com.sjt.login;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.sjt.utils.ResultUtils;
import com.sjt.utils.ResultVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 小程序登录的控制器
 * @author Rocky
 * @create 2022-02-1111:10
 */
//Appid:wx95d7f05dc9c93bcf
//密钥:6377247ed136f058f95cf8f929f4082a
@RestController
@RequestMapping("/wxapi/weChat")
public class WeChatController {
    @RequestMapping("/wxLogin")
    public ResultVo wxLogin(@RequestParam("code") String code){
        Map<String, String> map = new HashMap<>();
        map.put("appid","wx95d7f05dc9c93bcf");
        map.put("secret","6377247ed136f058f95cf8f929f4082a");
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        //发送请求到微信接口服务获取openid
        String body = HttpRequest.get("https://api.weixin.qq.com/sns/jscode2session").form(map).body();
        JSONObject object = JSON.parseObject(body);
        return ResultUtils.success("获取成功", object);
    }
}
