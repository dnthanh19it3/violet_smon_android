package com.violet.smon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.DAO.AuthDatabase;
import com.violet.smon.Data.Model.AuthData;
import com.violet.smon.Data.Model.AuthResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    AuthData authData;
    public static final int SPLASH_DISPLAY_LENGTH = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                authData = AuthDatabase.getInstance(SplashScreen.this).authDAO().getAuth();
                if(authData == null){
                    Log.e("Loi dang nhap", "Hiện bạn chưa đăng nhập");
                    Intent intentLogin =  new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intentLogin);
                } else {
                    Log.e("Thông tin từ DB", String.format("UID %d Token %s", authData.getUid(), authData.getToken()));
                    storedLogin(authData.getUid(), authData.getToken());
                }
            }
        }, SPLASH_DISPLAY_LENGTH);


    }
    public void storedLogin(int id, String token){
        LaravelApi.laravelApi.loginOld(id, token).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                if(authResponse.getCode() == 1){
                    Toast.makeText(SplashScreen.this, authResponse.getMsg(), Toast.LENGTH_LONG).show();
                    MainActivity.user_id = authResponse.getUser_data().getId();
                    MainActivity.account_id = authResponse.getWallet_data().getId();
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
//                    Intent intent =  new Intent(SplashScreen.this, MainActivity2.class);
                    SplashScreen.this.startActivity(intent);
                    SplashScreen.this.finish();
                } else {
                    Toast.makeText(SplashScreen.this, authResponse.getMsg(), Toast.LENGTH_LONG).show();
                    Intent intentLogin =  new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intentLogin);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("Loi dang nhap token", t.toString());
            }
        });
    }

}