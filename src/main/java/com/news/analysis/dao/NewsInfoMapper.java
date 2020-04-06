package com.news.analysis.dao;

import com.news.analysis.pojo.NewsInfo;

import java.util.List;
import java.util.Map;

public interface NewsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NewsInfo record);

    int insertSelective(NewsInfo record);

    NewsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NewsInfo record);

    int updateByPrimaryKey(NewsInfo record);

    List<Map> selectList(Map params);
}