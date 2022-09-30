package com.my.sadebuser.act.network.request.booking;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BookingRequest {

    @FormUrlEncoded
    @POST("get_booking_users")
    Call<JsonElement> getBookingAppointment(
            @Field("user_id") String user_id);



    @GET("provider_accept_and_Cancel_request")
    Call<JsonElement> makestatus(@Query("status") String status,
                               @Query("request_id") String request,
                               @Query("provider_id") String provider_id
    );


    @GET("delete_booking_appointment")
    Call<JsonElement> delete_booking_appointment(@Query("request_id") String request_id);
}
