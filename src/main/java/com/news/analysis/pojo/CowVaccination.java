package com.news.analysis.pojo;

public class CowVaccination {
    private int id;                       //id
    private String cow_batch;            //奶牛批次
    private String vaccine_name;         //疫苗名称
    private String vaccination_date;     //接种时间
    private int vaccination_number;     //接种头数
    private int total_cow;               //总头数

    public CowVaccination() {
        super();
    }

    public CowVaccination(int id, String cow_batch, String vaccine_name, String vaccination_date, int vaccination_number, int total_cow) {
        this.id = id;
        this.cow_batch = cow_batch;
        this.vaccine_name = vaccine_name;
        this.vaccination_date = vaccination_date;
        this.vaccination_number = vaccination_number;
        this.total_cow = total_cow;
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

    public String getVaccine_name() {
        return vaccine_name;
    }

    public void setVaccine_name(String vaccine_name) {
        this.vaccine_name = vaccine_name;
    }

    public String getVaccination_date() {
        return vaccination_date;
    }

    public void setVaccination_date(String vaccination_date) {
        this.vaccination_date = vaccination_date;
    }

    public int getVaccination_number() {
        return vaccination_number;
    }

    public void setVaccination_number(int vaccination_number) {
        this.vaccination_number = vaccination_number;
    }

    public int getTotal_cow() {
        return total_cow;
    }

    public void setTotal_cow(int total_cow) {
        this.total_cow = total_cow;
    }

    @Override
    public String toString() {
        return "CowVaccination{" +
                "id=" + id +
                ", cow_batch='" + cow_batch + '\'' +
                ", vaccine_name='" + vaccine_name + '\'' +
                ", vaccination_date='" + vaccination_date + '\'' +
                ", vaccination_number=" + vaccination_number +
                ", total_cow=" + total_cow +
                '}';
    }
}
