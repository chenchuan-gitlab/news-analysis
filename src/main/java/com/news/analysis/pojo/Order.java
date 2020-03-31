package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 订单
 */
@Component
public class Order {
    private String order_id;    //订单id
    private String order_date;  //订单时间
    private String production_batch;  //生产批次
    private String product_type;    //产品种类
    private String product_name;    //产品名称
    private BigDecimal unit_price;  //单价
    private int number;             //数量
    private BigDecimal total_price; //总价
    private String channel;         //渠道（个人/经销商）

    public Order() {
        super();
    }

    public Order(String order_id, String order_date, String production_batch, String product_type, String product_name, BigDecimal unit_price, int number, BigDecimal total_price, String channel) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.production_batch = production_batch;
        this.product_type = product_type;
        this.product_name = product_name;
        this.unit_price = unit_price;
        this.number = number;
        this.total_price = total_price;
        this.channel = channel;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getProduction_batch() {
        return production_batch;
    }

    public void setProduction_batch(String production_batch) {
        this.production_batch = production_batch;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", order_date='" + order_date + '\'' +
                ", production_batch='" + production_batch + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_name='" + product_name + '\'' +
                ", unit_price=" + unit_price +
                ", number=" + number +
                ", total_price=" + total_price +
                ", channel='" + channel + '\'' +
                '}';
    }
}
