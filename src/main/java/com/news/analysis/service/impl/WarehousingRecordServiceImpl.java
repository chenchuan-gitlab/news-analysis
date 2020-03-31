package com.news.analysis.service.impl;

import com.news.analysis.dao.ProductionRecordMapper;
import com.news.analysis.dao.WarehousingRecordMapper;
import com.news.analysis.pojo.StockInformation;
import com.news.analysis.pojo.WarehousingRecord;
import com.news.analysis.service.WarehousingRecordService;
import com.news.analysis.dao.StockInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class WarehousingRecordServiceImpl implements WarehousingRecordService {

    @Autowired
    private WarehousingRecordMapper warehousingRecordMapper;
    @Autowired
    private ProductionRecordMapper productionRecordMapper;
    @Autowired
    private StockInformationMapper stockInformationMapper;

    @Override
    public WarehousingRecord getWarehousingRecordByBatch(String warehousing_batch) {
        return warehousingRecordMapper.getWarehousingRecordByBatch(warehousing_batch);
    }

    @Override
    @Transactional
    public int createWarehousingRecord(WarehousingRecord warehousingRecord) {
        String warehouse_number = warehousingRecord.getWarehouse_number();
        String production_batch = warehousingRecord.getProduction_batch();
        String product_type = warehousingRecord.getProduct_type();
        String product_name = warehousingRecord.getProduct_name();
        int inventory_quantity = warehousingRecord.getQuantity();
        String productionDate = productionRecordMapper.getProductionDate(production_batch);
        String warehousing_date = warehousingRecord.getWarehousing_date();
        StockInformation stockInformation = new StockInformation(warehouse_number,production_batch, product_type, product_name, inventory_quantity, productionDate, warehousing_date);
        int i = warehousingRecordMapper.createWarehousingRecord(warehousingRecord);
        int j = stockInformationMapper.createStockInfo(stockInformation);
        if (i != 0 && j != 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public List<WarehousingRecord> getWarehousingRecordList(Map<String, Object> paramsMap) {
        return warehousingRecordMapper.getWarehousingRecordList(paramsMap);
    }
}
