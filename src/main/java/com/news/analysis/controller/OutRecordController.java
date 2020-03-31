package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.pojo.OutRecord;
import com.news.analysis.service.OutRecordService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "outRecord")
public class OutRecordController {

    @Autowired
    private OutRecordService outRecordService;

    /**
     * 创建记录
     *
     * @param outRecord
     * @return
     */
    @RequestMapping(value = "createOutRecord.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createOutRecord(@RequestBody OutRecord outRecord) {
        outRecord.setOut_number(IDUtil.getID());
        outRecord.setOut_date(DateUtil.getDate_yMdhms());
        int i = outRecordService.createOutRecord(outRecord);
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
     * 通过id获取记录
     *
     * @param out_number
     * @return
     */
    @RequestMapping(value = "getOutRecordByID.action", method = RequestMethod.GET)
    @ResponseBody
    public OutRecord getOutRecordByID(String out_number) {
        OutRecord outRecord = outRecordService.getOutRecordByID(out_number);
        return outRecord;
    }

    /**
     * 通过条件获取出库记录
     *
     * @param out_number
     * @param start_time
     * @param end_time
     * @param warehouse_number
     * @param production_batch
     * @param product_type
     * @param product_name
     * @param operator
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getOutRecords.action", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<OutRecord> getOutRecords(@RequestParam(value = "out_number", required = false) String out_number,
                                             @RequestParam(value = "start_time", required = false) String start_time,
                                             @RequestParam(value = "end_time", required = false) String end_time,
                                             @RequestParam(value = "warehouse_number", required = false) String warehouse_number,
                                             @RequestParam(value = "production_batch", required = false) String production_batch,
                                             @RequestParam(value = "product_type", required = false) String product_type,
                                             @RequestParam(value = "product_name", required = false) String product_name,
                                             @RequestParam(value = "operator", required = false) String operator,
                                             @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        if ("".equals(out_number)) {
            out_number = null;
        }
        if ("".equals(start_time)) {
            start_time = null;
        }
        if ("".equals(end_time)) {
            end_time = null;
        }
        if ("".equals(warehouse_number)) {
            warehouse_number = null;
        }
        if ("".equals(production_batch)) {
            production_batch = null;
        }
        if ("".equals(product_type)) {
            product_type = null;
        }
        if ("".equals(product_name)) {
            product_name = null;
        }
        if ("".equals(operator)) {
            operator = null;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("out_number", out_number);
        paramsMap.put("start_time", start_time);
        paramsMap.put("end_time", end_time);
        paramsMap.put("warehouse_number", warehouse_number);
        paramsMap.put("production_batch", production_batch);
        paramsMap.put("product_type", product_type);
        paramsMap.put("product_name", product_name);
        paramsMap.put("operator", operator);
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<OutRecord> outRecords = outRecordService.getOutRecords(paramsMap);
        PageInfo<OutRecord> page = new PageInfo<>(outRecords);
        return page;
    }
}
