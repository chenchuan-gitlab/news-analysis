package com.news.analysis.service.impl;

import com.news.analysis.dao.OutRecordMapper;
import com.news.analysis.dao.StockInformationMapper;
import com.news.analysis.pojo.OutRecord;
import com.news.analysis.service.OutRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class OutRecordServiceImpl implements OutRecordService {

    @Autowired
    private OutRecordMapper outRecordMapper;
    @Autowired
    private StockInformationMapper stockInformationMapper;

    @Override
    public OutRecord getOutRecordByID(String out_number) {
        return outRecordMapper.getOutRecordByID(out_number);
    }

    @Override
    @Transactional
    public int createOutRecord(OutRecord outRecord) {
        int inventory_quantity = outRecord.getQuantity();
        String production_batch = outRecord.getProduction_batch();
        int quantity = stockInformationMapper.getQuantity(production_batch);
        inventory_quantity = quantity - inventory_quantity;
        int i = outRecordMapper.createOutRecord(outRecord);
        int j = stockInformationMapper.updateQuantity(production_batch, inventory_quantity);
        if (i != 0 && j != 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<OutRecord> getOutRecords(Map<String, Object> paramsMap) {
        return outRecordMapper.getOutRecords(paramsMap);
    }
}
