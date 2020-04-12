package com.news.analysis.service;

import com.news.analysis.dao.AnalysisMapper;
import com.news.analysis.pojo.ParserResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * AnalysisService
 */
@Service
public class AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;

    public List<Map<String, String>> getUrlList() {
        return analysisMapper.getUrlList();
    }

    public void saveData(List<ParserResultEntity> resultList) {
        analysisMapper.saveData(resultList);
    }
}
