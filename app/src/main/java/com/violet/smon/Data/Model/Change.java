package com.violet.smon.Data.Model;

import android.icu.text.NumberFormat;

import java.util.Locale;

public class Change extends Change_type{
    private int account_id;
    private int type;
    private int ammount;
    private String content;
    private String with;
    private String is_report;

    public Change() {
    }

    public Change(int id, String name, int method, String icon, int account_id, int type, int ammount, String content, String with, String is_report) {
        super(id, name, method, icon);
        this.account_id = account_id;
        this.type = type;
        this.ammount = ammount;
        this.content = content;
        this.with = with;
        this.is_report = is_report;
    }

    public Change(int account_id, int type, int ammount, String content, String with, String is_report) {
        this.account_id = account_id;
        this.type = type;
        this.ammount = ammount;
        this.content = content;
        this.with = with;
        this.is_report = is_report;
    }

    public int getAccount_id(){
        return account_id;
    }

    public void setAccount_id(int account_id){
        this.account_id=account_id;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type=type;
    }

    public int getAmmount(){
        return ammount;
    }

    public void setAmmount(int ammount){
        this.ammount=ammount;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }

    public String getWith(){
        return with;
    }

    public void setWith(String with){
        this.with=with;
    }

    public String getIs_report(){
        return is_report;
    }

    public void setIs_report(String is_report){
        this.is_report=is_report;
    }
    public  String getFormattedAmmount(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String convertedMonney = currencyVN.format(getAmmount());
        return convertedMonney;
    }
}
