package com.news.analysis.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.news.analysis.service.OrderService;
import com.news.analysis.pojo.Order;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增订单
     *
     * @param order
     * @return
     */
    @RequestMapping(value = "insertOrder.action", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> insertOrder(@RequestBody Order order) {
        String order_id = IDUtil.getID();
        String order_date = DateUtil.getDate_yMdhms();
        order.setOrder_id(order_id);
        order.setOrder_date(order_date);
        order.setTotal_price(order.getUnit_price().multiply(new BigDecimal(order.getNumber())));
        int i = orderService.insertOrder(order);
        Map<String, String> map = new HashMap<>();
        if (i == 0) {
            map.put("result", "error");
            map.put("msg", "新增记录失败");
        } else {
            map.put("result", "success");
            map.put("msg", "新增记录成功");
        }
        return map;
    }

    /**
     * 根据id获取订单信息
     *
     * @param order_id
     * @return
     */
    @RequestMapping(value = "getOrderByID.action", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrderByID(String order_id) {
        Order order = orderService.getOrderById(order_id);
        return order;
    }

    /**
     * 根据参数获取订单
     *
     * @param order_id
     * @param start_time
     * @param end_time
     * @param production_batch
     * @param product_type
     * @param product_name
     * @param channel
     * @return
     */
    @RequestMapping(value = "getOrders.action", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Order> getOrders(@RequestParam(value = "order_id", required = false) String order_id,
                                     @RequestParam(value = "start_time", required = false) String start_time,
                                     @RequestParam(value = "end_time", required = false) String end_time,
                                     @RequestParam(value = "production_batch", required = false) String production_batch,
                                     @RequestParam(value = "product_type", required = false) String product_type,
                                     @RequestParam(value = "product_name", required = false) String product_name,
                                     @RequestParam(value = "channel", required = false) String channel,
                                     @RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("order_id", order_id);
        paramsMap.put("start_time", start_time);
        paramsMap.put("end_time", end_time);
        paramsMap.put("production_batch", production_batch);
        paramsMap.put("product_type", product_type);
        paramsMap.put("product_name", product_name);
        paramsMap.put("channel", channel);
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Order> orders = orderService.getOrders(paramsMap);
        PageInfo<Order> page = new PageInfo<>(orders);
        return page;
    }
}
