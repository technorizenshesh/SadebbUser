package com.my.sadebuser.act.network.request.serviceprovider;

import com.google.gson.JsonElement;
import com.my.sadebuser.act.model.serviceprovider.ServiceProviderListResponse;
import com.my.sadebuser.model.SuccessResGetBanner;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceProvider {

    @GET("get_service_by_provider")
    Call<ServiceProviderListResponse> getServiceProviderList(@Query("user_id") String user_id,
                                                             @Query("lat") String lat,
                                                             @Query("lon") String lon);

    @GET("get_fav_provider")
    Call<ServiceProviderListResponse> getFavouriteProviderList(@Query("user_id") String user_id);


    @GET("get_banner")
    Call<SuccessResGetBanner> getBannerList();


    @GET("get_favorite_service")
    Call<ResponseBody> get_favorite_service(@Query("user_id") String user_id);




}
