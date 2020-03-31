package com.news.analysis.service;

import com.news.analysis.pojo.OutRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by chenchuan on 2019-04-17.
 */
public interface OutRecordService {
    OutRecord getOutRecordByID(String out_number);

    int createOutRecord(OutRecord outRecord);

    List<OutRecord> getOutRecords(Map<String,Object> paramsMap);
}
