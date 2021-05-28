package com.violet.smon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.Profile;
import com.violet.smon.Data.Model.SimpleResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuaHoSo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText txt_name, txt_dob, txt_gender;
    ImageButton btn_luu;
    String thoi_gian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_ho_so);
        initView();
        loadData(MainActivity.user_id);



        txt_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile(MainActivity.user_id, txt_name.getText().toString(), txt_dob.getText().toString(), txt_gender.getText().toString());
            }
        });
    }
    void initView(){
        txt_name = findViewById(R.id.txt_name);
        txt_gender = findViewById(R.id.txt_gender);
        txt_dob = findViewById(R.id.txt_dob);
        btn_luu = findViewById(R.id.btn_hs_luu);

        txt_dob.setKeyListener(null);
        txt_dob.setCursorVisible(false);
        txt_dob.setPressed(false);
        txt_dob.setFocusable(false);
    }
    public void loadData(int user_id){
        LaravelApi.laravelApi.getProfile(user_id).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.body() != null){
                    Profile profile = response.body();
                    try {
                        txt_name.setText(profile.getDisplay_name());
                        txt_dob.setText(profile.getDob().toString());
                        txt_gender.setText(profile.getGender());
                    } catch (Exception e){
                        Log.e("Loi ho so", e.toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.e("Loi ho so", t.toString());
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
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        thoi_gian = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
        txt_dob.setText(thoi_gian);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    public void updateProfile(int user_id, String name, String dob, String gender){
        LaravelApi.laravelApi.editProfile(user_id, name, dob, gender).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if(response.body() != null){
                    SimpleResponse simpleResponse = response.body();
                    if(simpleResponse.getCode() == 1){
                        Toast.makeText(SuaHoSo.this, simpleResponse.getMsg(), Toast.LENGTH_LONG).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("RESULT", 1);
                        finish();
                    } else {
                        Toast.makeText(SuaHoSo.this, simpleResponse.getMsg(), Toast.LENGTH_LONG).show();
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("RESULT", 0);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {

            }
        });
    }
}