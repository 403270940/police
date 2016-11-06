package com.police.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liyy on 16/11/5.
 */
@Entity
@Table(name = "loginfo")
public class LogInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int uid;
    private String token;
    private Date created_at;
    private Date failed_at;

    //token 失效时间
    private long token_fail_time = 7 * 24 * 60 * 60 * 1000;

    public LogInfo(){

    }

    public LogInfo(int uid, String token) {
        this.uid = uid;
        this.token = token;
        this.created_at = new Date();
        this.failed_at = new Date(System.currentTimeMillis() + token_fail_time);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getFailed_at() {
        return failed_at;
    }

    public void setFailed_at(Date failed_at) {
        this.failed_at = failed_at;
    }
}
