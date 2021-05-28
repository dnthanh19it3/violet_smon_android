package com.violet.smon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class XemVaSuaActivity extends AppCompatActivity {

    String so_tien, danh_muc, ghi_chu, thoi_gian, vi, dia_diem, voi_ai, thoi_gian_raw;
    int so_tien_raw, danh_muc_raw, transaction_id, account_id, cate_id;
    int iconList[];

    TextView lb_so_tien, lb_danh_muc, lb_ghi_chu, lb_thoi_gian, lb_vi, lb_dia_diem, lb_voi_ai;
    ImageView icon;
    int type, transaction_type;
    FrameLayout frameLayout;
    ImageButton btn_back, btn_sua_giao_dich;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_va_sua);
        initIconList();
        initView();

        // Get var from last intent
        so_tien = getIntent().getStringExtra("EX_SO_TIEN");
        so_tien_raw = getIntent().getIntExtra("EX_SO_TIEN_RAW", 1);
        Log.e("Second pass", String.valueOf(so_tien_raw));
        danh_muc = getIntent().getStringExtra("EX_DANH_MUC");
        ghi_chu = getIntent().getStringExtra("EX_GHI_CHU");
        thoi_gian = getIntent().getStringExtra("EX_THOI_GIAN");
        vi = getIntent().getStringExtra("EX_VI");
        dia_diem = getIntent().getStringExtra("EX_DIA_DIEM");
        voi_ai = getIntent().getStringExtra("EX_VOI_AI");
        type = getIntent().getIntExtra("EX_TYPE", 1);
        danh_muc_raw = getIntent().getIntExtra("EX_DANH_MUC_RAW", 1);
        transaction_id = getIntent().getIntExtra("EX_TRANSACTION_ID", 1);
        account_id = getIntent().getIntExtra("EX_ACCOUNT_ID", 1);
        transaction_type = getIntent().getIntExtra("EX_TRANSACTION_TYPE", 1);
        thoi_gian_raw = getIntent().getStringExtra("EX_THOI_GIAN_RAW");
        cate_id = getIntent().getIntExtra("EX_CATE_ID", 1);


        //Set text
        lb_so_tien.setText(so_tien);
        lb_danh_muc.setText(danh_muc);
        lb_ghi_chu.setText(ghi_chu);
        lb_thoi_gian.setText(thoi_gian);
        lb_vi.setText(vi);
        lb_dia_diem.setText(dia_diem);
        lb_voi_ai.setText(voi_ai);
        icon.setImageDrawable(getDrawable(iconList[cate_id]));

        if (type == 1) {
            frameLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_gra_thu));
        } else {
            frameLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_gra_chi));
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_sua_giao_dich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XemVaSuaActivity.this, SuaGiaoDich.class);
                intent.putExtra("EX_SO_TIEN_RAW", so_tien_raw);
                Log.e("Log before send", String.valueOf(so_tien_raw));
                intent.putExtra("EX_DANH_MUC", danh_muc);
                intent.putExtra("EX_DANH_MUC_RAW", danh_muc_raw);
                intent.putExtra("EX_GHI_CHU", ghi_chu);
                intent.putExtra("EX_THOI_GIAN", thoi_gian);
                intent.putExtra("EX_THOI_GIAN_RAW", thoi_gian_raw);
                intent.putExtra("EX_VI", "nope");
                intent.putExtra("EX_DIA_DIEM", dia_diem);
                intent.putExtra("EX_VOI_AI", voi_ai);
                intent.putExtra("EX_TRANSACTION_ID", transaction_id);
                intent.putExtra("EX_ACCOUNT_ID", account_id);
                intent.putExtra("EX_TRANSACTION_TYPE", transaction_type);
                startActivityForResult(intent, 1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(XemVaSuaActivity.this, String.valueOf(resultCode), Toast.LENGTH_LONG).show();
        if (requestCode == 1) {
            if (resultCode == 1) {
                int flag = data.getIntExtra("FLAG", 404);
                if(flag == 1){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }
    void initIconList(){
        int iconList[] = {R.drawable.ct_khac, R.drawable.ct_giaoduc, R.drawable.ct_anuong, R.drawable.ct_hoadon, R.drawable.ic_dichuyen, R.drawable.ct_banbe, R.drawable.ct_giaitri, R.drawable.ct_dulich, R.drawable.ct_suckhoe, R.drawable.ct_quatang, R.drawable.ct_giadinh, R.drawable.ct_dautu, R.drawable.ic_teamwork, R.drawable.ct_kinhdoanh, R.drawable.ct_thuong, R.drawable.ct_tienlai, R.drawable.ct_luong, R.drawable.ct_quatang, R.drawable.ct_bando, R.drawable.ct_thunhapkhac, R.drawable.ct_khac, R.drawable.ct_khac, R.drawable.ct_khac,  R.drawable.ct_khac};
        this.iconList = iconList;
    }
    void initView(){
        //Get item view
        lb_so_tien = findViewById(R.id.lb_so_tien_xem);
        lb_danh_muc = findViewById(R.id.lb_danh_muc_xem);
        lb_ghi_chu = findViewById(R.id.lb_ghi_chu_xem);
        lb_thoi_gian = findViewById(R.id.lb_thoi_gian_xem);
        lb_vi = findViewById(R.id.lb_vi_xem);
        lb_dia_diem = findViewById(R.id.lb_dia_diem_xem);
        lb_voi_ai = findViewById(R.id.lb_voi_ai_xem);
        frameLayout = findViewById(R.id.frame_thu_chi);
        btn_back = findViewById(R.id.btn_back);
        btn_sua_giao_dich = findViewById(R.id.btn_hs_sua);
        icon = findViewById(R.id.img_icon);
    }


}