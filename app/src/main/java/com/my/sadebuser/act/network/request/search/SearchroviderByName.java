package com.my.sadebuser.act.network.request.search;

import com.google.gson.JsonElement;
import com.my.sadebuser.act.model.search.SearchProviderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchroviderByName {

    @GET("provider_search")
    Call<JsonElement> getproviderbyname(@Query("provider_name") String provider_name);
}
