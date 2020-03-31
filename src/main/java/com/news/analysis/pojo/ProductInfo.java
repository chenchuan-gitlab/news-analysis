package com.news.analysis.pojo;

public class ProductInfo {
    private int id;                 //id
    private String product_type;    //产品类型
    private String product_name;   //产品名称
    private String energy_rate;     //能量营养参考价值
    private String protein_rate;    //蛋白质营养参考价值
    private String fat_conten_rate; //脂肪营养参考价值
    private String carbohydrate_rate;   //碳水化合物营养参考价值
    private String sodium_rate;     //钠营养参考价值
    private String energy_content;  //能量含量
    private String protein_content; //蛋白质含量
    private String fat_conten_content;  //脂肪含量
    private String carbohydrate_content;    //碳水化合物含量
    private String sodium_content;      //钠含量
    private String img_url;              //产品图片路径
    private String expiration_date;

    public ProductInfo() {
        super();
    }

    public ProductInfo(int id, String product_type, String product_name, String energy_rate, String protein_rate, String fat_conten_rate, String carbohydrate_rate, String sodium_rate, String energy_content, String protein_content, String fat_conten_content, String carbohydrate_content, String sodium_content, String img_url, String expiration_date) {
        this.id = id;
        this.product_type = product_type;
        this.product_name = product_name;
        this.energy_rate = energy_rate;
        this.protein_rate = protein_rate;
        this.fat_conten_rate = fat_conten_rate;
        this.carbohydrate_rate = carbohydrate_rate;
        this.sodium_rate = sodium_rate;
        this.energy_content = energy_content;
        this.protein_content = protein_content;
        this.fat_conten_content = fat_conten_content;
        this.carbohydrate_content = carbohydrate_content;
        this.sodium_content = sodium_content;
        this.img_url = img_url;
        this.expiration_date = expiration_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEnergy_rate() {
        return energy_rate;
    }

    public void setEnergy_rate(String energy_rate) {
        this.energy_rate = energy_rate;
    }

    public String getProtein_rate() {
        return protein_rate;
    }

    public void setProtein_rate(String protein_rate) {
        this.protein_rate = protein_rate;
    }

    public String getFat_conten_rate() {
        return fat_conten_rate;
    }

    public void setFat_conten_rate(String fat_conten_rate) {
        this.fat_conten_rate = fat_conten_rate;
    }

    public String getCarbohydrate_rate() {
        return carbohydrate_rate;
    }

    public void setCarbohydrate_rate(String carbohydrate_rate) {
        this.carbohydrate_rate = carbohydrate_rate;
    }

    public String getSodium_rate() {
        return sodium_rate;
    }

    public void setSodium_rate(String sodium_rate) {
        this.sodium_rate = sodium_rate;
    }

    public String getEnergy_content() {
        return energy_content;
    }

    public void setEnergy_content(String energy_content) {
        this.energy_content = energy_content;
    }

    public String getProtein_content() {
        return protein_content;
    }

    public void setProtein_content(String protein_content) {
        this.protein_content = protein_content;
    }

    public String getFat_conten_content() {
        return fat_conten_content;
    }

    public void setFat_conten_content(String fat_conten_content) {
        this.fat_conten_content = fat_conten_content;
    }

    public String getCarbohydrate_content() {
        return carbohydrate_content;
    }

    public void setCarbohydrate_content(String carbohydrate_content) {
        this.carbohydrate_content = carbohydrate_content;
    }

    public String getSodium_content() {
        return sodium_content;
    }

    public void setSodium_content(String sodium_content) {
        this.sodium_content = sodium_content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", product_type='" + product_type + '\'' +
                ", product_name='" + product_name + '\'' +
                ", energy_rate='" + energy_rate + '\'' +
                ", protein_rate='" + protein_rate + '\'' +
                ", fat_conten_rate='" + fat_conten_rate + '\'' +
                ", carbohydrate_rate='" + carbohydrate_rate + '\'' +
                ", sodium_rate='" + sodium_rate + '\'' +
                ", energy_content='" + energy_content + '\'' +
                ", protein_content='" + protein_content + '\'' +
                ", fat_conten_content='" + fat_conten_content + '\'' +
                ", carbohydrate_content='" + carbohydrate_content + '\'' +
                ", sodium_content='" + sodium_content + '\'' +
                ", img_url='" + img_url + '\'' +
                ", expiration_date='" + expiration_date + '\'' +
                '}';
    }
}
