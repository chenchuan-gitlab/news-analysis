package com.news.analysis.controller;

import com.news.analysis.pojo.WebsiteConfig;
import com.news.analysis.service.WebsiteConfigService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.PageForm;
import com.news.analysis.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/website")
public class WebsiteConfigController {

    @Autowired
    private WebsiteConfigService websiteConfigService;

    @RequestMapping(value = "/list.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserList(PageForm pageForm, String website, String startTime, String endTime) {
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("startTime", startTime);
            paramsMap.put("endTime", endTime);
            paramsMap.put("website", website);
            pageForm = websiteConfigService.getList(pageForm, paramsMap);
            return ResponseBuilder.custom().success().data(pageForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }

    @RequestMapping(value = "/getDataByID.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserByID(String id) {
        try {
            return ResponseBuilder.custom().success().data(websiteConfigService.getDataByID(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }

    @RequestMapping(value = "/add.action", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody WebsiteConfig websiteConfig) {
        try {
            websiteConfig.setStatus(1);
            websiteConfig.setTime(DateUtil.getDate_yMdhms());
            return ResponseBuilder.custom().success("success", websiteConfigService.add(websiteConfig));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/delete.action")
    @ResponseBody
    public Object delete(String id) {
        try {
            websiteConfigService.delete(id);
            return ResponseBuilder.custom().success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/update.action", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUser(@RequestBody WebsiteConfig websiteConfig) {
        try {
            return ResponseBuilder.custom().success("success", websiteConfigService.update(websiteConfig));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/updateStatus.action")
    @ResponseBody
    public Object updateStatus(String id, int status) {
        try {
            WebsiteConfig websiteConfig = new WebsiteConfig();
            status = status == 0 ? 1 : 0;
            websiteConfig.setId(Long.valueOf(id));
            websiteConfig.setStatus(status);
            return ResponseBuilder.custom().success("success", websiteConfigService.update(websiteConfig));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }
}
