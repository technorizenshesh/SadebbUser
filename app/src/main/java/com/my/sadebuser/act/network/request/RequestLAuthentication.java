package com.my.sadebuser.act.network.request;

import com.google.gson.JsonElement;
import com.my.sadebuser.act.model.ChangePasswordResponse;
import com.my.sadebuser.act.model.ResponseAuthentication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestLAuthentication {

    @POST("login")
    @FormUrlEncoded
    Call<JsonElement> getlogIn(@Field("email") String email,
                               @Field("password") String password,
                               @Field("type") String type,
                               @Field("register_id") String register_id
    );

    @FormUrlEncoded
    @POST("user_signup")
    Call<JsonElement> getSignUp(@Field("user_name") String user_name,
                                @Field("surname") String surname,
                                @Field("password") String password,
                                @Field("email") String email,
                                @Field("type") String type,
                                @Field("mobile") String mobile,
                                @Field("description") String description,
                                @Field("register_id") String register_id,
                                @Field("business_name") String businessName,
                                @Field("business_address") String businessAddress,
                                @Field("business_cell_phone") String businessCell,
                                @Field("business_landline") String businessLandline,
                                @Field("offer_home_delivery") String OfferHomeDelivery,
                                @Field("b_lat") String lat,
                                @Field("b_lon") String lon

    );


    @POST("forgot_password")
    @FormUrlEncoded
    Call<JsonElement> getForgetPass(@Field("email") String email);

    @POST("get_profile")
    @FormUrlEncoded
    Call<ResponseAuthentication> get_profile(@Field("user_id") String email);

    @FormUrlEncoded
    @POST("update_profile")
    Call<ResponseAuthentication> getProfileUpdate(@Field("user_name") String user_name,
                                                  @Field("email") String email,
                                                  @Field("mobile") String mobile,
                                                  @Field("user_id") String id,
                                                  @Field("gender") String gender

    );

    @FormUrlEncoded
    @POST("change_password")
    Call<JsonElement> changePassword(@Field("user_id") String id,
                                                @Field("old_password") String old_pass,
                                                @Field("new_password") String new_password
    );

}
