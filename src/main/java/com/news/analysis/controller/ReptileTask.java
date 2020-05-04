package com.news.analysis.controller;

import com.news.analysis.pojo.ParserResultEntity;
import com.news.analysis.service.AnalysisService;
import com.news.analysis.utils.IndexEnum;
import com.news.analysis.utils.webpage.HtmlParserManager;
import com.news.analysis.utils.webpage.WebPageDownLoadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * ReptileTask
 */
@Component
public class ReptileTask {

    private Logger logger = LoggerFactory.getLogger(ReptileTask.class);

    @Autowired
    private AnalysisService analysisService;

    @Scheduled(cron = "0 */30 * * * ?")
    public void task() {
        logger.error("对接开始");
        try {
            logger.error("中国青年网对接开始");
            // 获取中国青年网页面链接
            List<Map<String, String>> youthList = analysisService.getUrlList("中国青年网");
            for (Map<String, String> map : youthList) {
                String siteName = map.get("siteName");
                String newsType = map.get("newsType");
                String newsUrl = map.get("newsUrl");
                String time = map.get("time");
                IndexEnum[] indexEnums = IndexEnum.values();
                getYouthData(siteName, newsType, newsUrl, time, indexEnums);
            }
            logger.error("中国青年网对接完成");
            logger.error("中华网对接开始");
            // 获取中华网网页面链接
            List<Map<String, String>> chinaNetList = analysisService.getUrlList("中华网");
            for (Map<String, String> map : chinaNetList) {
                String siteName = map.get("siteName");
                String newsType = map.get("newsType");
                String newsUrl = map.get("newsUrl");
                String time = map.get("time");
                getChinaNetData(siteName, newsType, newsUrl, time);
            }
            logger.error("中华网对接完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 对接中国青年网
    public void getYouthData(String siteName, String newsType, String newsUrl, String time, IndexEnum[] indexEnums) throws Exception {
        for (IndexEnum indexEnum : indexEnums) {
            String index = indexEnum.getValue();
            String finalUrl = newsUrl + index;
            String html = WebPageDownLoadUtil.getHtmlSource(finalUrl, "gb2312");
            List<ParserResultEntity> resultList = HtmlParserManager.parseYouthHtml(html, time, siteName, newsType);
            if (resultList != null && resultList.size() > 0) {
                analysisService.saveData(resultList);
            }
        }
    }

    // 对接中华网
    public void getChinaNetData(String siteName, String newsType, String newsUrl, String time) throws Exception {
        String html = WebPageDownLoadUtil.getHtmlSource(newsUrl, "utf-8");
        List<ParserResultEntity> resultList = HtmlParserManager.parseChinaNetHtml(html, time, siteName, newsType);
        if (resultList != null && resultList.size() > 0) {
            analysisService.saveData(resultList);
        }
    }
}
