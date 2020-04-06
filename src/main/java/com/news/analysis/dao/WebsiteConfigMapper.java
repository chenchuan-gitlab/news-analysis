package com.news.analysis.dao;

import com.news.analysis.pojo.WebsiteConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WebsiteConfigMapper {
    int deleteByPrimaryKey(Long id);

    int deleteByIds(@Param("id") String id);

    int insert(WebsiteConfig record);

    int insertSelective(WebsiteConfig record);

    List<Map> selectList(Map params);

    WebsiteConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WebsiteConfig record);

    int updateByPrimaryKey(WebsiteConfig record);
}