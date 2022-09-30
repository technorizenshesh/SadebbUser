package com.my.sadebuser.act.network.request.reviews;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetAllReviews {
    @GET("get_review")
    Call<JsonElement> getAllreviews(@Query("provider_id") String provider_id);
}
