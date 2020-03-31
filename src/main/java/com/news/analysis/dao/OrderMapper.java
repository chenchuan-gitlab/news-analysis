package com.news.analysis.dao;

import com.news.analysis.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    Order getOrderByID(String order_id);

    List<Order> getOrders(Map<String,Object> paramsMap);

    int insertOrder(Order order);

}
