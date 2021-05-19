package com.violet.smon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChonLoaiGiaoDich#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChonLoaiGiaoDich extends Fragment {

    RecyclerView recyclerViewThu;
    RecyclerView recyclerViewChi;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChonLoaiGiaoDich() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChonLoaiGiaoDich.
     */
    // TODO: Rename and change types and number of parameters
    public static ChonLoaiGiaoDich newInstance(String param1, String param2) {
        ChonLoaiGiaoDich fragment = new ChonLoaiGiaoDich();
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
        View v = inflater.inflate(R.layout.fragment_chon_loai_giao_dich, container, false);
//        renderThu(v);
        return v;
    }
//    public void renderThu(View v){
//        LaravelApi.laravelApi.getChangeTypeThu().enqueue(new Callback<List<Change_type>>() {
//            public void onResponse(Call<List<Change_type>> call, Response<List<Change_type>> response) {
//                List<Change_type> change_types = response.body();
//                Log.e("Check Respond", String.valueOf(response.body()));
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext());
//                Log.e("CheckPoint", "Da di qua 1");
//                ChonLoaiGiaoDichAdapter adapterLoaiGiaoDich = new ChonLoaiGiaoDichAdapter(change_types);
//                recyclerViewThu = v.findViewById(R.id.recycle_view_thu);
//                Log.e("CheckPoint", "Da di qua 2");
//                recyclerViewThu.setAdapter(adapterLoaiGiaoDich);
//                Log.e("CheckPoint", "Da di qua 3");
//                recyclerViewThu.setLayoutManager(linearLayoutManager);
//            }
//            @Override
//            public void onFailure(Call<List<Change_type>> call, Throwable t) {
//                Log.e("Loi", t.toString());
//            }
//        });
//    }
    public void renderChi(){

    }

}