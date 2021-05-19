package com.violet.smon;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.violet.smon.Data.Adapter.ChonLoaiGiaoDichAdapter;
import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.Change_type;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubActivityChonDanhMuc extends AppCompatActivity {
    RecyclerView recyclerView;
    TabLayout tabThuChi;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_chon_danh_muc);
        tabThuChi = findViewById(R.id.tab_thuchi);
        // Remove Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        renderThu();

        tabThuChi.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        renderThu();
                        break;
                    case 1:
                        renderChi();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void renderThu(){
        LaravelApi.laravelApi.getChangeTypeThu().enqueue(new Callback<List<Change_type>>() {
            public void onResponse(Call<List<Change_type>> call, Response<List<Change_type>> response) {
                List<Change_type> change_types = response.body();
                Log.e("Check Respond", String.valueOf(response.body()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubActivityChonDanhMuc.this);
                Log.e("CheckPoint", "Da di qua 1");
                ChonLoaiGiaoDichAdapter adapterLoaiGiaoDich = new ChonLoaiGiaoDichAdapter(change_types, SubActivityChonDanhMuc.this);
                recyclerView = findViewById(R.id.recycle_view_thu);
                Log.e("CheckPoint", "Da di qua 2");
                recyclerView.setAdapter(adapterLoaiGiaoDich);
                Log.e("CheckPoint", "Da di qua 3");
                recyclerView.setLayoutManager(linearLayoutManager);
            }
            @Override
            public void onFailure(Call<List<Change_type>> call, Throwable t) {
                Log.e("Loi", t.toString());
            }
        });
    }
    public void renderChi(){
        LaravelApi.laravelApi.getChangeTypeChi().enqueue(new Callback<List<Change_type>>() {
            public void onResponse(Call<List<Change_type>> call, Response<List<Change_type>> response) {
                List<Change_type> change_types = response.body();
                Log.e("Check Respond", String.valueOf(response.body()));
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SubActivityChonDanhMuc.this);
                Log.e("CheckPoint", "Da di qua 1");
                ChonLoaiGiaoDichAdapter adapterLoaiGiaoDich = new ChonLoaiGiaoDichAdapter(change_types, SubActivityChonDanhMuc.this);
                recyclerView = findViewById(R.id.recycle_view_thu);
                Log.e("CheckPoint", "Da di qua 2");
                recyclerView.setAdapter(adapterLoaiGiaoDich);
                Log.e("CheckPoint", "Da di qua 3");
                recyclerView.setLayoutManager(linearLayoutManager);
            }
            @Override
            public void onFailure(Call<List<Change_type>> call, Throwable t) {
                Log.e("Loi", t.toString());
            }
        });
    }
}