package com.my.sadebuser.act.network.time;

import com.my.sadebuser.act.model.booking.UsersDetails;
import com.my.sadebuser.act.model.timeavailibity.TimeAvailibityResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TimeAvailibity {

    @POST("time_slote")
    @FormUrlEncoded
    Call<TimeAvailibityResponse> getAllTimeSlot (@Field("service_id") String service_id,
                                                 @Field("date") String date,
                                                 @Field("provider_user_id") String provider_user_id);


    @POST("get_profile")
    @FormUrlEncoded
    Call<ResponseBody> get_profile (@Field("user_id") String user_id);


}
