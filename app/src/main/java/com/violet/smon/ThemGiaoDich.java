package com.violet.smon;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.SimpleResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemGiaoDich extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private FusedLocationProviderClient fusedLocationClient;

    final int RESULT_DANHMUC = 1;
    final int RESULT_THOIGIAN = 2;
    final int RESULT_VI = 3;

    // Form data
    public String loai_giao_dich_text;
    public int loai_giao_dich_key;
    public int transaction_type;
    public String ghichu;
    public String thoi_gian;
//    public int vi = 1;
    public int ammount = 0;
    public  String dia_diem, voi_ai;
    // View Element
    EditText txt_so_tien, txt_nhom, txt_ghi_chu, txt_thoi_gian, txt_vi, txt_dia_diem, txt_voi_ai;
    ImageButton btn_them_giao_dich, btn_back, btn_get_local;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);
        ActionBar actionBar = getSupportActionBar();
        // Location Services
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        actionBar.hide();
        // Innit view
        initView();
        // Disable Input
        disableInputField();
        // Action Listener
        txt_nhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int LAUNCH_SECOND_ACTIVITY = 1;
                Intent intent = new Intent(ThemGiaoDich.this, SubActivityChonDanhMuc.class);
                startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
            }
        });
        txt_thoi_gian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        btn_them_giao_dich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_so_tien.getText().length() > 0 ){
                    ammount = Integer.parseInt(String.valueOf(txt_so_tien.getText()));
                    ghichu = String.valueOf(txt_ghi_chu.getText());
                    dia_diem = String.valueOf(txt_dia_diem.getText());
                    voi_ai = String.valueOf(txt_voi_ai.getText());
                    addTransactiom(MainActivity.user_id, transaction_type, MainActivity.account_id, loai_giao_dich_key, ammount, ghichu, thoi_gian, voi_ai, dia_diem);
                } else {
                    Toast.makeText(ThemGiaoDich.this, "Vui lòng nhập thông tin!", Toast.LENGTH_LONG).show();
                }


//                if(ammount == 0){
//                    Toast.makeText(ThemGiaoDich.this, "Vui lòng nhập thông tin!", Toast.LENGTH_LONG).show();
//                } else {
//
//                }
//                Log.e("Debug", String.format("Uid %s Wallet %s TransactionType %d", MainActivity.user_id, MainActivity.account_id, transaction_type));

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_get_local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(ThemGiaoDich.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(ThemGiaoDich.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(ThemGiaoDich.this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();
        if (requestCode == 1) {
            if (resultCode == RESULT_DANHMUC) {
                loai_giao_dich_key = data.getIntExtra("LOAI_GIAO_DICH_KEY", 404);
                transaction_type = data.getIntExtra("TRANSACTION_TYPE", 404);
                if (transaction_type == 1) {
                    txt_so_tien.setTextColor(Color.rgb(51, 181, 229));
                } else {
                    txt_so_tien.setTextColor(Color.rgb(255, 68, 68));
                }
                txt_nhom.setText(data.getStringExtra("LOAI_GIAO_DICH_TEXT"));

            }
        }
    }

    public void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.e("Thang", String.valueOf(month));
        thoi_gian = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
        txt_thoi_gian.setText(thoi_gian);
    }

    public void addTransactiom(int user_id, int transaction_type, int account_id, int type, int ammount, String content, String time, String with, String location) {
        LaravelApi.laravelApi.storeTransaction(user_id, transaction_type, account_id, type, ammount, content, time, with, location).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                Log.e("Debug them giao dich", response.toString());
                SimpleResponse simpleResponse = response.body();
                if(simpleResponse.getCode() == 1){
                    Toast.makeText(ThemGiaoDich.this, simpleResponse.getMsg(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ThemGiaoDich.this, simpleResponse.getMsg(), Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(ThemGiaoDich.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Log.e("Loi them giao dich", t.toString());
            }
        });
    }

    @SuppressLint("MissingPermission")
    public void getLocation() {

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            Geocoder geocoder = new Geocoder(ThemGiaoDich.this, Locale.getDefault());
                            try {
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(), 1);
                                Log.e("Dia diem", addresses.get(0).getAddressLine(0));
                                txt_dia_diem.setText(addresses.get(0).getAddressLine(0));

                            } catch (IOException e) {
                                Log.e("Loi vi tri", e.toString());
                            }
                        }
                    }
                });
    }
    void initView(){
        txt_ghi_chu = findViewById(R.id.txt_ghi_chu);
        txt_nhom = findViewById(R.id.txt_nhom);
        txt_so_tien = findViewById(R.id.txt_so_tien);
        txt_thoi_gian = findViewById(R.id.txt_thoi_gian);
        txt_vi = findViewById(R.id.txt_vi);
        txt_dia_diem = findViewById(R.id.txt_dia_diem);
        txt_voi_ai = findViewById(R.id.txt_cung_voi);
        btn_them_giao_dich = findViewById(R.id.btn_cap_nhat_giao_dich);
        btn_back = findViewById(R.id.btn_back);
        btn_get_local = findViewById(R.id.btn_get_local);
    }
    void disableInputField(){
        txt_nhom.setKeyListener(null);
        txt_nhom.setCursorVisible(false);
        txt_nhom.setPressed(false);
        txt_nhom.setFocusable(false);

        txt_thoi_gian.setKeyListener(null);
        txt_thoi_gian.setCursorVisible(false);
        txt_thoi_gian.setPressed(false);
        txt_nhom.setFocusable(false);
    }
}