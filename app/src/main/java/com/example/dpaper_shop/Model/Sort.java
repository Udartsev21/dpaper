package com.example.dpaper_shop.Model;

public class Sort {
    String  name, color, simg;
    public Sort(String name, String color, String simg) {
        this.name = name;
        this.color = color;
        this.simg = simg;
    }

    public String getSimg() {
        return simg;
    }

    public void setSimg(String simg) {
        this.simg = simg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
