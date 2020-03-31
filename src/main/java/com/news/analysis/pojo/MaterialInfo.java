package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 原料购买记录
 */
@Component
public class MaterialInfo {
    private String material_batch;      //原料奶批次
    private String cow_batch;            //奶牛批次
    private double milk_yield;          //产奶量
    private String date;                  //产奶日期

    public MaterialInfo() {
        super();
    }

    public MaterialInfo(String material_batch, String cow_batch, double milk_yield, String date) {
        this.material_batch = material_batch;
        this.cow_batch = cow_batch;
        this.milk_yield = milk_yield;
        this.date = date;
    }

    public String getMaterial_batch() {
        return material_batch;
    }

    public void setMaterial_batch(String material_batch) {
        this.material_batch = material_batch;
    }

    public String getCow_batch() {
        return cow_batch;
    }

    public void setCow_batch(String cow_batch) {
        this.cow_batch = cow_batch;
    }

    public double getMilk_yield() {
        return milk_yield;
    }

    public void setMilk_yield(double milk_yield) {
        this.milk_yield = milk_yield;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MaterialInfo{" +
                "material_batch='" + material_batch + '\'' +
                ", cow_batch='" + cow_batch + '\'' +
                ", milk_yield=" + milk_yield +
                ", date='" + date + '\'' +
                '}';
    }
}
