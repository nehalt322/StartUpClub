package com.example.startupclub;

public class Userdata {
    String name,email,phone,userId;

    public Userdata() {
    }

    public Userdata(String name, String email, String phone, String userId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }


}
