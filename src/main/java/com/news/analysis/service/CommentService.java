package com.news.analysis.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.analysis.dao.CommentMapper;
import com.news.analysis.utils.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * CommentService
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    public PageForm getList(PageForm pageForm, Map params) {
        Page page = PageHelper.startPage(pageForm.getPageNum(), pageForm.getPageSize());
        List<Map> recordList = commentMapper.selectList(params);
        pageForm.setRows(recordList);
        pageForm.setTotalCount(page.getTotal());
        return pageForm;
    }

    public Object getDataByID(String id) {
        return commentMapper.selectByPrimaryKey(Long.valueOf(id));
    }
}
