package com.news.analysis.pojo;

import org.springframework.stereotype.Component;

/**
 * 职员
 */
@Component
public class Staff {
    private String staff_id;    //员工id
    private String staff_pass;  //员工密码
    private String staff_name;  //员工姓名
    private String sex;         //性别
    private String phone;       //电话
    private String role_id;        //角色id

    public Staff() {
        super();
    }

    public Staff(String staff_id, String staff_pass, String staff_name, String sex, String phone, String role_id) {
        this.staff_id = staff_id;
        this.staff_pass = staff_pass;
        this.staff_name = staff_name;
        this.sex = sex;
        this.phone = phone;
        this.role_id = role_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_pass() {
        return staff_pass;
    }

    public void setStaff_pass(String staff_pass) {
        this.staff_pass = staff_pass;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staff_id='" + staff_id + '\'' +
                ", staff_pass='" + staff_pass + '\'' +
                ", staff_name='" + staff_name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
