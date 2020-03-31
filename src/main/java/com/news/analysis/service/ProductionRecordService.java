package com.news.analysis.service;

import com.news.analysis.pojo.ExcipientsPutRecord;
import com.news.analysis.pojo.ProductionRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by chenchuan on 2019-04-17.
 */
public interface ProductionRecordService {

    ProductionRecord getProductionRecordByBatch(String production_batch);

    int creatProductionRecord(ProductionRecord productionRecord,String exPutRecord);

    String getProductionDate(String production_batch);

    List<ProductionRecord> getProductionRecords(Map<String,Object> paramsMap);

    List<String> getBatch(String material_batch);

    List<String> getTypeByBatch(String production_batch);

    List<String> getNameByBatchAndType(@Param("production_batch") String production_batch, @Param("product_type") String product_type);

    List<ExcipientsPutRecord> getExcipientsPutRecordList(String production_batch);
}
