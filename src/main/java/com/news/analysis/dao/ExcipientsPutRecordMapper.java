package com.news.analysis.dao;

import com.news.analysis.pojo.ExcipientsPutRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExcipientsPutRecordMapper {

    int createRecord(List<ExcipientsPutRecord> recordList);

    List<ExcipientsPutRecord> getExcipientsPutRecordList(@Param("production_batch") String production_batch);
}
