package com.news.analysis.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.analysis.dao.AdminUserMapper;
import com.news.analysis.utils.PageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * AdminUserService
 */
@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    /**
     * 获取用户列表
     *
     * @param pageForm 分页信息
     * @param params   查询参数
     * @return 用户数据
     */
    public PageForm getUserList(PageForm pageForm, Map params) {
        Page page = PageHelper.startPage(pageForm.getPageNum(), pageForm.getPageSize());
        List<Map> recordList = adminUserMapper.getUserList(params);
        pageForm.setRows(recordList);
        pageForm.setTotalCount(page.getTotal());
        return pageForm;
    }

}
