package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

/**
 * 生产记录
 */
@Component
public class ProductionRecord {
    private String production_batch;    //生产批次
    private String product_type;        //产品类型
    private String product_name;        //产品名称
    private String material_batch;      //原料批次
    private double feed_quantity;       //投料量
    private double plan_output;         //计划产出
    private String production_date;     //生产日期
    private String operator;            //操作人员

    public ProductionRecord() {
        super();
    }

    public ProductionRecord(String production_batch, String product_type, String product_name, String material_batch, double feed_quantity, double plan_output, String production_date, String operator) {
        this.production_batch = production_batch;
        this.product_type = product_type;
        this.product_name = product_name;
        this.material_batch = material_batch;
        this.feed_quantity = feed_quantity;
        this.plan_output = plan_output;
        this.production_date = production_date;
        this.operator = operator;
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

    public String getMaterial_batch() {
        return material_batch;
    }

    public void setMaterial_batch(String material_batch) {
        this.material_batch = material_batch;
    }

    public double getFeed_quantity() {
        return feed_quantity;
    }

    public void setFeed_quantity(double feed_quantity) {
        this.feed_quantity = feed_quantity;
    }

    public double getPlan_output() {
        return plan_output;
    }

    public void setPlan_output(double plan_output) {
        this.plan_output = plan_output;
    }

    public String getProduction_date() {
        return production_date;
    }

    public void setProduction_date(String production_date) {
        this.production_date = production_date;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "ProductionRecord{" +
                "production_batch='" + production_batch + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_name='" + product_name + '\'' +
                ", material_batch='" + material_batch + '\'' +
                ", feed_quantity=" + feed_quantity +
                ", plan_output=" + plan_output +
                ", production_date='" + production_date + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
