package com.violet.smon.Data.ApiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.violet.smon.Data.Model.Account;
import com.violet.smon.Data.Model.Change_type;
import com.violet.smon.Data.Model.SimpleResponse;
import com.violet.smon.Data.Model.TransactionEachDayResponse;
import com.violet.smon.Data.Model.TransactionGetResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LaravelApi {
    final  String URL = "http://192.168.0.73:8000";

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    LaravelApi laravelApi = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LaravelApi.class);

    @GET("/api/change/0")
    Call<List<Change_type>> getChangeTypeChi();
    @GET("/api/change/1")
    Call<List<Change_type>> getChangeTypeThu();
    @GET("/api/{user_id}/wallet")
    Call<Account> getAllWallet(@Path("user_id") String user_id);
    @POST("/api/{user_id}/transaction/{transaction_type}")
    Call<SimpleResponse> storeTransaction(@Path("user_id") int user_id,
                                           @Path("transaction_type") int transaction_type,
                                           @Query("account_id") int account_id,
                                           @Query("type") int type,
                                           @Query("ammount") int ammount,
                                           @Query("content") String content,
                                           @Query("time") String time
    );
    @GET("/api/{user_id}/transaction/{account_id}/get")
    Call<List<TransactionGetResponse>> getAllTransaction(@Path("user_id") int user_id,
                                                          @Path("account_id") int account_id
    );
    @GET("/api/{user_id}/transaction/{account_id}/get_by_month/{month}")
    Call<List<TransactionEachDayResponse>> getTransactionByMonth(@Path("user_id") int user_id,
                                                                 @Path("account_id") int account_id,
                                                                 @Path("month") int month
    );
}
