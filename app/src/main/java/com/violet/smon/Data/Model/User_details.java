package com.violet.smon.Data.Model;

import java.util.Date;

public class User_details extends Users {

    private int uid;
    private String display_name;
    private String dob;
    private String gender;


    public User_details() {
    }

    public User_details(int id, String username, String password, int status, String token, int login_time, int uid, String display_name, String dob, String gender) {
        super(id, username, password, status, token, login_time);
        this.uid = uid;
        this.display_name = display_name;
        this.dob = dob;
        this.gender = gender;
    }

    public User_details(int uid, String display_name, String dob, String gender) {
        this.uid = uid;
        this.display_name = display_name;
        this.dob = dob;
        this.gender = gender;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
