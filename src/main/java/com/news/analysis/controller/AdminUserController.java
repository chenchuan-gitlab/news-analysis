package com.news.analysis.controller;

import com.news.analysis.service.AdminUserService;
import com.news.analysis.utils.PageForm;
import com.news.analysis.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class AdminUserController {

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
}
