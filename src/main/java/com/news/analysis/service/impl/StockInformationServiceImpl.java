package com.news.analysis.service.impl;

import com.news.analysis.pojo.StockInformation;
import com.news.analysis.service.StockInformationService;
import com.news.analysis.dao.StockInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StockInformationServiceImpl implements StockInformationService {

    @Autowired
    private StockInformationMapper stockInformationMapper;

    @Override
    public StockInformation getInfoByID(int record_id) {
        return stockInformationMapper.getInfoByID(record_id);
    }

    @Override
    public int createStockInfo(StockInformation stockInformation) {
        return stockInformationMapper.createStockInfo(stockInformation);
    }

    @Override
    public List<StockInformation> getInfoList(Map<String, Object> paramsMap) {
        return stockInformationMapper.getInfoList(paramsMap);
    }

    @Override
    public int updateQuantity(String production_batch, int inventory_quantity) {
        return stockInformationMapper.updateQuantity(production_batch,inventory_quantity);
    }

    @Override
    public int getQuantity(String production_batch) {
        return stockInformationMapper.getQuantity(production_batch);
    }
}
