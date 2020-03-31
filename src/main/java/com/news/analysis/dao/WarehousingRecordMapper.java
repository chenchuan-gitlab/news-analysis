package com.news.analysis.dao;

import com.news.analysis.pojo.WarehousingRecord;

import java.util.List;
import java.util.Map;

public interface WarehousingRecordMapper {
    WarehousingRecord getWarehousingRecordByBatch(String warehousing_batch);

    int createWarehousingRecord(WarehousingRecord warehousingRecord);

    List<WarehousingRecord> getWarehousingRecordList(Map<String,Object> paramsMap);
}
