package com.news.analysis.service.api;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.news.analysis.dao.api.NewsAnalysisMapper;
import com.news.analysis.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NewsAnalysisService
 */
@Service
public class NewsAnalysisService {

    @Autowired
    private NewsAnalysisMapper newsAnalysisMapper;

    public Map getData() {
        List<Map> dataList = newsAnalysisMapper.getDataList();
        Map dataMap = new HashMap(2);
        List<String> typeList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        for (Map data : dataList) {
            String type = MapUtil.getStr(data, "type");
            typeList.add(type);
            Integer value = MapUtil.getInt(data, "total");
            valueList.add(value == null ? 0 : value);
        }
        dataMap.put("type", typeList);
        dataMap.put("data", valueList);
        return dataMap;
    }

    public Map getTodayTotal() {
        String currentDay = DateUtil.getDate_yMd();
        String startTime = currentDay + " 00:00:00";
        String endTime = currentDay + " 23:59:59";
        List<Map> dataList = newsAnalysisMapper.getTodayTotal(startTime, endTime);
        Map dataMap = new HashMap(2);
        List<String> typeList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        for (Map data : dataList) {
            String type = MapUtil.getStr(data, "type");
            typeList.add(type);
            Integer value = MapUtil.getInt(data, "total");
            valueList.add(value == null ? 0 : value);
        }
        dataMap.put("type", typeList);
        dataMap.put("data", valueList);
        return dataMap;
    }

    public Map getMonthTotal(String month) throws Exception {
        Map<String, Object> dataSetMap = new HashMap<>(2);
        List<String> typeList = new ArrayList<>();
        List<Map> dataValueList = new ArrayList<>();
        if (StrUtil.isBlank(month)) {
            month = DateUtil.getDate_ym();
        }
        List<String> monthDayList = DateUtil.getDaysByMonth(month);
        String startDate = monthDayList.get(0);
        String endDate = monthDayList.get(monthDayList.size() - 1);
        String startTime = startDate + " 00:00:00";
        String endTime = endDate + " 23:59:59";
        List<Map<String, Object>> dataList = newsAnalysisMapper.getMonthTotal(startTime, endTime);
        for (Map<String, Object> dataMap : dataList) {
            List<Integer> datasList = new ArrayList<>();
            Map valueMap = new HashMap(16);
            String type = MapUtil.getStr(dataMap, "type");
            typeList.add(type);
            List<Map> list = (List) dataMap.get("list");
            for (String day : monthDayList) {
                Integer total = 0;
                for (Map map : list) {
                    String time = MapUtil.getStr(map, "time");
                    if (StrUtil.isNotBlank(time) && day.equals(time)) {
                        total = MapUtil.getInt(map, "total");
                        break;
                    }
                }
                datasList.add(total);
            }
            valueMap.put("name", type);
            valueMap.put("type", "line");
            valueMap.put("stack", "总量");
            valueMap.put("areaStyle", new HashMap<>(2));
            valueMap.put("data", datasList);
            dataValueList.add(valueMap);
        }
        dataSetMap.put("type", typeList);
        dataSetMap.put("time", monthDayList);
        dataSetMap.put("data", dataValueList);
        return dataSetMap;
    }
}
