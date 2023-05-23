package com.example.dpaper_shop.Model;

public class Collections {

    int id;
    String img, tittle, country, manifacture, color;


    public Collections(int id, String img, String tittle, String country, String manifacture, String color) {
        this.id = id;
        this.img = img;
        this.tittle = tittle;
        this.country = country;
        this.manifacture = manifacture;
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

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getManifacture() {
        return manifacture;
    }

    public void setManifacture(String manifacture) {
        this.manifacture = manifacture;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
