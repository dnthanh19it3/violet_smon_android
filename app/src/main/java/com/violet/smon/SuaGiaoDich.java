package com.violet.smon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.SimpleResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaGiaoDich extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {



    private FusedLocationProviderClient fusedLocationClient;

    final int RESULT_DANHMUC = 1;
    final int RESULT_THOIGIAN = 2;
    final int RESULT_VI = 3;

    // Form data
    public String  danh_muc, ghi_chu, thoi_gian, vi, dia_diem, voi_ai;
    public int danh_muc_id, transaction_id, so_tien, so_tien_raw, danh_muc_raw;
    public int loai_giao_dich_key;
    public int transaction_type, account_id;

    // View Element
    EditText txt_so_tien, txt_nhom, txt_ghi_chu, txt_thoi_gian, txt_vi, txt_dia_diem, txt_voi_ai;
    ImageButton btn_cap_nhat_giao_dich, btn_back, btn_get_local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_giao_dich);
        getSupportActionBar().hide();

        // Find view
        txt_ghi_chu = findViewById(R.id.txt_ghi_chu);
        txt_nhom = findViewById(R.id.txt_nhom);
        txt_so_tien = findViewById(R.id.txt_so_tien);
        txt_thoi_gian = findViewById(R.id.txt_thoi_gian);
        txt_vi = findViewById(R.id.txt_vi);
        txt_dia_diem = findViewById(R.id.txt_dia_diem);
        txt_voi_ai = findViewById(R.id.txt_cung_voi);
        btn_cap_nhat_giao_dich = findViewById(R.id.btn_cap_nhat_giao_dich);
        btn_back = findViewById(R.id.btn_back);
        btn_get_local = findViewById(R.id.btn_get_local);

        // Disable some input

        txt_nhom.setKeyListener(null);
        txt_nhom.setCursorVisible(false);
        txt_nhom.setPressed(false);
        txt_nhom.setFocusable(false);

        txt_thoi_gian.setKeyListener(null);
        txt_thoi_gian.setCursorVisible(false);
        txt_thoi_gian.setPressed(false);
        txt_nhom.setFocusable(false);

        // Get var from last intent
        so_tien_raw = getIntent().getIntExtra("EX_SO_TIEN_RAW", 1);
        danh_muc = getIntent().getStringExtra("EX_DANH_MUC");
        ghi_chu = getIntent().getStringExtra("EX_GHI_CHU");
        thoi_gian = getIntent().getStringExtra("EX_THOI_GIAN_RAW");
        vi  = getIntent().getStringExtra("EX_VI");
        dia_diem = getIntent().getStringExtra("EX_DIA_DIEM");
        voi_ai = getIntent().getStringExtra("EX_VOI_AI");
        danh_muc_raw = getIntent().getIntExtra("EX_DANH_MUC_RAW", 1);
        loai_giao_dich_key = danh_muc_raw;
        transaction_type = getIntent().getIntExtra("EX_TRANSACTION_TYPE", 1);;
        transaction_id = getIntent().getIntExtra("EX_TRANSACTION_ID", 1);
        account_id = getIntent().getIntExtra("EX_ACCOUNT_ID", 1);

        // Set text to view
        txt_so_tien.setText(String.valueOf(so_tien_raw));
        txt_nhom.setText(danh_muc);
        txt_ghi_chu.setText(ghi_chu);
        txt_thoi_gian.setText(thoi_gian);
        txt_vi.setText(vi);
        txt_dia_diem.setText(dia_diem);
        txt_voi_ai.setText(voi_ai);

        txt_thoi_gian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SuaGiaoDich.this, "Đã click back", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        txt_nhom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int LAUNCH_SECOND_ACTIVITY = 1;
                Intent intent = new Intent(SuaGiaoDich.this, SubActivityChonDanhMuc.class);
                startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
            }
        });
        btn_cap_nhat_giao_dich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                so_tien = Integer.parseInt(String.valueOf(txt_so_tien.getText()));
                ghi_chu = String.valueOf(txt_ghi_chu.getText());
                dia_diem = String.valueOf(txt_dia_diem.getText());
                voi_ai = String.valueOf(txt_voi_ai.getText());

                storeUpdate(MainActivity.user_id, transaction_id, transaction_type, account_id, so_tien_raw, loai_giao_dich_key, so_tien, ghi_chu, thoi_gian, voi_ai, dia_diem);
//                Log.e("Debug", String.format("%d %d %d %d %d %d %d %s %s %s %s", MainActivity.user_id, transaction_id, transaction_type, account_id, so_tien_raw, loai_giao_dich_key, so_tien, ghi_chu, thoi_gian, voi_ai, dia_diem));


                    Log.e("Debug", String.format("%d %d %d %d %d %d %d %s %s %s %s", MainActivity.user_id, transaction_id, transaction_type, account_id, so_tien_raw, loai_giao_dich_key, so_tien, ghi_chu, thoi_gian, voi_ai, dia_diem));


            }
        });
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(SuaGiaoDich.this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();
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
    public void storeUpdate(int user_id, int transaction_id, int transaction_type, int account_id, int previous_ammount, int type, int ammount, String content, String time, String with, String location){
        Log.e("view log", "pass hết");
        LaravelApi.laravelApi.getUpdate(user_id, transaction_id, transaction_type, account_id, previous_ammount, type, ammount, content, time, with, location).enqueue(new Callback<SimpleResponse>() {
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                SimpleResponse simpleResponse = response.body();
                Toast.makeText(SuaGiaoDich.this, simpleResponse.getMsg(), Toast.LENGTH_LONG).show();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("FLAG", 1);
                setResult(1, returnIntent);
                finish();
            }
            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Log.e("Loi", t.toString());
                Toast.makeText(SuaGiaoDich.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}