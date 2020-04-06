package com.news.analysis.controller;


import cn.hutool.core.map.MapUtil;
import com.news.analysis.service.api.UserService;
import com.news.analysis.utils.ResponseBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private MD5 md5 = new MD5();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.action", method = RequestMethod.GET)
    @ResponseBody
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            if ("admin".equals(username) && DigestUtils.md5Hex("12345678").equals(password)) {
                return ResponseBuilder.custom().success();
            } else {
                Map userInfo = userService.getUserInfo(username);
                String securityPass = MapUtil.getStr(userInfo, "password");
                boolean flag = passwordEncoder.matches(password, securityPass);
                if (flag) {
                    if (MapUtil.getInt(userInfo, "type") == 0) {
                        return ResponseBuilder.custom().faild("该用户不是管理员，无法登录", 0);
                    }
                    return ResponseBuilder.custom().success();
                } else {
                    return ResponseBuilder.custom().faild("用户名或密码错误", 0);
                }
            }
        } catch (Exception e) {
            return ResponseBuilder.custom().faild("请求异常", 0);
        }
    }
}
