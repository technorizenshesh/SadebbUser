package com.my.sadebuser.act.network.request.bookingdetail;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BookingRequest {

    @POST("booking_appointment")
    @FormUrlEncoded
    Call<JsonElement> booking(@Field("user_id") String user_id,
                              @Field("service_id") String service_id,
                              @Field("date") String date,
                              @Field("start_time") String start_time,
                              @Field("end_time") String end_time,
                              @Field("email") String email,
                              @Field("mobile") String mobile,
                              @Field("lat") String lat,
                              @Field("lon") String longitude,
                              @Field("provider_user_id") String employee_id,
                              @Field("lang") String lang

    );

    @POST("get_users")
    @FormUrlEncoded
    Call<JsonElement> getAllUsers(@Field("user_id") String user_id);

    @POST("get_service_user")
    @FormUrlEncoded
    Call<JsonElement> getmyServicesdetail(@Field("service_id") String service_id);

}
