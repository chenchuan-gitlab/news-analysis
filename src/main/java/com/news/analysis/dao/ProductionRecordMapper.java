package com.news.analysis.dao;

import com.news.analysis.pojo.ProductionRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductionRecordMapper {
    ProductionRecord getProductionRecordByBatch(String production_batch);

    int creatProductionRecord(ProductionRecord productionRecord);

    String getProductionDate(String production_batch);

    List<ProductionRecord> getProductionRecords(Map<String,Object> paramsMap);

    List<String> getBatch(String material_batch);

    List<String> getTypeByBatch(String production_batch);

    List<String> getNameByBatchAndType(@Param("production_batch") String production_batch,@Param("product_type") String product_type);
}
