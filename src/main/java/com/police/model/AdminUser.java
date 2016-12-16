package com.police.model;


import javax.persistence.*;

/**
 * Created by liyy on 16/10/17.
 */
@Entity
@Table(name = "adminuser")
public class AdminUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String password;

    public AdminUser(){

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public AdminUser(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
