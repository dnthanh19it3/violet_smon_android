package com.violet.smon.Data.Model;

public class LoginResponse {
    int code;
    String msg;
    Users user;

    public LoginResponse() {
    }
    public LoginResponse(int code, String msg, Users user) {
        this.code = code;
        this.msg = msg;
        this.user = user;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
