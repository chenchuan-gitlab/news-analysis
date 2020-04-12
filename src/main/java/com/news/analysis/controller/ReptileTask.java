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

    @Scheduled(cron = "0 */10 * * * ?")
    public void task() {
        logger.error("对接开始");
        try {
            // 先获取新闻页面链接
            List<Map<String, String>> urlList = analysisService.getUrlList();
            for (Map<String, String> map : urlList) {
                String siteName = map.get("siteName");
                String newsType = map.get("newsType");
                String newsUrl = map.get("newsUrl");
                String time = map.get("time");
                IndexEnum[] indexEnums = IndexEnum.values();
                getData(siteName, newsType, newsUrl, time, indexEnums);
            }
            logger.error("对接完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getData(String siteName, String newsType, String newsUrl, String time, IndexEnum[] indexEnums) throws Exception {
        for (IndexEnum indexEnum : indexEnums) {
            String index = indexEnum.getValue();
            String finalUrl = newsUrl + index;
            String html = WebPageDownLoadUtil.getHtmlSource(finalUrl, "gb2312");
            List<ParserResultEntity> resultList = HtmlParserManager.parseHtml(html, time, siteName, newsType);
            if(resultList != null && resultList.size() > 0){
                analysisService.saveData(resultList);
            }
        }
    }
}
