package com.news.analysis.service;

import com.news.analysis.pojo.CowVaccination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CowVaccinationService {
    CowVaccination getRecordByID(@Param("id") int id);

    int createRecord(CowVaccination cowVaccination);

    List<CowVaccination> getRecordList(Map<String,Object> paramsMap);

    int updateRecord(CowVaccination cowVaccination);
}
