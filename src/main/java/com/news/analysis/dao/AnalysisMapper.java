package com.news.analysis.dao;

import com.news.analysis.pojo.ParserResultEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * AnalysisMapper
 */
@Repository
public interface AnalysisMapper {
    List<Map<String,String>> getUrlList(@Param("siteName") String siteName);

    void saveData(@Param("list") List<ParserResultEntity> resultList);
}
