package com.violet.smon.Data.Model;

public class AuthResponse {
    private int code;
    private  String msg;
    private String new_token;
    private User_details user_data;
    private Account wallet_data;

    public AuthResponse() {
    }

    public AuthResponse(int code, String msg, String new_token, User_details user_data, Account wallet_data) {
        this.code = code;
        this.msg = msg;
        this.new_token = new_token;
        this.user_data = user_data;
        this.wallet_data = wallet_data;
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

    public String getNew_token() {
        return new_token;
    }

    public void setNew_token(String new_token) {
        this.new_token = new_token;
    }

    public User_details getUser_data() {
        return user_data;
    }

    public void setUser_data(User_details user_data) {
        this.user_data = user_data;
    }

    public Account getWallet_data() {
        return wallet_data;
    }

    public void setWallet_data(Account wallet_data) {
        this.wallet_data = wallet_data;
    }
}
