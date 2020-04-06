package com.news.analysis.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.news.analysis.dao.AdminUserMapper;
import com.news.analysis.pojo.User;
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

    /**
     * 通过id获取用户信息
     *
     * @param id id
     * @return 用户数据
     */
    public Object getUserByID(String id) {
        return adminUserMapper.getRecordByID(Long.valueOf(id));
    }

    /**
     * 删除数据
     *
     * @param id 数据id
     */
    public void delete(String id) {
        adminUserMapper.delete(id);
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 添加结果
     */
    public int addUser(User user) {
        return adminUserMapper.insert(user);
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 更新结果
     */
    public int updateUser(User user) {
        return adminUserMapper.updateUser(user);
    }
}
