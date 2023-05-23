package com.example.dpaper_shop.Model;

public class Users
{
    private String username, phone, image, password;

    public Users()
    {

    }

    public Users(String name, String phone, String pass) {
        this.username = name;
        this.phone = phone;
        this.password = pass;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {this.image = image;
    }


    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return password;
    }

    public void setPass(String pass) {
        this.password = pass;
    }
}
