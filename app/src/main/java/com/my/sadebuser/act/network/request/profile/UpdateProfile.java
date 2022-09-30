package com.my.sadebuser.act.network.request.profile;

import com.google.gson.JsonElement;
import com.my.sadebuser.act.model.ResponseAuthentication;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UpdateProfile {



    @Multipart
    @POST("update_profile")
    Call<JsonElement> getEditUpdate(@Part MultipartBody.Part user_name,
                                    @Part MultipartBody.Part email,
                                    @Part MultipartBody.Part mobile,
                                    @Part MultipartBody.Part user_id,
                                    @Part MultipartBody.Part gender,
                                    @Part MultipartBody.Part image);


}
