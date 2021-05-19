package com.violet.smon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.SimpleResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemGiaoDich extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    final int RESULT_DANHMUC = 1;
    final int RESULT_THOIGIAN = 2;
    final int RESULT_VI = 3;

    // Form data
    public String loai_giao_dich_text;
    public int  loai_giao_dich_key;
    public int transaction_type;
    public String ghichu;
    public String thoi_gian;
    public int vi = 1;
    public int ammount;
    // View Element
    EditText txt_so_tien, txt_nhom, txt_ghi_chu, txt_thoi_gian, txt_vi;
    Button btn_them_giao_dich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giao_dich);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        txt_ghi_chu = findViewById(R.id.txt_ghi_chu);
        txt_nhom = findViewById(R.id.txt_nhom);
        txt_so_tien = findViewById(R.id.txt_so_tien);
        txt_thoi_gian = findViewById(R.id.txt_thoi_gian);
        txt_vi = findViewById(R.id.txt_vi);
        btn_them_giao_dich = findViewById(R.id.btnThemGiaoDich);


        txt_nhom.setKeyListener(null);
        txt_nhom.setCursorVisible(false);
        txt_nhom.setPressed(false);
        txt_nhom.setFocusable(false);

        txt_thoi_gian.setKeyListener(null);
        txt_thoi_gian.setCursorVisible(false);
        txt_thoi_gian.setPressed(false);
        txt_nhom.setFocusable(false);

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
                ammount = Integer.parseInt(String.valueOf(txt_so_tien.getText()));
                ghichu = String.valueOf(txt_ghi_chu.getText());

                addTransactiom(MainActivity.user_id, transaction_type, vi, loai_giao_dich_key, ammount, ghichu, thoi_gian);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(ThemGiaoDich.this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();
        if (requestCode == 1){
            if (resultCode == RESULT_DANHMUC) {
                loai_giao_dich_key = data.getIntExtra("LOAI_GIAO_DICH_KEY", 404);
                transaction_type = data.getIntExtra("TRANSACTION_TYPE", 404);
                txt_nhom.setText(data.getStringExtra("LOAI_GIAO_DICH_TEXT"));

            }
        }
    }

    public void showDatePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.e("Thang", String.valueOf(month));
        thoi_gian = String.format("%d-%d-%d", year, month+1, dayOfMonth);
        txt_thoi_gian.setText(thoi_gian);
    }
    public void addTransactiom(int user_id, int transaction_type, int account_id, int type, int ammount, String content, String time){
        LaravelApi.laravelApi.storeTransaction(user_id, transaction_type, account_id, type, ammount, content, time).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                Toast.makeText(ThemGiaoDich.this, "Thêm giao dịch thành công!", Toast.LENGTH_LONG).show();
                Log.e("LogAdd", response.body().toString());
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Toast.makeText(ThemGiaoDich.this, "Thêm giao dịch thất bại, vui lòng kiểm tra lại!", Toast.LENGTH_LONG).show();
            }
        });
    }

}