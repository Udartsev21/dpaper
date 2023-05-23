package com.example.dpaper_shop.Model;

public class Collections_Page {
    int id;

    String img, pname, desc, price, color;

    public Collections_Page(int id, String img, String pname, String desc, String price, String color) {
        this.id = id;
        this.img = img;
        this.pname = pname;
        this.desc = desc;
        this.price = price;
        this.color = color;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {return color;}

    public void setColor(String color) {
        this.color = color;
    }
}
