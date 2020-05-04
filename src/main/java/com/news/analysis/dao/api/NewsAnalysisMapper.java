package com.news.analysis.dao.api;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * NewsAnalysisMapper
 */
@Repository
public interface NewsAnalysisMapper {
    List<Map> getDataList(@Param("siteName") String siteName);

    List<Map>  getTodayTotal(@Param("siteName") String siteName,@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getMonthTotal(@Param("siteName") String siteName,@Param("startTime")String startTime, @Param("endTime")String endTime);
}
