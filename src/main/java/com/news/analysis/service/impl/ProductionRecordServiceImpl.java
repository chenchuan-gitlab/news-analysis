package com.news.analysis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.news.analysis.dao.ProductionRecordMapper;
import com.news.analysis.service.ProductionRecordService;
import com.news.analysis.dao.ExcipientsPutRecordMapper;
import com.news.analysis.pojo.ExcipientsPutRecord;
import com.news.analysis.pojo.ProductionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductionRecordServiceImpl implements ProductionRecordService {

    @Autowired
    private ProductionRecordMapper productionRecordMapper;
    @Autowired
    private ExcipientsPutRecordMapper excipientsPutRecordMapper;

    @Override
    public ProductionRecord getProductionRecordByBatch(String production_batch) {
        return productionRecordMapper.getProductionRecordByBatch(production_batch);
    }

    @Override
    @Transactional
    public int creatProductionRecord(ProductionRecord productionRecord, String exPutRecord) {
        String production_batch = productionRecord.getProduction_batch();
        int a = productionRecordMapper.creatProductionRecord(productionRecord);
        List<ExcipientsPutRecord> recordList = new ArrayList<>();
        ExcipientsPutRecord excipientsPutRecord = null;
        System.out.println(exPutRecord);
        if (exPutRecord != null) {
            JSONArray jsonArray = JSON.parseArray(exPutRecord);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String excipients_name = object.getString("excipients_name");
                double feed_quantity = Double.valueOf(object.getString("feed_quantity"));
                excipientsPutRecord = new ExcipientsPutRecord(0,production_batch,excipients_name,feed_quantity);
                recordList.add(excipientsPutRecord);
            }
            a = excipientsPutRecordMapper.createRecord(recordList);
        }
        return a;
    }

    @Override
    public String getProductionDate(String production_batch) {
        return productionRecordMapper.getProductionDate(production_batch);
    }

    @Override
    public List<ProductionRecord> getProductionRecords(Map<String, Object> paramsMap) {
        return productionRecordMapper.getProductionRecords(paramsMap);
    }

    @Override
    public List<String> getBatch(String material_batch) {
        return productionRecordMapper.getBatch(material_batch);
    }

    @Override
    public List<String> getTypeByBatch(String production_batch) {
        return productionRecordMapper.getTypeByBatch(production_batch);
    }

    @Override
    public List<String> getNameByBatchAndType(String production_batch, String product_type) {
        return productionRecordMapper.getNameByBatchAndType(production_batch, product_type);
    }

    @Override
    public List<ExcipientsPutRecord> getExcipientsPutRecordList(String production_batch) {
        return excipientsPutRecordMapper.getExcipientsPutRecordList(production_batch);
    }
}
