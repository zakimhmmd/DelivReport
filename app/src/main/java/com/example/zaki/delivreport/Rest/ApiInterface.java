package com.example.zaki.delivreport.Rest;

import com.example.zaki.delivreport.Model.Dashboard.DashResponse;
import com.example.zaki.delivreport.Model.Decar.DecarResponse;
import com.example.zaki.delivreport.Model.Deexpress.DeexpressResponse;
import com.example.zaki.delivreport.Model.Defood.DefoodResponse;
import com.example.zaki.delivreport.Model.Defood.DetailResponse;
import com.example.zaki.delivreport.Model.Depayment.DepayResponse;
import com.example.zaki.delivreport.Model.Deride.DerideResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("defood/{from}/{to}")
    Call<DefoodResponse> getDataDefood(@Path("from") String from, @Path("to") String to);

    @GET("deride/{from}/{to}")
    Call<DerideResponse> getDataDeride(@Path("from") String from, @Path("to") String to);

    @GET("decar/{from}/{to}")
    Call<DecarResponse> getDataDecar(@Path("from") String from, @Path("to") String to);

    @GET("deexpress/{from}/{to}")
    Call<DeexpressResponse> getDataDeexpress(@Path("from") String from, @Path("to") String to);

    @GET("depayment/{from}/{to}")
    Call<DepayResponse> getDataDepay(@Path("from") String from, @Path("to") String to);

    @GET("{from}")
    Call<DashResponse> getDashboardData(@Path("from") String from);

    @GET("defood/{id}")
    Call<DetailResponse> getDetailDefood(@Path("id") String id);

    @GET("deride/{id}")
    Call<com.example.zaki.delivreport.Model.Deride.DetailResponse> getDetailDeride(@Path("id") String id);

    @GET("deexpress/{id}")
    Call<com.example.zaki.delivreport.Model.Deexpress.DetailResponse> getDetailDeexpress(@Path("id") String id);
}
