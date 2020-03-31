package com.news.analysis.pojo;

public class Menu {
    private int menu_id;        //菜单id
    private String menu_name;   //菜单名称

    public Menu() {
        super();
    }

    public Menu(int menu_id, String menu_name) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                '}';
    }
}
