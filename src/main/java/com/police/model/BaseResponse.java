package com.police.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by liyy on 16/10/20.
 */
public class BaseResponse {
    private int code;
    private String msg;
    private Object data;


    public BaseResponse(int code,String msg){
        this.code = code;
        this.msg = msg;
        this.data = Collections.emptyMap();
    }
    public BaseResponse(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
