package com.news.analysis.service;

import com.news.analysis.pojo.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProductInfoService {
    ProductInfo getRecordByID(@Param("id") int id);

    int createRecord(ProductInfo productInfo);

    List<ProductInfo> getRecordList(Map<String,Object> paramsMap);

    int updateRecord(ProductInfo productInfo);
}
