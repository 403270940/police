package com.police.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liyy on 16/10/27.
 */
@Entity
@Table(name = "captcha")
public class Captcha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String captcha;
    private Date created_at;

    public Captcha(String phone,String captcha) {
        this.phone = phone;
        this.captcha = captcha;
        this.created_at = new Date();
    }

    public int getId(){
        return id;
    }
    public String getCaptcha() {
        return captcha;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
