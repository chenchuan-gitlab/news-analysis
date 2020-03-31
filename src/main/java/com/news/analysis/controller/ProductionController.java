package com.news.analysis.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.pojo.ExcipientsPutRecord;
import com.news.analysis.pojo.ProductionRecord;
import com.news.analysis.service.ProductionRecordService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "production")
public class ProductionController {

    @Autowired
    private ProductionRecordService productionRecordService;

    /**
     * 通过生成批次获取生产记录
     *
     * @param production_batch
     * @return
     */
    @RequestMapping(value = "getProductionRecordByBatch.action", method = RequestMethod.GET)
    @ResponseBody
    public ProductionRecord getProductionRecordByBatch(@RequestBody String production_batch) {
        ProductionRecord productionRecord = productionRecordService.getProductionRecordByBatch(production_batch);
        return productionRecord;
    }

    /**
     * 新建生产记录
     *
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "createProductionRecord.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createProductionRecord(@RequestBody Map<String, Object> requestMap) {
        ProductionRecord productionRecord = JSON.parseObject(requestMap.get("record").toString(), ProductionRecord.class);
        productionRecord.setProduction_batch(IDUtil.getID());
        productionRecord.setProduction_date(DateUtil.getDate_yMd());
        int i = productionRecordService.creatProductionRecord(productionRecord, requestMap.get("exPutRecord").toString());
        Map<String, String> map = new HashMap<>();
        if (i == 0) {
            map.put("result", "error");
            map.put("msg", "新增记录失败");
        } else {
            map.put("result", "success");
            map.put("msg", "新增记录成功");
        }
        return map;
    }

    /**
     * 通过条件获取生产记录
     *
     * @param production_batch
     * @param product_type
     * @param product_name
     * @param operator
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getProductionRecords.action", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<ProductionRecord> getProductionRecords(@RequestParam(value = "production_batch", required = false) String production_batch,
                                                           @RequestParam(value = "product_type", required = false) String product_type,
                                                           @RequestParam(value = "product_name", required = false) String product_name,
                                                           @RequestParam(value = "operator", required = false) String operator,
                                                           @RequestParam(value = "start_time", required = false) String start_time,
                                                           @RequestParam(value = "end_time", required = false) String end_time,
                                                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        if("".equals(production_batch)){
            production_batch = null;
        }
        if("".equals(product_type)){
            product_type = null;
        }
        if("".equals(product_name)){
            product_name = null;
        }
        if("".equals(operator)){
            operator = null;
        }
        if("".equals(start_time)){
            start_time = null;
        }
        if("".equals(end_time)){
            end_time = null;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("production_batch", production_batch);
        paramsMap.put("product_type", product_type);
        paramsMap.put("product_name", product_name);
        paramsMap.put("operator", operator);
        paramsMap.put("start_time", start_time);
        paramsMap.put("end_time", end_time);
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<ProductionRecord> productionRecordList = productionRecordService.getProductionRecords(paramsMap);
        PageInfo<ProductionRecord> page = new PageInfo<>(productionRecordList);
        return page;
    }

    /**
     * 通过原料批次获取生产批次
     *
     * @return
     */
    @RequestMapping(value = "getProductionBatch.action", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getProductionBatch(String material_batch) {
        return productionRecordService.getBatch(material_batch);
    }

    /**
     * 通过批次获取种类
     *
     * @param production_batch
     * @return
     */
    @RequestMapping(value = "getProductionType.action", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getProductionType(@RequestBody String production_batch) {
        return productionRecordService.getTypeByBatch(production_batch);
    }

    /**
     * 通过批次和种类获取名称
     *
     * @param production_batch
     * @param production_type
     * @return
     */
    @RequestMapping(value = "getProductionName.action", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getProductionName(@RequestBody String production_batch, @RequestBody String production_type) {
        return productionRecordService.getNameByBatchAndType(production_batch, production_type);
    }

    /**
     * 获取辅料投放信息
     *
     * @param production_batch
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getExcipientsPutRecordList.action", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<ExcipientsPutRecord> getExcipientsPutRecordList(@RequestParam(value = "production_batch", required = false) String production_batch,
                                                                    @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        if("".equals(production_batch)){
            production_batch = null;
        }
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<ExcipientsPutRecord> recordList = productionRecordService.getExcipientsPutRecordList(production_batch);
        PageInfo<ExcipientsPutRecord> page = new PageInfo<>(recordList);
        return page;
    }

}
