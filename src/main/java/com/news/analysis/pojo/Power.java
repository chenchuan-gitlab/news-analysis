package com.news.analysis.pojo;

public class Power {
    private int power_id;           //权限id
    private String power_name;      //权限名称
    private String resource_path;   //资源路径
    private int menu_id;            //菜单id

    public Power() {
        super();
    }

    public Power(int power_id, String power_name, String resource_path, int menu_id) {
        this.power_id = power_id;
        this.power_name = power_name;
        this.resource_path = resource_path;
        this.menu_id = menu_id;
    }

    public int getPower_id() {
        return power_id;
    }

    public void setPower_id(int power_id) {
        this.power_id = power_id;
    }

    public String getPower_name() {
        return power_name;
    }

    public void setPower_name(String power_name) {
        this.power_name = power_name;
    }

    public String getResource_path() {
        return resource_path;
    }

    public void setResource_path(String resource_path) {
        this.resource_path = resource_path;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    @Override
    public String toString() {
        return "Power{" +
                "power_id=" + power_id +
                ", power_name='" + power_name + '\'' +
                ", resource_path='" + resource_path + '\'' +
                ", menu_id=" + menu_id +
                '}';
    }
}
