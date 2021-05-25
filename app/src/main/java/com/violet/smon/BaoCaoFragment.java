package com.violet.smon;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;
import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.Model.AuthResponse;
import com.violet.smon.Data.Model.ReportResponse;
import com.violet.smon.Data.Model.TransactionGetResponse;
import com.violet.smon.Data.Model.ValuePair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaoCaoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaoCaoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public List<TransactionGetResponse> transactionGetResponseList;
    public ArrayList<Integer> array_month_list = new ArrayList<Integer>();
    TabLayout tabLayout;
    AnyChartView anyChartView;

    //View define
    TextView txt_khoanthu, txt_khoanchi, txt_candoi;




    public BaoCaoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BaoCaoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BaoCaoFragment newInstance(String param1, String param2) {
        BaoCaoFragment fragment = new BaoCaoFragment();
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
        View v = inflater.inflate(R.layout.fragment_bao_cao, container, false);
        anyChartView= v.findViewById(R.id.any_chart_view);
        tabLayout = v.findViewById(R.id.tab_thuchi);
        txt_khoanthu  = v.findViewById(R.id.txt_bc_khoangthu);
        txt_khoanchi  = v.findViewById(R.id.txt_bc_khoangchi);
        txt_candoi  = v.findViewById(R.id.txt_bc_candoi);


        renderTab(tabLayout);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("Debu", String.valueOf(tab.getPosition()));
                if(array_month_list.size() >= 1){
                    Log.e("Debu", String.valueOf(array_month_list.get(tab.getPosition())));
                    renderReport(v, array_month_list.get(tab.getPosition()));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return v;
    }

    private void initView(View v) {
//        tabLayout = v.findViewById(R.id.tab_thuchi);
//        txt_khoanthu  = v.findViewById(R.id.txt_bc_khoangthu);
//        txt_khoanchi  = v.findViewById(R.id.txt_bc_khoangchi);
//        txt_candoi  = v.findViewById(R.id.txt_bc_candoi);
    }

    void renderReport(View v, int month){
        LaravelApi.laravelApi.getReport(MainActivity.user_id, MainActivity.account_id, month).enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                Log.e("Debug bao cao", response.toString());
                ReportResponse reportResponse = response.body();
                if(reportResponse != null){
                    txt_khoanchi.setText(String.valueOf(reportResponse.getOutgo()));
                    txt_khoanthu.setText(String.valueOf(reportResponse.getIncom()));
                    txt_candoi.setText(String.valueOf(reportResponse.getDiffence()));
                    renderChart(reportResponse.getOutgo_data(), v);
                }
            }

            @Override
            public void onFailure(Call<ReportResponse> call, Throwable t) {

            }
        });
    }

    void renderChart(ArrayList<ValuePair> data, View v){
        Log.e("Chart", "Render bảng mới");


        anyChartView.animate();
        Pie pie = AnyChart.pie();
        List<DataEntry> data1 = new ArrayList<>();
        if(data.size() > 0){
            for (int i = 0; i < data.size(); i++){
                data1.add(new ValueDataEntry(data.get(i).getX(), data.get(i).getValue()));
                Log.e("Chart item value", String.valueOf(data.get(i).getValue()));
            }
        } else {
            data1.add(new ValueDataEntry("Không có khoản chi", 0));
        }
        pie.data(data1);
        anyChartView.setChart(pie);
    }
    public void renderTab(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
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
                Log.e("Err BaoCao Fr", t.toString());
            }
        });


    }

}