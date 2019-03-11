package com.example.zaki.delivreport.Rest;

import com.example.zaki.delivreport.Model.DefoodResponse;
import com.example.zaki.delivreport.Model.DerideResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("defood/{from}/{to}")
    Call<DefoodResponse> getDataDefood(@Path("from") String from, @Path("to") String to);

    @GET("deride/{from}/{to}")
    Call<DerideResponse> getDataDeride(@Path("from") String from, @Path("to") String to);
}
