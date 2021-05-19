package com.violet.smon.Data.Model;

import java.util.Date;

public class User_details extends Users {

    private String user_id;
    private String display_name;
    private String dob;
    private String gender;


    public User_details() {
    }

    public User_details(String user_id, String display_name, String dob, String gender) {
        this.user_id = user_id;
        this.display_name = display_name;
        this.dob = dob;
        this.gender = gender;
    }

    public User_details(String username, String password, String status, String facebook_id, String token, Date token_due, Date last_login, int login_time, String user_id, String display_name, String dob, String gender) {
        super(username, password, status, facebook_id, token, token_due, last_login, login_time);
        this.user_id = user_id;
        this.display_name = display_name;
        this.dob = dob;
        this.gender = gender;
    }

    public String getUser_id(){
        return user_id;
    }

    public void setUser_id(String user_id){
        this.user_id=user_id;
    }

    public String getDisplay_name(){
        return display_name;
    }

    public void setDisplay_name(String display_name){
        this.display_name=display_name;
    }

    public String getDob(){
        return dob;
    }

    public void setDob(String dob){
        this.dob=dob;
    }

    public String getGender(){
        return gender;
    }

    public void setGender(String gender){
        this.gender=gender;
    }
}
