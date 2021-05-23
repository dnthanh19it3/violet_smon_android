package com.violet.smon.Data.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "auth_data")
public class AuthData {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "uid")
    int uid;
    @ColumnInfo(name="token")
    String token;

    public AuthData() {
    }

    public AuthData(int uid, String token) {
        this.uid = uid;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
