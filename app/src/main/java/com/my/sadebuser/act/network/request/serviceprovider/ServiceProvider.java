package com.my.sadebuser.act.network.request.serviceprovider;

import com.my.sadebuser.act.model.serviceprovider.ServiceProviderListResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceProvider {
    @GET("get_service_by_provider")
    Call<ServiceProviderListResponse> getServiceProviderList();


    @GET("get_favorite_service")
    Call<ResponseBody> get_favorite_service(@Query("user_id") String user_id);

}
