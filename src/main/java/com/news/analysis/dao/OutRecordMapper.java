package com.news.analysis.dao;

import com.news.analysis.pojo.OutRecord;

import java.util.List;
import java.util.Map;

public interface OutRecordMapper {
    OutRecord getOutRecordByID(String out_number);

    int createOutRecord(OutRecord outRecord);

    List<OutRecord> getOutRecords(Map<String,Object> paramsMap);

}
