package com.news.analysis.dao.api;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {
    /**
     * 保存用户
     *
     * @param user 用户数据
     */
    void saveUser(Map user) throws Exception;

    /**
     * 获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    Map getUserInfo(String userName);
}
