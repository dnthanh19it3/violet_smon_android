package com.violet.smon.Data.Model;

import com.anychart.chart.common.dataentry.DataEntry;

import java.util.ArrayList;
import java.util.List;

public class ReportResponse {
    private int incom;
    private int outgo;
    private int diffence;
    ArrayList<ValuePair> outgo_data;

    public ReportResponse(int incom, int outgo, int diffence, ArrayList<ValuePair> outgo_data) {
        this.incom = incom;
        this.outgo = outgo;
        this.diffence = diffence;
        this.outgo_data = outgo_data;
    }

    public ReportResponse() {
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

    public int getDiffence() {
        return diffence;
    }

    public void setDiffence(int diffence) {
        this.diffence = diffence;
    }

    public ArrayList<ValuePair> getOutgo_data() {
        return outgo_data;
    }

    public void setOutgo_data(ArrayList<ValuePair> outgo_data) {
        this.outgo_data = outgo_data;
    }
}
