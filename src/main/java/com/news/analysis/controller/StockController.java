package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.pojo.StockInformation;
import com.news.analysis.service.StockInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "stock")
public class StockController {
    @Autowired
    private StockInformationService stockInformationService;

    /**
     * 通过id获取记录
     *
     * @param record_id
     * @return
     */
    @RequestMapping(value = "getInfoByID.action", method = RequestMethod.GET)
    @ResponseBody
    public StockInformation getInfoByID(@RequestBody Integer record_id) {
        return stockInformationService.getInfoByID(record_id);
    }

    /*@RequestMapping(value = "createStockInfo",method = RequestMethod.POST)
    public Map<String,String> createStockInfo(@RequestBody StockInformation stockInformation){
        return null;
    }*/

    /**
     * 通过查询条件获取记录
     *
     * @param production_batch
     * @param product_type
     * @param product_name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getInfoList.action", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<StockInformation> getInfoList(@RequestParam(value = "production_batch", required = false) String production_batch,
                                                  @RequestParam(value = "product_type", required = false) String product_type,
                                                  @RequestParam(value = "product_name", required = false) String product_name,
                                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        if ("".equals(production_batch)) {
            production_batch = null;
        }
        if ("".equals(product_type)) {
            product_type = null;
        }
        if ("".equals(product_name)) {
            product_name = null;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("production_batch", production_batch);
        paramsMap.put("product_type", product_type);
        paramsMap.put("product_name", product_name);
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<StockInformation> infoList = stockInformationService.getInfoList(paramsMap);
        PageInfo<StockInformation> page = new PageInfo<>(infoList);
        return page;
    }

    /**
     * 获取该批次的库存数量
     *
     * @param production_batch
     * @return
     */
    @RequestMapping(value = "getQuantity.action", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getQuantity(@RequestBody String production_batch) {
        int quantity = stockInformationService.getQuantity(production_batch);
        Map<String, Object> map = new HashMap<>();
        map.put("quantity", quantity);
        return map;
    }

}
