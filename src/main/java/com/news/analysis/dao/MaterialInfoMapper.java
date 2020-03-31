package com.news.analysis.dao;

import com.news.analysis.pojo.MaterialInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MaterialInfoMapper {
    MaterialInfo getRecordByBatch(@Param("material_batch") String material_batch);

    int createRecord(MaterialInfo materialInfo);

    List<MaterialInfo> getRecordList(Map<String,Object> paramsMap);
}
