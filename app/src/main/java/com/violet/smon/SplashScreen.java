package com.violet.smon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private  boolean loginFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(!loginFlag){
            Intent intentLogin =  new Intent(this, LoginActivity.class);
            startActivity(intentLogin);
        }
    }

}