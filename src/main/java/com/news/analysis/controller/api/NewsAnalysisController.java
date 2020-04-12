package com.news.analysis.controller.api;

import com.news.analysis.service.api.NewsAnalysisService;
import com.news.analysis.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Object getTotal(){
        try {
            Map data = newsAnalysisService.getData();
            Map dataSet = new HashMap(2);
            dataSet.put("dataset",data);
            return ResponseBuilder.custom().success().data(dataSet);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping("/getTodayTotal.action")
    public Object getTodayTotal(){
        try {
            Map data = newsAnalysisService.getTodayTotal();
            Map dataSet = new HashMap(2);
            dataSet.put("dataset",data);
            return ResponseBuilder.custom().success().data(dataSet);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping("/getMonthTotal.action")
    public Object getMonthTotal(String month){
        try {
            Map data = newsAnalysisService.getMonthTotal(month);
            Map dataSet = new HashMap(2);
            dataSet.put("dataset",data);
            return ResponseBuilder.custom().success().data(dataSet);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }
}
