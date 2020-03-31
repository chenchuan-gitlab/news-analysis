package com.news.analysis.service.impl;

import com.news.analysis.dao.ProductInfoMapper;
import com.news.analysis.service.ProductInfoService;
import com.news.analysis.pojo.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public ProductInfo getRecordByID(int id) {
        return productInfoMapper.getRecordByID(id);
    }

    @Override
    public int createRecord(ProductInfo productInfo) {
        return productInfoMapper.createRecord(productInfo);
    }

    @Override
    public List<ProductInfo> getRecordList(Map<String, Object> paramsMap) {
        return productInfoMapper.getRecordList(paramsMap);
    }

    @Override
    public int updateRecord(ProductInfo productInfo) {
        return productInfoMapper.updateRecord(productInfo);
    }
}
