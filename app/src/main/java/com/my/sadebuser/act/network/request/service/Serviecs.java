package com.my.sadebuser.act.network.request.service;

import com.google.gson.JsonElement;
import com.my.sadebuser.act.model.servicelist.AllServiceResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Serviecs {

    @GET("get_service")
    Call<JsonElement> getServices(@Query("user_id") String user_id);

    @POST("get_service_bycategory")
    @FormUrlEncoded
    Call<JsonElement> get_service_bycategory(@Field("user_id") String user_id,
                                             @Field("category_id") String category_id);

    @GET("like_unlike_service")
    Call<JsonElement> like_unlike_service(@Query("user_id") String user_id,
                                          @Query("service_id") String service_id);

    @GET("add_fav_provider")
    Call<JsonElement> like_unlike_provider(@Query("user_id") String user_id,
                                          @Query("provider_id") String service_id);


}
