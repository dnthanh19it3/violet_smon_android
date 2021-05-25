package com.violet.smon.Data.Model;

public class ValuePair {
    String x;
    int value;

    public ValuePair() {
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ValuePair(String x, int value) {
        this.x = x;
        this.value = value;
    }
}
