package com.vcyber.myframe.base;


import java.io.Serializable;


/**
 * Create by zjl on 2019/5/6
 * ---- 网络请求解析基类，主要解析最外层数据 ----
 */
public class BaseResultBean implements Serializable {
    private int code;
    private String msg;


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
}
