package com.news.analysis.service.api;

import com.news.analysis.dao.api.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 保存用户
     *
     * @param user 用户数据
     * @throws Exception
     */
    public void saveUser(Map user) throws Exception {
        userMapper.saveUser(user);
    }

    /**
     * 获取用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    public Map getUserInfo(String userName) {
        return userMapper.getUserInfo(userName);
    }
}
