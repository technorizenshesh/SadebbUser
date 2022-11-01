package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.servicedetail.ServiceDetailResponse;
import com.my.sadebuser.act.model.servicedetail.ServiceUserItem;
import com.my.sadebuser.act.model.sevicelistbycat.ResultItem;
import com.my.sadebuser.act.model.sevicelistbycat.ServiceListByCatResponse;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.bookingdetail.BookingRequest;
import com.my.sadebuser.act.network.request.service.Serviecs;
import com.my.sadebuser.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.sadebuser.adapter.ServiceListByCatAdapter;
import com.my.sadebuser.databinding.ActivityCategorywiseAllServiceListBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategorywiseAllServiceListActivity extends AppCompatActivity {

    private ActivityCategorywiseAllServiceListBinding binding;
    private ServiceListByCatAdapter mAdapter;
    private List<ResultItem> serviceproviderlist = new ArrayList<>();
    private String Provider_Name,Provider_id,provider_img;
    private boolean amir=true;
    List<ServiceUserItem> list =new ArrayList<>();
    private ResponseAuthentication model;
    String  category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCategorywiseAllServiceListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.title.setText(getIntent().getStringExtra("category_name"));
        model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        init();
    }

    private void init(){
       category_id=getIntent().getStringExtra("category_id");
        getServiecs(category_id);

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

        binding.rvShop.setLayoutManager(new LinearLayoutManager(this));

    }

    private void getServiecs(String category_id) {
        Log.i("scss", "category_id: "+category_id);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(Serviecs.class)
                .get_service_bycategory(model.getResult().getId(),category_id)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        Log.i("scss", "onResponse: "+response.body());
                        if (response!=null){
                            if (response.isSuccessful()){
                                JsonObject object=response.body().getAsJsonObject();
                                int status = object.get("status").getAsInt();
                                if (status==1){
                                    serviceproviderlist = new ArrayList<>();
                                    ServiceListByCatResponse listResponse=new Gson().fromJson(object,ServiceListByCatResponse.class);
                                    serviceproviderlist.addAll(listResponse.getResult());

                                    Log.i("scss", "onResponse: "+serviceproviderlist.toString());
                                    mAdapter = new ServiceListByCatAdapter(  serviceproviderlist,  new HomeSaloonRecyclerViewAdapter.ItemPos() {
                                        @Override
                                        public void selectedpos(int pos) {
                                            getAllProviderUser(pos);
//                                            if (list.size()==0){
//                                                Toast.makeText(CategorywiseAllServiceListActivity.this, getString(R.string.there_is_no_provider), Toast.LENGTH_SHORT).show();
//                                            }else {
//                                                Log.i("scss", "selectedpos: "+Provider_Name);
//                                                Log.i("scss", "selectedpos: "+Provider_id);
//                                                Log.i("scss", "selectedpos: "+provider_img);
//                                                 Intent intent = new Intent(CategorywiseAllServiceListActivity.this, ProviderAllUserActivity.class);
//                                                intent.putExtra("service_id", serviceproviderlist.get(pos).getId());
//                                                 intent.putExtra("provider_img", provider_img);
//                                                intent.putExtra("item",new Gson().toJson(serviceproviderlist.get(pos)));
//                                                startActivity(intent);
//                                            }

                                        }

                                        @Override
                                        public void addFavourite(int pos) {

                                        }
                                    });
                                    mAdapter.setFavoriteClick(resultItem -> {
                                        favouriteServicelist(resultItem.getId());
                                    });
                                    binding.rvShop.setAdapter(mAdapter);

                                    mAdapter.notifyDataSetChanged();




                                }else {
                                    if (list.size()==0){
                                        binding.tvNoProductFound.setVisibility(View.VISIBLE);
                                     }
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                    }
                });
    }

    private void getAllProviderUser(int pos) {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        if (serviceproviderlist!=null){
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(BookingRequest.class)
                    .getmyServicesdetail(serviceproviderlist.get(pos).getId())
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
                                        list.addAll(authentication.getResult().getServiceUser());

                                        Log.i("ccscsc", "onResponse: "+list.size());
                                        if (list.size()==0){
                                            Toast.makeText(CategorywiseAllServiceListActivity.this, getString(R.string.there_is_no_provider), Toast.LENGTH_SHORT).show();
                                        }else {
                                            Log.i("scss", "selectedpos: "+Provider_Name);
                                            Log.i("scss", "selectedpos: "+Provider_id);
                                            Log.i("scss", "selectedpos: "+provider_img);
//                                            Intent intent = new Intent(CategorywiseAllServiceListActivity.this, ProviderAllUserActivity.class);
//                                            intent.putExtra("service_id", serviceproviderlist.get(pos).getId());
//                                            intent.putExtra("provider_img", provider_img);
//                                            intent.putExtra("provider_Name", Provider_Name);
//                                            intent.putExtra("item",new Gson().toJson(serviceproviderlist.get(pos)));
//                                            startActivity(intent);
                                            Intent intent = new Intent(CategorywiseAllServiceListActivity.this, ShopTimeAvailavility.class);
                                            intent.putExtra("service_id", serviceproviderlist.get(pos).getId());
                                            intent.putExtra("provider_Name", serviceproviderlist.get(pos).getBusiness_name());//Provider_name());//Provider_Name);
                                            intent.putExtra("provider_img", serviceproviderlist.get(pos).getProvider_image());//provider_img);
//                                            intent.putExtra("providerUserId", providerUserId);
                                            intent.putExtra("item", new Gson().toJson(serviceproviderlist.get(pos)));//item));
                                            startActivity(intent);
                                        }
                                    }
//                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
//                                        Log.i("dscbhs", "onResponse: " + authentication);
//                                        Snackbar.make(findViewById(android.R.id.content),
//                                                authentication.getResult(),
//                                                Snackbar.LENGTH_SHORT).show();
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



    private void favouriteServicelist(String id) {
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(Serviecs.class)
                .like_unlike_service(model.getResult().getId(),id)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {


                        if (response.isSuccessful()) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                            getServiecs(category_id);

//                            JsonObject jsonObject = response.body().getAsJsonObject();
//                            int status = jsonObject.get("status").getAsInt();
//                            if (status == 1) {
//                                Intent intent = new Intent(getContext(), ShopDetailsActivity.class);
//                                intent.putExtra("response", new Gson().toJson(response.body()));
//                                intent.putExtra("Provider_Name", Provider_Name);
//                                intent.putExtra("Provider_id", Provider_id);
//                                intent.putExtra("provider_img", provider_img);
//                                getContext().startActivity(intent);
//                            } else {
//                                binding.loaderLayout.loader.setVisibility(View.GONE);
//                                Toast.makeText(getContext(), getString(R.string.there_is_no_service), Toast.LENGTH_SHORT).show();
//                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        Log.i("sfdgvcv", "onFailure: " + t.getMessage());
                    }
                });
    }

}