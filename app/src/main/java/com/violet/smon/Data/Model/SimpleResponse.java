package com.violet.smon.Data.Model;

public class SimpleResponse {
    public int code;
    public String msg;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SimpleResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public SimpleResponse() {
    }



}
