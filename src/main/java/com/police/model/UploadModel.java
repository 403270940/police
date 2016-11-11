package com.police.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liyy on 16/11/9.
 */
@Entity
@Table(name = "upload")
public class UploadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String uid;
    private String location;
    private Date createTime;
    private String filepath;
    private String comment;
    private String type;

    public UploadModel(){}

    public UploadModel( String uid, String location, Date createTime, String filepath, String comment, String type) {
        this.uid = uid;
        this.location = location;
        this.createTime = createTime;
        this.filepath = filepath;
        this.comment = comment;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
