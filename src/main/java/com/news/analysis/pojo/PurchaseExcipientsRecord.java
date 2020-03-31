package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 辅料购买记录
 */
@Component
public class PurchaseExcipientsRecord {
    private String purchase_batch;      //采购批次
    private String excipients_name;     //辅料名称
    private String purchase_date;       //采购时间
    private String purchase_place;      //采购地点
    private BigDecimal unit_price;      //单价
    private double weight;              //重量
    private BigDecimal total_price;     //总价
    private String purchase_person;     //采购人员

    public PurchaseExcipientsRecord() {
        super();
    }

    public PurchaseExcipientsRecord(String purchase_batch, String excipients_name, String purchase_date, String purchase_place, BigDecimal unit_price, double weight, BigDecimal total_price, String purchase_person) {
        this.purchase_batch = purchase_batch;
        this.excipients_name = excipients_name;
        this.purchase_date = purchase_date;
        this.purchase_place = purchase_place;
        this.unit_price = unit_price;
        this.weight = weight;
        this.total_price = total_price;
        this.purchase_person = purchase_person;
    }

    public String getPurchase_batch() {
        return purchase_batch;
    }

    public void setPurchase_batch(String purchase_batch) {
        this.purchase_batch = purchase_batch;
    }

    public String getExcipients_name() {
        return excipients_name;
    }

    public void setExcipients_name(String excipients_name) {
        this.excipients_name = excipients_name;
    }

    public String getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(String purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getPurchase_place() {
        return purchase_place;
    }

    public void setPurchase_place(String purchase_place) {
        this.purchase_place = purchase_place;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public String getPurchase_person() {
        return purchase_person;
    }

    public void setPurchase_person(String purchase_person) {
        this.purchase_person = purchase_person;
    }

    @Override
    public String toString() {
        return "PurchaseExcipientsRecord{" +
                "purchase_batch='" + purchase_batch + '\'' +
                ", excipients_name='" + excipients_name + '\'' +
                ", purchase_date='" + purchase_date + '\'' +
                ", purchase_place='" + purchase_place + '\'' +
                ", unit_price=" + unit_price +
                ", weight=" + weight +
                ", total_price=" + total_price +
                ", purchase_person='" + purchase_person + '\'' +
                '}';
    }
}
