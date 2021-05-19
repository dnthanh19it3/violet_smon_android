package com.violet.smon;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {



    private  boolean loginFlag = false;
    public static int user_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // Find view



        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRect(100, 100, view.getWidth(), view.getHeight());
            }
        });




        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                    new SoGiaoDichFragment()).commit();
        }

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_so_giao_dich:
                            selectedFragment = new SoGiaoDichFragment();
                            break;
                        case R.id.nav_bao_cao:
                            selectedFragment = new BaoCaoFragment();
                            break;
                        case R.id.nav_lap_ke_hoach:
                            selectedFragment = new LapKeHoachFragment();
                            break;
                        case R.id.nav_tai_khoan:
                            selectedFragment = new TaiKhoanFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                            selectedFragment).commit();
                    return true;
                }
            };

}