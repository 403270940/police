package com.police.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by liyy on 16/10/24.
 */
@Entity
@Table(name = "log")
public class LogEvent {
    @Id
    private int id;
    private String phone;
    private String msg;

    private Date created_at;
    
    public LogEvent(String phone, String msg) {
        this.phone = phone;
        this.msg = msg;
        this.created_at = new Date();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return created_at.toString() + ":" + phone + ":" + msg;
    }
}
