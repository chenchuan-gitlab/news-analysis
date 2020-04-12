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
    List<Map> getDataList();

    List<Map>  getTodayTotal(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Map<String, Object>> getMonthTotal(@Param("startTime")String startTime, @Param("endTime")String endTime);
}
