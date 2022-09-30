package com.my.sadebuser.act.network.request.serviceprovider;

import com.my.sadebuser.act.model.nearbymepro.NearbymeProviderResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetNearByMeProvider {
    @GET("get_provider_list_nearbuy")
    Call<NearbymeProviderResponse> getproviderlist();
}
