package com.news.analysis.dao;

import com.news.analysis.pojo.Reply;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Reply record);

    int insertSelective(Reply record);

    Map selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKeyWithBLOBs(Reply record);

    int updateByPrimaryKey(Reply record);

    List<Map> selectList(Map params);
}