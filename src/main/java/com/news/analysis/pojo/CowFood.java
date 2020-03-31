package com.news.analysis.pojo;

public class CowFood {
    private int id;                 //id
    private String cow_batch;      //奶牛批次
    private String date;            //日期
    private String food_name;      //食物
    private double food_weight;   //质量

    public CowFood() {
        super();
    }

    public CowFood(int id, String cow_batch, String date, String food_name, double food_weight) {
        this.id = id;
        this.cow_batch = cow_batch;
        this.date = date;
        this.food_name = food_name;
        this.food_weight = food_weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCow_batch() {
        return cow_batch;
    }

    public void setCow_batch(String cow_batch) {
        this.cow_batch = cow_batch;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public double getFood_weight() {
        return food_weight;
    }

    public void setFood_weight(double food_weight) {
        this.food_weight = food_weight;
    }

    @Override
    public String toString() {
        return "CowFood{" +
                "id=" + id +
                ", cow_batch='" + cow_batch + '\'' +
                ", date='" + date + '\'' +
                ", food_name='" + food_name + '\'' +
                ", food_weight=" + food_weight +
                '}';
    }
}
