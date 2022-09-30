package com.my.sadebuser.act.network.request.reviews;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AddReviewReq {
    @GET("add_review")
    Call<JsonElement> addReviews(@Query("provider_id") String provider_id,
                                    @Query("user_id") String user_id,
                                    @Query("comment") String comment,
                                    @Query("rating") String rating
                                     );
}
