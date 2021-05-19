package com.violet.smon.Data.Model;

import java.util.Date;

public class Users{

private String username;
private String password;
private String status;
private String facebook_id;
private String token;
private java.util.Date token_due;
private java.util.Date last_login;
private int login_time;

    public Users() {
    }

    public Users(String username, String password, String status, String facebook_id, String token, Date token_due, Date last_login, int login_time) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.facebook_id = facebook_id;
        this.token = token;
        this.token_due = token_due;
        this.last_login = last_login;
        this.login_time = login_time;
    }

    public String getUsername(){
        return username;
        }

public void setUsername(String username){
        this.username=username;
        }

public String getPassword(){
        return password;
        }

public void setPassword(String password){
        this.password=password;
        }

public String getStatus(){
        return status;
        }

public void setStatus(String status){
        this.status=status;
        }

public String getFacebook_id(){
        return facebook_id;
        }

public void setFacebook_id(String facebook_id){
        this.facebook_id=facebook_id;
        }

public String getToken(){
        return token;
        }



    public void setToken(String token){
        this.token=token;
        }

public java.util.Date getToken_due(){
        return token_due;
        }

public void setToken_due(java.util.Date token_due){
        this.token_due=token_due;
        }

public java.util.Date getLast_login(){
        return last_login;
        }

public void setLast_login(java.util.Date last_login){
        this.last_login=last_login;
        }

public int getLogin_time(){
        return login_time;
        }

public void setLogin_time(int login_time){
        this.login_time=login_time;
        }
        }