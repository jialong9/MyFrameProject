package com.vcyber.myframe.base;


import java.io.Serializable;


/**
 * Create by zjl on 2019/5/6
 * ---- 网络请求解析基类，主要解析最外层数据 ----
 */
public class BaseResultBean implements Serializable {
    private int errorCode;
    private boolean success;
    private String msg;


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
