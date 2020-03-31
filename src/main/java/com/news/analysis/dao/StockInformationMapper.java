package com.news.analysis.dao;

import com.news.analysis.pojo.StockInformation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StockInformationMapper {
    StockInformation getInfoByID(int record_id);

    int createStockInfo(StockInformation stockInformation);

    List<StockInformation> getInfoList(Map<String,Object> paramsMap);

    int updateQuantity (@Param("production_batch") String production_batch, @Param("inventory_quantity") int inventory_quantity);

    int getQuantity(String production_batch);
}
