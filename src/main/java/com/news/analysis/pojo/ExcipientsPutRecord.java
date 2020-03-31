package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

@Component
public class ExcipientsPutRecord {
    private int id;
    private String production_batch;        //生产批次
    private String excipients_name;         //辅料名称
    private double feed_quantity;           //投料量

    public ExcipientsPutRecord() {
        super();
    }

    public ExcipientsPutRecord(int id, String production_batch, String excipients_name, double feed_quantity) {
        this.id = id;
        this.production_batch = production_batch;
        this.excipients_name = excipients_name;
        this.feed_quantity = feed_quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduction_batch() {
        return production_batch;
    }

    public void setProduction_batch(String production_batch) {
        this.production_batch = production_batch;
    }

    public String getExcipients_name() {
        return excipients_name;
    }

    public void setExcipients_name(String excipients_name) {
        this.excipients_name = excipients_name;
    }

    public double getFeed_quantity() {
        return feed_quantity;
    }

    public void setFeed_quantity(double feed_quantity) {
        this.feed_quantity = feed_quantity;
    }

    @Override
    public String toString() {
        return "ExcipientsPutRecord{" +
                "id=" + id +
                ", production_batch='" + production_batch + '\'' +
                ", excipients_name='" + excipients_name + '\'' +
                ", feed_quantity=" + feed_quantity +
                '}';
    }
}
