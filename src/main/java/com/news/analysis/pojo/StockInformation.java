package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

/**
 * 库存信息
 */
@Component
public class StockInformation {
    private String warehouse_number;     //仓库编号
    private String production_batch;    //生产批次
    private String product_type;        //产品类型
    private String product_name;        //产品名称
    private int inventory_quantity;     //数量
    private String product_date;        //生产日期
    private String warehousing_date;    //入库日期

    public StockInformation() {
        super();
    }

    public StockInformation(String warehouse_number, String production_batch, String product_type, String product_name, int inventory_quantity, String product_date, String warehousing_date) {
        this.warehouse_number = warehouse_number;
        this.production_batch = production_batch;
        this.product_type = product_type;
        this.product_name = product_name;
        this.inventory_quantity = inventory_quantity;
        this.product_date = product_date;
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

    public int getInventory_quantity() {
        return inventory_quantity;
    }

    public void setInventory_quantity(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    public String getWarehousing_date() {
        return warehousing_date;
    }

    public void setWarehousing_date(String warehousing_date) {
        this.warehousing_date = warehousing_date;
    }

    @Override
    public String toString() {
        return "StockInformation{" +
                "warehouse_number='" + warehouse_number + '\'' +
                ", production_batch='" + production_batch + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_name='" + product_name + '\'' +
                ", inventory_quantity=" + inventory_quantity +
                ", product_date='" + product_date + '\'' +
                ", warehousing_date='" + warehousing_date + '\'' +
                '}';
    }
}
