package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

/**
 * 出库记录
 */
@Component
public class OutRecord {
    private String out_number;   //出库批次
    private String out_date;    //出库日期
    private String warehouse_number;    //仓库编号
    private String production_batch;   //生产编号
    private String product_type;    //产品种类；
    private String product_name;    //产品名称
    private int quantity;           //出库数量
    private String operator;        //操作人员
    private String remark;          //备注

    public OutRecord() {
        super();
    }

    public OutRecord(String out_number, String out_date, String warehouse_number, String production_batch, String product_type, String product_name, int quantity, String operator, String remark) {
        this.out_number = out_number;
        this.out_date = out_date;
        this.warehouse_number = warehouse_number;
        this.production_batch = production_batch;
        this.product_type = product_type;
        this.product_name = product_name;
        this.quantity = quantity;
        this.operator = operator;
        this.remark = remark;
    }

    public String getOut_number() {
        return out_number;
    }

    public void setOut_number(String out_number) {
        this.out_number = out_number;
    }

    public String getOut_date() {
        return out_date;
    }

    public void setOut_date(String out_date) {
        this.out_date = out_date;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OutRecord{" +
                "out_number='" + out_number + '\'' +
                ", out_date='" + out_date + '\'' +
                ", warehouse_number='" + warehouse_number + '\'' +
                ", production_batch='" + production_batch + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_name='" + product_name + '\'' +
                ", quantity=" + quantity +
                ", operator='" + operator + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
