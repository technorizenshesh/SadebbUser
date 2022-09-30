package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.servicedetail.ServiceDetailResponse;
import com.my.sadebuser.act.model.servicedetail.ServiceUserItem;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.bookingdetail.BookingRequest;
import com.my.sadebuser.adapter.ProviderAllUserAdapter;
import com.my.sadebuser.databinding.ActivityProviderAllUserBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProviderAllUserActivity extends AppCompatActivity {
    private ActivityProviderAllUserBinding binding;
    private ProviderAllUserAdapter adapter;
    private List<ServiceUserItem> list =new ArrayList<>();
    private com.my.sadebuser.act.model.servicelist.ResultItem item;
    private String s="";
    private String providerUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityProviderAllUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init(){
        item = new Gson().fromJson(getIntent().getStringExtra("item"), com.my.sadebuser.act.model.servicelist.ResultItem.class);
        getAllProviderUser();
        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
        binding.rvAllUsers.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ProviderAllUserAdapter(list, new ProviderAllUserAdapter.SelectedItem() {
            @Override
            public void selectedItem(int position) {
                s="Aamir";
                providerUserId=list.get(position).getId();
                Log.i("dsfsfsfds", "selectedItem: "+providerUserId);
            }
        });
        binding.rvAllUsers.setAdapter(adapter);
        binding.tvNext.setOnClickListener(v -> {
            Log.i("fsfsffsfs", "init: "+item.toString());
            String provider_Name = getIntent().getStringExtra("provider_Name");
            String provider_img = getIntent().getStringExtra("provider_img");
            String service_id = getIntent().getStringExtra("service_id");
         if (!s.equals("")){
             Intent intent = new Intent(ProviderAllUserActivity.this, ShopTimeAvailavility.class);
             intent.putExtra("service_id", service_id);
              intent.putExtra("provider_Name", provider_Name);
             intent.putExtra("provider_img", provider_img);
             intent.putExtra("providerUserId", providerUserId);
             intent.putExtra("item", new Gson().toJson(item));
             startActivity(intent);
         }else {
             Snackbar.make(findViewById(android.R.id.content),
                     getString(R.string.please_select_a_provider),
                     Snackbar.LENGTH_SHORT).show();
         }
        });
    }
    private void getAllProviderUser() {
         binding.loaderLayout.loader.setVisibility(View.VISIBLE);
         if (item!=null){
             RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                     .create(BookingRequest.class)
                     .getmyServicesdetail(item.getId())
                     .enqueue(new Callback<JsonElement>() {
                         @Override
                         public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                             binding.loaderLayout.loader.setVisibility(View.GONE);
                             if (response != null) {
                                 if (response.isSuccessful()) {
                                     JsonObject object = response.body().getAsJsonObject();
                                     int status = object.get("status").getAsInt();
                                     if (status == 1) {
                                         ServiceDetailResponse authentication = new Gson().fromJson(object, ServiceDetailResponse.class);
                                         list.clear();
                                         list.addAll(authentication.getResult().getServiceUser());
                                         adapter.notifyDataSetChanged();
                                         for (int i = 0; i < list.size(); i++) {
                                             if (!authentication.getResult().getServiceUser().get(i).getBookingStatus().equals("Free")){
                                                 list.remove(i);
                                                 adapter.notifyDataSetChanged();
                                                 break;
                                             }
                                         }
                                         if (list.size()>0){
                                             binding.tvNoUserFound.setVisibility(View.GONE);
                                         }
                                     } else {
                                         if (list.size()==0){
                                             binding.tvNoUserFound.setVisibility(View.VISIBLE);
                                             binding.tvNext.setAlpha(0.5f);
                                             binding.tvNext.setEnabled(false);
                                         }
                                         ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                         Log.i("dscbhs", "onResponse: " + authentication);
                                         Snackbar.make(findViewById(android.R.id.content),
                                                 authentication.getResult(),
                                                 Snackbar.LENGTH_SHORT).show();
                                     }
                                 }
                             }
                         }
                         @Override
                         public void onFailure(Call<JsonElement> call, Throwable t) {
                             binding.loaderLayout.loader.setVisibility(View.GONE);
                             Log.i("sfssfs", "onResponse: " + t.getMessage());
                         }
                     });
         }
    }
}