package com.news.analysis.service;

import com.news.analysis.pojo.MaterialInfo;

import java.util.List;
import java.util.Map;

public interface MaterialInfoService {
    MaterialInfo getRecordByBatch(String material_batch);

    int createRecord(MaterialInfo materialInfo);

    List<MaterialInfo> getRecordList(Map<String,Object> paramsMap);
}
