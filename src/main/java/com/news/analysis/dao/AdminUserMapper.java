package com.news.analysis.dao;

import com.news.analysis.pojo.CowFood;
import com.news.analysis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AdminUserMapper {
    /**
     * 获取一条记录
     *
     * @param id 记录id
     * @return 记录
     */
    Map getRecordByID(@Param("id") Long id);

    /**
     * 插入
     *
     * @param userInfo 用户信息
     * @return 操作结果
     */
    int insert(User userInfo);

    /**
     * 查询列表
     *
     * @param paramsMap 查询参数
     * @return 列表数据
     */
    List<Map> getUserList(Map<String,Object> paramsMap);

    /**
     * 更新数据
     *
     * @param user 用户信息
     * @return 操作结果
     */
    int updateUser(User user);

    /**
     * 删除数据
     *
     * @param id 数据id
     */
    void delete(@Param("id") String id);
}
