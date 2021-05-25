package com.violet.smon.Data.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.violet.smon.Data.Model.Change_type;
import com.violet.smon.R;

import java.util.List;

public class ChonLoaiGiaoDichAdapter extends  RecyclerView.Adapter<ChonLoaiGiaoDichAdapter.ViewHolder>{
    public List<Change_type> changeTypeThu;
    Activity activity;
    Context context;
    int iconList[];


    public ChonLoaiGiaoDichAdapter(List<Change_type> changeTypeThu, Activity activity){
        this.changeTypeThu = changeTypeThu;
        this.activity = activity;
    }

    @NonNull
    public ChonLoaiGiaoDichAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_category_item, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(changeTypeThu.get(position).getName());
        holder.imageView.setImageDrawable(activity.getDrawable(iconList[changeTypeThu.get(position).getId()]));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("LOAI_GIAO_DICH_TEXT", changeTypeThu.get(position).getName());
                returnIntent.putExtra("LOAI_GIAO_DICH_KEY", changeTypeThu.get(position).getId());
                returnIntent.putExtra("TRANSACTION_TYPE", changeTypeThu.get(position).getMethod());
                activity.setResult(1, returnIntent);
                activity.finish();
            }
        });
    }

    public int getItemCount() {
        if (changeTypeThu != null){
            return changeTypeThu.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.category_image);
            textView = itemView.findViewById(R.id.category_text);
            linearLayout = itemView.findViewById(R.id.recyle_category_item);
            initIconList();
        }
    }
    void initIconList(){
        int iconList[] = {R.drawable.ct_khac, R.drawable.ct_giaoduc, R.drawable.ct_anuong, R.drawable.ct_hoadon, R.drawable.ic_dichuyen, R.drawable.ct_banbe, R.drawable.ct_giaitri, R.drawable.ct_dulich, R.drawable.ct_suckhoe, R.drawable.ct_quatang, R.drawable.ct_giadinh, R.drawable.ct_dautu, R.drawable.ic_teamwork, R.drawable.ct_kinhdoanh, R.drawable.ct_thuong, R.drawable.ct_tienlai, R.drawable.ct_luong, R.drawable.ct_quatang, R.drawable.ct_bando, R.drawable.ct_thunhapkhac, R.drawable.ct_khac, R.drawable.ct_khac, R.drawable.ct_khac,  R.drawable.ct_khac};
        this.iconList = iconList;
    }
}
