package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.search.ResultItem;
import com.my.sadebuser.act.model.search.SearchProviderResponse;
import com.my.sadebuser.act.model.serviceprovider.ServiceProviderListResponse;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.search.SearchroviderByName;
import com.my.sadebuser.act.network.request.service.Serviecs;
import com.my.sadebuser.act.ui.Fragment.HomeFragment;
import com.my.sadebuser.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.sadebuser.adapter.SuggestionAdapter;
import com.my.sadebuser.databinding.ActivitySearchBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

     private ActivitySearchBinding binding;
     private List<ResultItem> list=new ArrayList<>();
     private HomeSaloonRecyclerViewAdapter  adapter;
     private String Provider_Name,Provider_id,provider_img;
     private List<com.my.sadebuser.act.model.serviceprovider.ResultItem> serviceproviderlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init(){
         binding.rvSuggestion.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter = new HomeSaloonRecyclerViewAdapter("",this, serviceproviderlist, new HomeSaloonRecyclerViewAdapter.ItemPos() {
            @Override
            public void selectedpos(int pos) {
                allServicelist(serviceproviderlist.get(pos).getId());
                Provider_Name = serviceproviderlist.get(pos).getBusiness_name();//UserName();
                Provider_id=serviceproviderlist.get(pos).getId();
                provider_img=serviceproviderlist.get(pos).getImage();
                binding.loaderLayout.loader.setVisibility(View.VISIBLE);
            }

            @Override
            public void addFavourite(int pos) {

            }
        });

        binding.rvSuggestion.setAdapter(adapter);

        binding.ivSearch.setOnClickListener(v -> {
            binding.loaderLayout.loader.setVisibility(View.VISIBLE);
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(SearchroviderByName.class)
                    .getproviderbyname(binding.etSearch.getText().toString())
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                            if (response.body() != null) {
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                                if (response.isSuccessful()) {
                                    JsonObject object = response.body().getAsJsonObject();
                                    int status = object.get("status").getAsInt();
                                    Log.i("scvdbx", "sta: " + status);

                                    if (status == 1) {
                                        serviceproviderlist = new ArrayList<>();
                                        ServiceProviderListResponse authentication = new Gson().fromJson(object, ServiceProviderListResponse.class);
                                        serviceproviderlist.addAll(authentication.getResult());
                                        adapter = new HomeSaloonRecyclerViewAdapter("",SearchActivity.this, serviceproviderlist, new HomeSaloonRecyclerViewAdapter.ItemPos() {
                                            @Override
                                            public void selectedpos(int pos) {
                                                allServicelist(serviceproviderlist.get(pos).getId());
                                                Provider_Name = serviceproviderlist.get(pos).getBusiness_name();//UserName();
                                                Provider_id=serviceproviderlist.get(pos).getId();
                                                provider_img=serviceproviderlist.get(pos).getImage();
                                                binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                                            }

                                            @Override
                                            public void addFavourite(int pos) {

                                            }
                                        });

                                        binding.rvSuggestion.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();

                                        adapter.SetOnItemClickListener(new HomeSaloonRecyclerViewAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(int position, com.my.sadebuser.act.model.serviceprovider.ResultItem model) {
                                                allServicelist(model.getId());
                                                Provider_Name = model.getBusiness_name();//UserName();
                                                binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                                            }
                                        });

                                    }else {
                                        binding.loaderLayout.loader.setVisibility(View.GONE);

                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                        Log.i("dscbhs", "onResponse: " + authentication);
                                        Snackbar.make(findViewById(android.R.id.content),
                                                authentication.getMessage(),
                                                Snackbar.LENGTH_SHORT).show();

                                        if (serviceproviderlist.size()==0){
                                            binding.tvNoProductFound.setVisibility(View.VISIBLE);
                                        }

                                    }

                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            Log.i("svddbf", "onFailure: "+t.getMessage());
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                        }
                    });
        });

    }

    private void allServicelist(String id) {
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(Serviecs.class)
                .getServices(id)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {


                        if (response.isSuccessful()) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                            JsonObject jsonObject = response.body().getAsJsonObject();
                            int status = jsonObject.get("status").getAsInt();
                            if (status == 1) {
                                Intent intent = new Intent(SearchActivity.this, ShopDetailsActivity.class);
                                intent.putExtra("response", new Gson().toJson(response.body()));
                                intent.putExtra("Provider_Name", Provider_Name);
                                intent.putExtra("Provider_id", Provider_id);
                                intent.putExtra("provider_img", provider_img);
                                SearchActivity.this.startActivity(intent);

                            } else {
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                                Toast.makeText(SearchActivity.this, "There is No Service", Toast.LENGTH_SHORT).show();
                            }
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