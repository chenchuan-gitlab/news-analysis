package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.service.CowVaccinationService;
import com.news.analysis.pojo.CowVaccination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cowVaccination")
public class CowVaccinationController {
    @Autowired
    CowVaccinationService cowVaccinationService;

    @RequestMapping(value = "getRecordByID.action", method = RequestMethod.GET)
    @ResponseBody
    public CowVaccination getRecordByID(@RequestParam("id") int id) {
        return cowVaccinationService.getRecordByID(id);
    }

    @RequestMapping(value = "createRecord.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> createRecord(@RequestBody CowVaccination cowVaccination) {
        int i = cowVaccinationService.createRecord(cowVaccination);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "新增记录成功");
        } else {
            map.put("msg", "新增记录失败");
        }
        return map;
    }

    @RequestMapping(value = "getRecordList.action", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<CowVaccination> getRecordList(@RequestParam("cow_batch") String cow_batch,
                                           @RequestParam("start_time") String start_time,
                                           @RequestParam("end_time") String end_time,
                                           @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        if ("".equals(cow_batch)) {
            cow_batch = null;
        }
        if ("".equals(start_time)) {
            start_time = null;
        }
        if ("".equals(end_time)) {
            end_time = null;
        }
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("cow_batch", cow_batch);
        paramsMap.put("start_time", start_time);
        paramsMap.put("end_time", end_time);
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<CowVaccination> recordList = cowVaccinationService.getRecordList(paramsMap);
        PageInfo<CowVaccination> page = new PageInfo<>(recordList);
        return page;
    }


    @RequestMapping(value = "updateRecord.action",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateRecord(@RequestBody CowVaccination cowVaccination) {
        int i = cowVaccinationService.updateRecord(cowVaccination);
        Map<String, String> map = new HashMap<>();
        if (i != 0) {
            map.put("msg", "修改成功");
        } else {
            map.put("msg", "修改失败");
        }
        return map;
    }
}
