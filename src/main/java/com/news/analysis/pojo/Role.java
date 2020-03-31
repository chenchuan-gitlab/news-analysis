package com.news.analysis.pojo;

public class Role {
    private String role_id;        //角色id
    private String role_name;   //角色名称
    private int power_id;       //权限id

    public Role() {
        super();
    }

    public Role(String role_id, String role_name, int power_id) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.power_id = power_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getPower_id() {
        return power_id;
    }

    public void setPower_id(int power_id) {
        this.power_id = power_id;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", power_id=" + power_id +
                '}';
    }
}
