package com.news.analysis.controller;

import com.news.analysis.pojo.User;
import com.news.analysis.service.AdminUserService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.PageForm;
import com.news.analysis.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class AdminUserController {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/list.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserList(PageForm pageForm, String userName, String startTime, String endTime) {
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("userName", userName);
            paramsMap.put("startTime", startTime);
            paramsMap.put("endTime", endTime);
            pageForm = adminUserService.getUserList(pageForm, paramsMap);
            return ResponseBuilder.custom().success().data(pageForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }

    @RequestMapping(value = "/getUserByID.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByID(String id) {
        try {
            return ResponseBuilder.custom().success().data(adminUserService.getUserByID(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }

    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody User user) {
        try {
            String pass = user.getPassword();
            // 密码加密存数据库
            user.setPassword(passwordEncoder.encode(pass));
            user.setStatus(1);
            user.setTime(DateUtil.getDate_yMdhms());
            return ResponseBuilder.custom().success("success", adminUserService.addUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/delete.action")
    @ResponseBody
    public Object delete(String id) {
        try {
            adminUserService.delete(id);
            return ResponseBuilder.custom().success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/updateUser.action",method = RequestMethod.POST)
    @ResponseBody
    public Object updateUser(@RequestBody User user) {
        try {
            return ResponseBuilder.custom().success("success", adminUserService.updateUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/updateStatus.action")
    @ResponseBody
    public Object updateStatus(String id, int status) {
        try {
            User user = new User();
            status = status == 0 ? 1 : 0;
            user.setId(Long.valueOf(id));
            user.setStatus(status);
            return ResponseBuilder.custom().success("success", adminUserService.updateUser(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }
}
