package com.news.analysis.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.analysis.dao.NewsInfoMapper;
import com.news.analysis.dao.ReplyMapper;
import com.news.analysis.utils.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ReplyService
 */
@Service
public class ReplyService {
    @Autowired
    private ReplyMapper replyMapper;

    public PageForm getList(PageForm pageForm, Map params) {
        Page page = PageHelper.startPage(pageForm.getPageNum(), pageForm.getPageSize());
        //List<Map> recordList = replyMapper.selectList(params);
        List<Map> recordList = new ArrayList<>();
        pageForm.setRows(recordList);
        pageForm.setTotalCount(page.getTotal());
        return pageForm;
    }

    public Object getDataByID(String id) {
        return replyMapper.selectByPrimaryKey(Long.valueOf(id));
    }
}
