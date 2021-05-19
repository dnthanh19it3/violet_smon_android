package com.violet.smon.Data.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.violet.smon.Data.Model.Change;
import com.violet.smon.R;

import java.util.List;
import java.util.zip.Inflater;

public class RecyTransItem extends RecyclerView.Adapter<RecyTransItem.ViewHolder> {
    public List<Change> data;

    public RecyTransItem(List<Change> data) {
        this.data = data;
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView activity_icon;
        TextView lb_danh_muc, lb_ghi_chu, lb_so_tien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lb_danh_muc = itemView.findViewById(R.id.lb_danh_muc);
            lb_ghi_chu = itemView.findViewById(R.id.lb_ghi_chu);
            lb_so_tien = itemView.findViewById(R.id.lb_so_tien);
        }
    }
}
