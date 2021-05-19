package com.violet.smon.Data.Model;

import android.icu.text.NumberFormat;

import java.util.Locale;

public class Change_type{
        public Change_type(int id, String name, int method, String icon) {
                this.id = id;
                this.name = name;
                this.method = method;
                this.icon = icon;
        }

        public Change_type() {
        }

        private int id;
private String name;
private int method;
private String icon;
public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }
public String getName(){
        return name;
        }

public void setName(String name){
        this.name=name;
        }

public int getMethod(){
        return method;
        }

public void setMethod(int method){
        this.method=method;
        }

public String getIcon(){
        return icon;
        }

public void setIcon(String icon){
        this.icon=icon;
        }
        }


