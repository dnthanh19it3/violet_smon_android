package com.violet.smon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.violet.smon.Data.Adapter.TransactionEachDayAdapter;
import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.Account;
import com.violet.smon.Data.Model.TransactionEachDayResponse;
import com.violet.smon.Data.Model.TransactionGetResponse;
import com.violet.smon.Data.Model.User_details;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SoGiaoDichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SoGiaoDichFragment extends Fragment {
    //Init var
//    String user_id = "1";
    int account_id = 1;
    //Define View
    ImageButton buttonAddTrans;
    TextView text_so_du_vi, text_khoan_thu, text_khoan_chi, text_hieu_so;
    RecyclerView recy_each_days;
    public ArrayList<Integer> array_month_list = new ArrayList<Integer>();
    public List<TransactionGetResponse> transactionGetResponseList;
    //Define
    Intent themGiaDichIntent;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TabLayout tabLayout;
    String flag;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SoGiaoDichFragment() {
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SoGiaoDichFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SoGiaoDichFragment newInstance(String param1, String param2) {
        SoGiaoDichFragment fragment = new SoGiaoDichFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_so_giao_dich, container, false);
        // FindView
        buttonAddTrans = v.findViewById(R.id.btn_addtrans);
        tabLayout = v.findViewById(R.id.tab_thuchi);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        text_khoan_thu = v.findViewById(R.id.txt_khoang_thu);
//        text_khoan_chi = v.findViewById(R.id.txt_khoang_chi);
//        text_hieu_so = v.findViewById(R.id.txt_hieuso);
        recy_each_days = v.findViewById(R.id.recy_trans_each_day);

        themGiaDichIntent= new Intent(this.getContext(), ThemGiaoDich.class);
//        themGiaDichIntent= new Intent(this.getContext(), MainActivity2.class);
        LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
        renderTab(tabLayout);


        text_so_du_vi = v.findViewById(R.id.text_so_du_vi);
        loadWallet();


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {
//                text_khoan_thu.setText(transactionGetResponseList.get(tab.getPosition()).getFormattedIncom());
//                text_khoan_chi.setText(transactionGetResponseList.get(tab.getPosition()).getFormattedOutgo());
//                text_hieu_so.setText(transactionGetResponseList.get(tab.getPosition()).getFormattedDiffrence());
                renderEachDays(transactionGetResponseList.get(tab.getPosition()).getMonth());


            }
            public void onTabUnselected(TabLayout.Tab tab) {}
            public void onTabReselected(TabLayout.Tab tab) {}
        });
        buttonAddTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(themGiaDichIntent);
            }
        });





        return v;
    }


    public void renderEachDays(int month){
        Log.e("Debug giao dich", String.valueOf(MainActivity.account_id));
        LaravelApi.laravelApi.getTransactionByMonth(MainActivity.user_id, MainActivity.account_id, month).enqueue(new Callback<List<TransactionEachDayResponse>>() {
            @Override
            public void onResponse(Call<List<TransactionEachDayResponse>> call, Response<List<TransactionEachDayResponse>> response) {
                Log.e("Debug giao dich", response.toString());
                List<TransactionEachDayResponse> transactionEachDayResponses = response.body();
                TransactionEachDayAdapter transactionEachDayAdapter = new TransactionEachDayAdapter(transactionEachDayResponses, SoGiaoDichFragment.this.getContext());
                LinearLayoutManager linearLayout = new LinearLayoutManager(SoGiaoDichFragment.this.getContext());

                recy_each_days.setAdapter(transactionEachDayAdapter);
                recy_each_days.setLayoutManager(linearLayout);
            }

            @Override
            public void onFailure(Call<List<TransactionEachDayResponse>> call, Throwable t) {
                Log.e("Render Each day", t.toString());
            }
        });


    }

    public void renderTab(TabLayout tabLayout) {
        LaravelApi.laravelApi.getAllTransaction(MainActivity.user_id, MainActivity.account_id).enqueue(new Callback<List<TransactionGetResponse>>() {
            @Override
            public void onResponse(Call<List<TransactionGetResponse>> call, Response<List<TransactionGetResponse>> response) {
                List<TransactionGetResponse> transactionGetResponses = response.body();
                transactionGetResponseList = transactionGetResponses;
                DateFormat dateFormat = new SimpleDateFormat("MM");
                Date date = new Date();
                int thismonth = Integer.parseInt(dateFormat.format(date));

                for (int i = 0; i < transactionGetResponses.size(); i++) {
                    int monthnum = transactionGetResponses.get(i).getMonth();

                    if(monthnum == thismonth){
                        tabLayout.addTab(tabLayout.newTab().setText("Tháng này"));
                    } else if (monthnum == thismonth + 1){
                        tabLayout.addTab(tabLayout.newTab().setText("Tháng sau"));
                    } else if (monthnum == thismonth - 1){
                        tabLayout.addTab(tabLayout.newTab().setText("Tháng trước"));
                    } else {
                        tabLayout.addTab(tabLayout.newTab().setText("Tháng "+monthnum));
                    }
                    array_month_list.add(transactionGetResponses.get(i).getMonth());
                }

                tabLayout.getTabAt(array_month_list.indexOf(thismonth)).select();
            }

            @Override
            public void onFailure(Call<List<TransactionGetResponse>> call, Throwable t) {

            }
        });


    }

    public void loadWallet() {
        LaravelApi.laravelApi.getAllWallet(MainActivity.user_id).enqueue(new Callback<Account>() {
            @Override
            public void onResponse(Call<Account> call, Response<Account> response) {
                Account account = response.body();
                text_so_du_vi.setText(account.getFormattedBalance());
            }

            @Override
            public void onFailure(Call<Account> call, Throwable t) {
                Log.e("Lỗi:", "Load vi loi");
            }
        });
    }
    public void loadListThuChi(){

    }
}