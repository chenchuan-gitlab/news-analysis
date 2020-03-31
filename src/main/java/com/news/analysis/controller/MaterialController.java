package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.pojo.MaterialInfo;
import com.news.analysis.service.MaterialInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "material")
public class MaterialController {

    @Autowired
    private MaterialInfoService materialInfoService;

    /**
     * 通过批次获取记录
     * @param material_batch
     * @return
     */
    @RequestMapping(value = "getRecordByBatch",method = RequestMethod.GET)
    @ResponseBody
    public MaterialInfo getRecordByBatch(@RequestBody String material_batch){
        return materialInfoService.getRecordByBatch(material_batch);
    }

    /**
     * 新增记录
     * @param materialInfo
     * @return
     */
    @RequestMapping(value = "createRecord.action",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> createRecord(@RequestBody MaterialInfo materialInfo){
        /*materialInfo.setMaterial_batch(IDUtil.getID());*/
       /* purchaseMaterialRecord.setPurchase_date(DateUtil.getDate_yMd());*/
        /*materialInfo.setDate(DateUtil.getDate_yMd());*/
        int i = materialInfoService.createRecord(materialInfo);
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
     * 通过查询条件获取记录
     * @param material_batch
     * @param cow_batch
     * @param start_time
     * @param end_time
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "getRecordList.action",method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<MaterialInfo> getRecordList(@RequestParam(value = "material_batch",required = false) String material_batch,
                                                @RequestParam(value = "cow_batch",required = false) String cow_batch,
                                                @RequestParam(value = "start_time",required = false) String start_time,
                                                @RequestParam(value = "end_time",required = false) String end_time,
                                                @RequestParam(value = "pageNum",required = false,defaultValue = "1") String pageNum,
                                                @RequestParam(value = "pageSize",required = false,defaultValue = "10") String pageSize){
        if("".equals(material_batch)){
            material_batch = null;
        }
        if("".equals(cow_batch)){
            cow_batch = null;
        }
        if("".equals(start_time)){
            start_time = null;
        }
        if("".equals(end_time)){
            end_time = null;
        }
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("material_batch",material_batch);
        paramsMap.put("cow_batch",cow_batch);
        paramsMap.put("start_time",start_time);
        paramsMap.put("end_time",end_time);
        PageHelper.startPage(Integer.parseInt(pageNum),Integer.parseInt(pageSize));
        List<MaterialInfo> recordList = materialInfoService.getRecordList(paramsMap);
        PageInfo<MaterialInfo> page = new PageInfo<>(recordList);
        return page;
    }
}
