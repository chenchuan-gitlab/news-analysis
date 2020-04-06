package com.news.analysis.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.analysis.dao.WebsiteConfigMapper;
import com.news.analysis.pojo.WebsiteConfig;
import com.news.analysis.utils.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * WebsiteConfigService
 */
@Service
public class WebsiteConfigService {

    @Autowired
    private WebsiteConfigMapper websiteConfigMapper;

    public PageForm getList(PageForm pageForm, Map params) {
        Page page = PageHelper.startPage(pageForm.getPageNum(), pageForm.getPageSize());
        List<Map> recordList = websiteConfigMapper.selectList(params);
        pageForm.setRows(recordList);
        pageForm.setTotalCount(page.getTotal());
        return pageForm;
    }

    public Object getDataByID(String id) {
        return websiteConfigMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    public void delete(String id) {
        websiteConfigMapper.deleteByIds(id);
    }

    public int add(WebsiteConfig websiteConfig) {
        return websiteConfigMapper.insert(websiteConfig);
    }

    public int update(WebsiteConfig websiteConfig) {
        return websiteConfigMapper.updateByPrimaryKeySelective(websiteConfig);
    }
}
