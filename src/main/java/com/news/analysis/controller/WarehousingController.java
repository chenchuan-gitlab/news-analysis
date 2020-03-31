package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.pojo.WarehousingRecord;
import com.news.analysis.service.WarehousingRecordService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "warehousing")
public class WarehousingController {

    @Autowired
    private WarehousingRecordService warehousingRecordService;

    /**
     * 通过批次获取记录
     * @param warehousing_batch
     * @return
     */
    @RequestMapping(value = "getRecordByBatch.action",method = RequestMethod.GET)
    @ResponseBody
    public WarehousingRecord getRecordByBatch(@RequestBody String warehousing_batch){
        return warehousingRecordService.getWarehousingRecordByBatch(warehousing_batch);
    }


    /**
     * 新增记录
     * @param warehousingRecord
     * @return
     */
    @RequestMapping(value = "createWarehousingRecord.action",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> createWarehousingRecord(@RequestBody WarehousingRecord warehousingRecord){
        warehousingRecord.setWarehousing_batch(IDUtil.getID());
        warehousingRecord.setWarehousing_date(DateUtil.getDate_yMdhms());
        int i = warehousingRecordService.createWarehousingRecord(warehousingRecord);
        Map<String,String> map = new HashMap<>();
        if (i == 0) {
            map.put("result", "error");
            map.put("msg", "新增记录失败");
        } else {
            map.put("result", "success");
            map.put("msg", "新增记录成功");
        }
        return map;
    }

    @RequestMapping(value = "getRecordList.action",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<WarehousingRecord> getRecordList(@RequestParam(value = "warehousing_batch",required = false) String warehousing_batch,
                                                     @RequestParam(value = "start_time",required = false) String start_time,
                                                     @RequestParam(value = "end_time",required = false) String end_time,
                                                     @RequestParam(value = "warehouse_number",required = false) String warehouse_number,
                                                     @RequestParam(value = "production_batch",required = false) String production_batch,
                                                     @RequestParam(value = "product_type",required = false) String product_type,
                                                     @RequestParam(value = "product_name",required = false) String product_name,
                                                     @RequestParam(value = "operator",required = false) String operator,
                                                     @RequestParam(value = "pageNum",required = false,defaultValue = "1") String pageNum,
                                                     @RequestParam(value = "pageSize",required = false,defaultValue = "10") String pageSize){
        if("".equals(warehousing_batch)){
            warehousing_batch = null;
        }
        if("".equals(start_time)){
            start_time = null;
        }
        if("".equals(end_time)){
            end_time = null;
        }
        if("".equals(warehouse_number)){
            warehouse_number = null;
        }
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
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("warehousing_batch",warehousing_batch);
        paramsMap.put("start_time",start_time);
        paramsMap.put("end_time",end_time);
        paramsMap.put("warehouse_number",warehouse_number);
        paramsMap.put("production_batch",production_batch);
        paramsMap.put("product_type",product_type);
        paramsMap.put("product_name",product_name);
        paramsMap.put("operator",operator);
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        List<WarehousingRecord> recordList = warehousingRecordService.getWarehousingRecordList(paramsMap);
        PageInfo<WarehousingRecord> page = new PageInfo<>(recordList);
        return page;
    }
}
