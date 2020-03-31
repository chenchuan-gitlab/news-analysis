package com.news.analysis.service.impl;

import com.news.analysis.dao.PurchaseExcipientsRecordMapper;
import com.news.analysis.pojo.PurchaseExcipientsRecord;
import com.news.analysis.service.PurchaseExcipientsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PurchaseExcipientsRecordServiceImpl implements PurchaseExcipientsRecordService {

    @Autowired
    private PurchaseExcipientsRecordMapper purchaseExcipientsRecordMapper;

    @Override
    public PurchaseExcipientsRecord getRecordByBatch(String purchase_batch) {
        return purchaseExcipientsRecordMapper.getRecordByBatch(purchase_batch);
    }

    @Override
    public int createRecord(PurchaseExcipientsRecord purchaseExcipientsRecord) {
        return purchaseExcipientsRecordMapper.createRecord(purchaseExcipientsRecord);
    }

    @Override
    public List<PurchaseExcipientsRecord> getRecordList(Map<String, Object> paramsMap) {
        return purchaseExcipientsRecordMapper.getRecordList(paramsMap);
    }
}
