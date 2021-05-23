package com.violet.smon.Data.Model;

import android.icu.text.NumberFormat;

import java.util.Locale;

public class Account {
    private int id;
    private int user_id;
    private int balance;

    public Account() {
    }

    public Account(int id, int user_id, int balance) {
        this.id = id;
        this.user_id = user_id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public  String getFormattedBalance(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String convertedMonney = currencyVN.format(balance);
        return convertedMonney;
    }
}
