package com.news.analysis.controller;

import com.news.analysis.pojo.User;
import com.news.analysis.pojo.WebsiteConfig;
import com.news.analysis.service.AdminUserService;
import com.news.analysis.service.NewsInfoService;
import com.news.analysis.service.WebsiteConfigService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.PageForm;
import com.news.analysis.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/newsInfo")
public class NewsInfoController {

    @Autowired
    private NewsInfoService newsInfoService;

    @RequestMapping(value = "/list.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserList(PageForm pageForm, String startTime, String endTime) {
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("startTime", startTime);
            paramsMap.put("endTime", endTime);
            pageForm = newsInfoService.getList(pageForm, paramsMap);
            return ResponseBuilder.custom().success().data(pageForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }

    @RequestMapping(value = "/getDataByID.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getDataByID(String id) {
        try {
            return ResponseBuilder.custom().success().data(newsInfoService.getDataByID(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }
}
