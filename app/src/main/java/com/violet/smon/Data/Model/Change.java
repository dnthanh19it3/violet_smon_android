package com.violet.smon.Data.Model;

import android.icu.text.DateFormat;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class Change extends Change_type{
    private int account_id;
    private int change_id;
    private int type;
    private int ammount;
    private String content;
    private String with;
    private String is_report;
    private String time;
    private String location;

    public Change() {
    }

    public Change(int id, String name, int method, String icon, int change_id, int account_id, int type, int ammount, String content, String with, String is_report, String time, String location) {
        super(id, name, method, icon);
        this.change_id = change_id;
        this.account_id = account_id;
        this.type = type;
        this.ammount = ammount;
        this.content = content;
        this.with = with;
        this.is_report = is_report;
        this.time = time;
        this.location = location;
    }


//    public Change(int id, String name, int method, String icon, int account_id, int type, int ammount, String content, String with, String is_report, String time, String location) {
//        super(id, name, method, icon);
//        this.account_id = account_id;
//        this.type = type;
//        this.ammount = ammount;
//        this.content = content;
//        this.with = with;
//        this.is_report = is_report;
//        this.time = time;
//        this.location = location;
//    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Change(int change_id, int account_id, int type, int ammount, String content, String with, String is_report, String time, String location) {
        this.change_id = change_id;
        this.account_id = account_id;
        this.type = type;
        this.ammount = ammount;
        this.content = content;
        this.with = with;
        this.is_report = is_report;
        this.time = time;
        this.location = location;
    }

    public int getChange_id() {
        return change_id;
    }

    public void setChange_id(int change_id) {
        this.change_id = change_id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public  String getFormattedAmmount(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String convertedMonney = currencyVN.format(getAmmount());
        return convertedMonney;
    }
    public String getFullTime() throws ParseException {
        Locale localeVN = new Locale("vi", "VN");
        return DateFormat.getDateInstance(DateFormat.FULL, localeVN).format(getDateObject());
    }
    public Date getDateObject() throws ParseException {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = simpledateformat.parse(getTime());
        return dateObj;
    }
}
