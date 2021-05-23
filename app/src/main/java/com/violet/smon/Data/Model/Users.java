package com.violet.smon.Data.Model;

class Users {
    int id;
    private String username;
    private String password;
    private int status;
    private String token;
    private int login_time;

    public Users() {
    }

    public Users(int id, String username, String password, int status, String token, int login_time) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.token = token;
        this.login_time = login_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLogin_time() {
        return login_time;
    }

    public void setLogin_time(int login_time) {
        this.login_time = login_time;
    }
}