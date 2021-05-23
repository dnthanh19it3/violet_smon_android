package com.violet.smon.Data.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.violet.smon.Data.Model.Change;
import com.violet.smon.R;
import com.violet.smon.XemVaSuaActivity;

import java.text.ParseException;
import java.util.List;

public class RecyTransItem extends RecyclerView.Adapter<RecyTransItem.ViewHolder> {
    public List<Change> data;
    View view;
    Context context;

    public RecyTransItem(List<Change> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lb_so_tien.setText(data.get(position).getFormattedAmmount());
        holder.lb_danh_muc.setText(data.get(position).getName());
        holder.lb_ghi_chu.setText(data.get(position).getContent());
        holder.frame_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, XemVaSuaActivity.class);
                //Send to new intent
                intent.putExtra("EX_SO_TIEN", data.get(position).getFormattedAmmount());
                intent.putExtra("EX_SO_TIEN_RAW", data.get(position).getAmmount());
                intent.putExtra("EX_DANH_MUC", data.get(position).getName());
                intent.putExtra("EX_TRANSACTION_TYPE", data.get(position).getMethod());
                intent.putExtra("EX_GHI_CHU", data.get(position).getContent());

                intent.putExtra("EX_TRANSACTION_ID", data.get(position).getChange_id());
                intent.putExtra("EX_ACCOUNT_ID", data.get(position).getAccount_id());

                try {
                    intent.putExtra("EX_THOI_GIAN", data.get(position).getFullTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                intent.putExtra("EX_THOI_GIAN_RAW", data.get(position).getTime());
                intent.putExtra("EX_VI", "nope");
                intent.putExtra("EX_DIA_DIEM", data.get(position).getLocation());
                intent.putExtra("EX_VOI_AI", data.get(position).getWith());
                intent.putExtra("EX_TYPE", data.get(position).getMethod());
                intent.putExtra("EX_DANH_MUC_RAW", data.get(position).getType());


//                lb_so_tien = getIntent().getStringExtra("EX_SO_TIEN");
//                lb_danh_muc = getIntent().getStringExtra("EX_DANH_MUC");
//                lb_ghi_chu = getIntent().getStringExtra("EX_GHI_CHU");
//                lb_thoi_gian = getIntent().getStringExtra("EX_THOI_GIAN");
//                lb_vi  = getIntent().getStringExtra("EX_vi");
//                lb_dia_diem = getIntent().getStringExtra("EX_DIA_DIEN");
//                lb_voi_ai = getIntent().getStringExtra("EX_VOI_AI");


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView activity_icon;
        TextView lb_danh_muc, lb_ghi_chu, lb_so_tien;
        FrameLayout frame_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lb_danh_muc = itemView.findViewById(R.id.lb_vi_xem);
            lb_ghi_chu = itemView.findViewById(R.id.lb_ghi_chu);
            lb_so_tien = itemView.findViewById(R.id.lb_so_tien);
            frame_item = itemView.findViewById(R.id.frame_item);
        }
    }
}
