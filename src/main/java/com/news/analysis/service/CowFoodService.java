package com.news.analysis.service;

import com.news.analysis.pojo.CowFood;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CowFoodService {
    CowFood getRecordByID(@Param("id") int id);

    int createRecord(CowFood cowFood);

    List<CowFood> getRecordList(Map<String,Object> paramsMap);

    int updateRecord(CowFood cowFood);
}
