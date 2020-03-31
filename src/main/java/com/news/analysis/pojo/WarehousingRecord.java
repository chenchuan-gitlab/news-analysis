package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

/**
 * 入库信息
 */
@Component
public class WarehousingRecord {
    private String warehousing_batch;   //入库批次
    private String warehousing_date;    //入库日期
    private String warehouse_number;   //仓库编号
    private String production_batch;    //生产批次
    private String product_type;        //产品类型
    private String product_name;        //产品名称
    private int quantity;              //数量
    private String operator;            //操作人员

    public WarehousingRecord() {
        super();
    }

    public WarehousingRecord(String warehousing_batch, String warehousing_date, String warehouse_number, String production_batch, String product_type, String product_name, int quantity, String operator) {
        this.warehousing_batch = warehousing_batch;
        this.warehousing_date = warehousing_date;
        this.warehouse_number = warehouse_number;
        this.production_batch = production_batch;
        this.product_type = product_type;
        this.product_name = product_name;
        this.quantity = quantity;
        this.operator = operator;
    }

    public String getWarehousing_batch() {
        return warehousing_batch;
    }

    public void setWarehousing_batch(String warehousing_batch) {
        this.warehousing_batch = warehousing_batch;
    }

    public String getWarehousing_date() {
        return warehousing_date;
    }

    public void setWarehousing_date(String warehousing_date) {
        this.warehousing_date = warehousing_date;
    }

    public String getWarehouse_number() {
        return warehouse_number;
    }

    public void setWarehouse_number(String warehouse_number) {
        this.warehouse_number = warehouse_number;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "WarehousingRecord{" +
                "warehousing_batch='" + warehousing_batch + '\'' +
                ", warehousing_date='" + warehousing_date + '\'' +
                ", warehouse_number='" + warehouse_number + '\'' +
                ", production_batch='" + production_batch + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_name='" + product_name + '\'' +
                ", quantity=" + quantity +
                ", operator='" + operator + '\'' +
                '}';
    }
}
