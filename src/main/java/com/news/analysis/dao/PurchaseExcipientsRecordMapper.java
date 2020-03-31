package com.news.analysis.dao;

import com.news.analysis.pojo.PurchaseExcipientsRecord;

import java.util.List;
import java.util.Map;

public interface PurchaseExcipientsRecordMapper {
    PurchaseExcipientsRecord getRecordByBatch(String purchase_batch);

    int createRecord(PurchaseExcipientsRecord purchaseExcipientsRecord);

    List<PurchaseExcipientsRecord> getRecordList(Map<String,Object> paramsMap);

}
