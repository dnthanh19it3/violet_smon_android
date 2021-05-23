package com.violet.smon;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.DAO.AuthDAO;
import com.violet.smon.Data.DAO.AuthDatabase;
import com.violet.smon.Data.Model.AuthData;
import com.violet.smon.Data.Model.AuthResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    LoginButton loginButton;
    AccessToken accessToken;
    EditText txt_username, txt_password;
    Button btnLogin, btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(txt_username.getText());
                String password = String.valueOf(txt_password.getText());
                loginNew(username, password);
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(txt_username.getText());
                String password = String.valueOf(txt_password.getText());
                if(username.length() > 0 && password.length() >0){
                    register(username, password);
                }
            }
        });
    }



    public void initView(){
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnReg = findViewById(R.id.btn_reg);
    }
    public void loginNew(String username, String password){
        LaravelApi.laravelApi.loginNew(username, password).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                if(authResponse.getCode() == 1){
                    MainActivity.user_id = authResponse.getUser_data().getId();
                    MainActivity.account_id = authResponse.getWallet_data().getId();
                    Toast.makeText(LoginActivity.this, authResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    AuthData authData = new AuthData();
                    authData.setUid(authResponse.getUser_data().getId());
                    authData.setToken(authResponse.getNew_token());
                    AuthDatabase.getInstance(LoginActivity.this).authDAO().resetTable();
                    AuthDatabase.getInstance(LoginActivity.this).authDAO().insert(authData);
                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, authResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("Loi", t.toString());
            }
        });

    }
    void register(String username, String password){
        LaravelApi.laravelApi.register(username,password).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                AuthResponse authResponse = response.body();
                if(authResponse.getCode() == 1){
                    Toast.makeText(LoginActivity.this, authResponse.getMsg(), Toast.LENGTH_LONG).show();
                    txt_password.setText("");
                    txt_username.setText("");
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                Log.e("Loi dang nhap", t.toString());
            }
        });
    }

//    public void loginNew(String facebook_id){
//        Log.e("Return login", "Vào khối đăng nhập");
//        LaravelApi.laravelApi.loginNew(facebook_id).enqueue(new Callback<AuthResponse>() {
//            @Override
//            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
//                Log.e("Debug log", "Đã request Resful thành công!");
//                AuthResponse authResponse = response.body();
//                Log.e("Debug log", "Đã parse response Resful thành công!");
//                AuthData authData = new AuthData();
//                authData.setUid(authResponse.getUser_data().getUid());
//                authData.setToken(authResponse.getNew_token());
//                //Truyền biến tới Main Act
//                MainActivity.user_id = authResponse.getUser_data().getUid();
//                MainActivity.account_id = authResponse.getWallet_data().getId();
//
//                Log.e("Debug log: Auth Token", authResponse.getNew_token());
//                AuthDatabase.getInstance(LoginActivity.this).authDAO().insert(authData);
//                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
//                LoginActivity.this.finish();
//                startActivity(mainIntent);
//                Log.e("Return login", authResponse.getMsg() + authResponse.getUser_data().getUid());
//            }
//
//            @Override
//            public void onFailure(Call<AuthResponse> call, Throwable t) {
//                Log.e("Return login", t.toString());
//            }
//        });
//    }
}