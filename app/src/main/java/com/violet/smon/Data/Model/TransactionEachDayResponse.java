package com.violet.smon.Data.Model;

import android.icu.text.DateFormat;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionEachDayResponse {

    public TransactionEachDayResponse() {
    }

    public TransactionEachDayResponse(String time, int diffence, List<Change> data) {
        this.time = time;
        this.diffence = diffence;
        this.data = data;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDiffence() {
        return diffence;
    }

    public void setDiffence(int diffence) {
        this.diffence = diffence;
    }

    public List<Change> getData() {
        return data;
    }

    public void setData(List<Change> data) {
        this.data = data;
    }

    public  String getFormattedDiffrence(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String convertedMonney = currencyVN.format(getDiffence());
        return convertedMonney;
    }
    public String getDowFormatted() throws ParseException {
        Locale localeVN = new Locale("vi", "VN");
        return DateFormat.getPatternInstance(DateFormat.WEEKDAY).format(getDateObject());
    }
    public String getFullMothFormatted() throws ParseException {
        Locale localeVN = new Locale("vi", "VN");
        return DateFormat.getPatternInstance(DateFormat.MONTH).format(getDateObject());
    }
    public String getDay() throws ParseException {
        Locale localeVN = new Locale("vi", "VN");
        return DateFormat.getPatternInstance(DateFormat.DAY).format(getDateObject());
    }
    public String getYear() throws ParseException {
        Locale localeVN = new Locale("vi", "VN");
        return DateFormat.getPatternInstance(DateFormat.YEAR).format(getDateObject());
    }

    public String time;
    public int diffence;
    List<Change> data;

    public Date getDateObject() throws ParseException {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateObj = simpledateformat.parse(getTime());
       return dateObj;
    }
}
