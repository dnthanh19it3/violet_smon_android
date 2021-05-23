package com.violet.smon.Data.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.violet.smon.Data.Model.TransactionEachDayResponse;
import com.violet.smon.R;

import java.text.ParseException;
import java.util.List;

public class TransactionEachDayAdapter extends RecyclerView.Adapter<TransactionEachDayAdapter.ViewHolder> {
    //Define
    List<TransactionEachDayResponse> transEachDays;
    Context context;



    public TransactionEachDayAdapter(List<TransactionEachDayResponse> transEacDays, Context context) {
        this.transEachDays = transEacDays;
        this.context = context;
    }


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_each_day, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.lb_thu.setText(transEachDays.get(position).getDowFormatted());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.lb_ngay.setText(transEachDays.get(position).getDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            holder.lb_thang_nam.setText(transEachDays.get(position).getFullMothFormatted() + " " + transEachDays.get(position).getYear());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.lb_so_tien.setText(transEachDays.get(position).getFormattedDiffrence());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        RecyTransItem recyTransItem = new RecyTransItem(transEachDays.get(position).getData(), context);
        holder.recyclerView.setAdapter(recyTransItem);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public int getItemCount() {
        return transEachDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lb_thu, lb_ngay, lb_thang_nam, lb_so_tien;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lb_thu = itemView.findViewById(R.id.lb_thu);
            lb_ngay = itemView.findViewById(R.id.lb_ngay);
            lb_thang_nam = itemView.findViewById(R.id.lb_thang_ngay);
            lb_so_tien = itemView.findViewById(R.id.lb_so_tien);
            recyclerView = itemView.findViewById(R.id.recy_trans_item);

        }
    }
}
