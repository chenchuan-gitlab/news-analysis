package com.news.analysis.controller.api;

import cn.hutool.core.util.StrUtil;
import com.news.analysis.service.api.NewsAnalysisService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Struct;
import java.util.HashMap;
import java.util.Map;

/**
 * NewsAnalysisController
 */
@RestController
@RequestMapping("/api/analysis")
@CrossOrigin
public class NewsAnalysisController {
    @Autowired
    private NewsAnalysisService newsAnalysisService;

    @RequestMapping("/getTotal.action")
    public Object getTotal(String siteName) {
        try {
            if (StrUtil.isBlank(siteName)) {
                siteName = "中国青年网";
            }
            Map data = newsAnalysisService.getData(siteName);
            Map dataSet = new HashMap(2);
            dataSet.put("dataset", data);
            return ResponseBuilder.custom().success().data(dataSet);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping("/getTodayTotal.action")
    public Object getTodayTotal(String siteName, String date) {
        try {
            if (StrUtil.isBlank(siteName)) {
                siteName = "中国青年网";
            }
            if (StrUtil.isBlank(date)) {
                date = DateUtil.getDate_yMd();
            }
            Map data = newsAnalysisService.getTodayTotal(siteName, date);
            Map dataSet = new HashMap(2);
            dataSet.put("dataset", data);
            return ResponseBuilder.custom().success().data(dataSet);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping("/getMonthTotal.action")
    public Object getMonthTotal(String siteName, String month) {
        try {
            if (StrUtil.isBlank(siteName)) {
                siteName = "中国青年网";
            }
            Map data = newsAnalysisService.getMonthTotal(siteName, month);
            Map dataSet = new HashMap(2);
            dataSet.put("dataset", data);
            return ResponseBuilder.custom().success().data(dataSet);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }
}
