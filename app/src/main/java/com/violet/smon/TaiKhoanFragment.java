package com.violet.smon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.violet.smon.Data.ApiService.LaravelApi;
import com.violet.smon.Data.DAO.AuthDatabase;
import com.violet.smon.Data.Model.Profile;
import com.violet.smon.Data.Model.SimpleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaiKhoanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaiKhoanFragment extends Fragment {
    public Context context;
    TextView name, dob, gender, hello;
    ImageView btn_sua;

    LinearLayout logout, about;

    public  TaiKhoanFragment(Context context){
        this.context = context;
    }
    public TaiKhoanFragment() {
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaiKhoanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaiKhoanFragment newInstance(String param1, String param2) {
        TaiKhoanFragment fragment = new TaiKhoanFragment();
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
        View v = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        initView(v);
        loadData(MainActivity.user_id);
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSua = new Intent(context, SuaHoSo.class);
                startActivityForResult(intentSua, 1);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(MainActivity.user_id);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, About.class);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadData(MainActivity.user_id);
    }

    public void loadData(int user_id){
        LaravelApi.laravelApi.getProfile(user_id).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.body() != null){
                    Profile profile = response.body();
                   try {
                       name.setText(profile.getDisplay_name());
                       dob.setText(profile.getDob().toString());
                       hello.setText(profile.getHello());
                       gender.setText(profile.getGender());
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
    void logout(int user_id){
        Log.e("Debug logout", "Đã click");
        LaravelApi.laravelApi.logout(user_id).enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                Log.e("Debug logout", response.toString());
                if(response.body() != null){
                    SimpleResponse simpleResponse = response.body();
                    if(simpleResponse.getCode() == 1){
                        Toast.makeText(context, simpleResponse.getMsg(), Toast.LENGTH_LONG).show();
                        AuthDatabase.getInstance(context).authDAO().resetTable();
                        MainActivity.account_id = 0;
                        MainActivity.user_id = 0;
                        Intent intent = new Intent(context, LoginActivity.class);
                        getActivity().finishAffinity();
                    }
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                Log.e("Loi dang xuat", t.toString());
            }
        });
    }



    public void initView(View v){
        name = v.findViewById(R.id.lb_tk_hoten);
        dob = v.findViewById(R.id.lb_tk_dob);
        gender = v.findViewById(R.id.lb_tk_gender);
        hello = v.findViewById(R.id.lb_tk_chao);
        btn_sua = v.findViewById(R.id.btn_hs_sua);
        about = v.findViewById(R.id.about);
        logout = v.findViewById(R.id.logout);
    }
}