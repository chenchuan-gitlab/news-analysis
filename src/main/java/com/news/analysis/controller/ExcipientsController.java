package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.pojo.PurchaseExcipientsRecord;
import com.news.analysis.service.PurchaseExcipientsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "excipients")
public class ExcipientsController {

    @Autowired
    private PurchaseExcipientsRecordService purchaseExcipientsRecordService;

    /**
     * 根据批次查询记录
     * @param purchase_batch
     * @return
     */
    @RequestMapping(value = "getRecordByBatch.action", method = RequestMethod.GET)
    @ResponseBody
    public PurchaseExcipientsRecord getRecordByBatch(@RequestBody String purchase_batch) {
        return purchaseExcipientsRecordService.getRecordByBatch(purchase_batch);
    }

    /**
     * 新增记录
     * @param purchaseExcipientsRecord
     * @return
     */
    @RequestMapping(value = "createRecord.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createRecord(@RequestBody PurchaseExcipientsRecord purchaseExcipientsRecord) {
        /*purchaseExcipientsRecord.setPurchase_batch(IDUtil.getID());
        purchaseExcipientsRecord.setPurchase_date(DateUtil.getDate_yMd());*/
        purchaseExcipientsRecord.setTotal_price(purchaseExcipientsRecord.getUnit_price().multiply(new BigDecimal(purchaseExcipientsRecord.getWeight())));
        int i = purchaseExcipientsRecordService.createRecord(purchaseExcipientsRecord);
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

    /**
     * 通过条件获取记录
     * @param purchase_batch
     * @param excipients_name
     * @param start_time
     * @param end_time
     * @param purchase_place
     * @param purchase_person
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getRecordList.action",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<PurchaseExcipientsRecord> getRecordList(@RequestParam(value = "purchase_batch",required = false) String purchase_batch,
                                                            @RequestParam(value = "excipients_name",required = false) String excipients_name,
                                                            @RequestParam(value = "start_time",required = false) String start_time,
                                                            @RequestParam(value = "end_time",required = false) String end_time,
                                                            @RequestParam(value = "purchase_place",required = false) String purchase_place,
                                                            @RequestParam(value = "purchase_person",required = false) String purchase_person,
                                                            @RequestParam(value = "pageNum",required = false,defaultValue = "1") String pageNum,
                                                            @RequestParam(value = "pageSize",required = false,defaultValue = "10") String pageSize){
        if("".equals(purchase_batch)){
            purchase_batch=null;
        }
        if("".equals(excipients_name)){
            excipients_name=null;
        }
        if("".equals(start_time)){
            start_time=null;
        }
        if("".equals(end_time)){
            end_time=null;
        }
        if("".equals(purchase_place)){
            purchase_place=null;
        }
        if("".equals(purchase_person)){
            purchase_person=null;
        }
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("purchase_batch",purchase_batch);
        paramsMap.put("excipients_name",excipients_name);
        paramsMap.put("start_time",start_time);
        paramsMap.put("end_time",end_time);
        paramsMap.put("purchase_place",purchase_place);
        paramsMap.put("purchase_person",purchase_person);
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        List<PurchaseExcipientsRecord> recordList = purchaseExcipientsRecordService.getRecordList(paramsMap);
        PageInfo<PurchaseExcipientsRecord> page = new PageInfo<>(recordList);
        return page;
    }

}
