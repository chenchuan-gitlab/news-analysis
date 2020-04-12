package com.news.analysis.controller.api;

import cn.hutool.core.map.MapUtil;
import com.news.analysis.service.api.UserService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.ResponseBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController extends ResponseBuilder {
    private Logger logger = Logger.getLogger(UserController.class);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/regist.action",method = RequestMethod.POST)
    public Object regist(String userName, String password,String phone,String sex,int age) {
        try {
            Map userInfo = userService.getUserInfo(userName);
            if (userInfo != null && !userInfo.isEmpty()) {
                Map resMap = new HashMap(1);
                resMap.put("msg", "用户名不能重复");
                return ResponseBuilder.custom().faild().data(resMap);
            } else {
                // md5 hash
                String md5Pass = DigestUtils.md5Hex(password);
                // encoder加密
                String securityPassword = passwordEncoder.encode(md5Pass);
                Map user = new HashMap(3);
                user.put("userName", userName);
                user.put("password", securityPassword);
                user.put("phone",phone);
                user.put("sex",sex);
                user.put("age",age);
                user.put("time", DateUtil.getDate_yMdhms());
                userService.saveUser(user);
                return ResponseBuilder.custom().success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("注册异常");
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping("/login.action")
    public Object login(String userName, String password) {
        try {
            Map userInfo = userService.getUserInfo(userName);
            String securityPass = MapUtil.getStr(userInfo, "password");
            boolean flag = passwordEncoder.matches(password, securityPass);
            Map resMap = new HashMap(2);
            if (flag) {
                resMap.put("mes", "登录成功");
                resMap.put("userInfo",userInfo);
                return ResponseBuilder.custom().success().data(resMap);
            } else {
                resMap.put("mes", "登陆失败");
                return ResponseBuilder.custom().faild().data(resMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("登录异常");
            return ResponseBuilder.custom().faild("登陆异常", 0);
        }
    }

}
