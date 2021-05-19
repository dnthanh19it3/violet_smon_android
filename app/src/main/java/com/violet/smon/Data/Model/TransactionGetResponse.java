package com.violet.smon.Data.Model;

import android.icu.text.NumberFormat;

import java.util.Locale;

public class TransactionGetResponse {
    public int month, incom, outgo;
    public TransactionGetResponse() {
    }

    public TransactionGetResponse(int month, int incom, int outgo) {
        this.month = month;
        this.incom = incom;
        this.outgo = outgo;
    }


    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getIncom() {
        return incom;
    }

    public void setIncom(int incom) {
        this.incom = incom;
    }

    public int getOutgo() {
        return outgo;
    }

    public void setOutgo(int outgo) {
        this.outgo = outgo;
    }

    public  String getFormattedIncom(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String convertedMonney = currencyVN.format(incom);
        return convertedMonney;
    }
    public  String getFormattedOutgo(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String convertedMonney = currencyVN.format(outgo);
        return convertedMonney;
    }
    public int getDiffrence(){
        return incom - outgo;
    }
    public  String getFormattedDiffrence(){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String convertedMonney = currencyVN.format(getDiffrence());
        return convertedMonney;
    }


}
