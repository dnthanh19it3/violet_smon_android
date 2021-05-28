package com.violet.smon.Data.Model;

import java.util.Date;

public class Profile {
    public int id;
    public String uid;
    public String display_name;
    public String dob;
    public String gender;

    public Profile() {
    }

    public Profile(int id, String uid, String display_name, String dob, String gender) {
        this.id = id;
        this.uid = uid;
        this.display_name = display_name;
        this.dob = dob;
        this.gender = gender;
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

    public String getDisplay_name() {
        return display_name;
    }
    public String getHello() {
        return "Chào " + display_name;
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
