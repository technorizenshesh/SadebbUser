package com.my.sadebuser.act.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.nearbymepro.NearbymeProviderResponse;
import com.my.sadebuser.act.model.nearbymepro.ResultItem;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.service.Serviecs;
import com.my.sadebuser.act.network.request.serviceprovider.GetNearByMeProvider;
import com.my.sadebuser.act.ui.activity.ShopDetailsActivity;
import com.my.sadebuser.adapter.NearestProviderAdapter;
import com.my.sadebuser.databinding.FragmentNearestProviderListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearestProviderListFragment extends Fragment {

    private final List<ResultItem> list = new ArrayList<>();
    private FragmentNearestProviderListBinding binding;
    private NearestProviderAdapter adapter;
    private String Provider_Name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNearestProviderListBinding.inflate(getLayoutInflater());
        binding.rvNearmeProvider.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NearestProviderAdapter(getActivity(),list, position -> {
            allServicelist(list.get(position).getId());
            Provider_Name = list.get(position).getBusiness_name();//UserName();
         });
        binding.rvNearmeProvider.setAdapter(adapter);
        getproviderlist();
        return binding.getRoot();

    }

    private void getproviderlist() {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(GetNearByMeProvider.class)
                .getproviderlist()
                .enqueue(new Callback<NearbymeProviderResponse>() {
                    @Override
                    public void onResponse(Call<NearbymeProviderResponse> call, Response<NearbymeProviderResponse> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        if (response != null) {
                            if (response.isSuccessful()) {
                                list.addAll(response.body().getResult());
                                adapter.notifyDataSetChanged();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NearbymeProviderResponse> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                    }
                });
    }

    private void allServicelist(String id) {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(Serviecs.class)
                .getServices(id)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);


                        if (response.isSuccessful()) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);

                            JsonObject jsonObject = response.body().getAsJsonObject();
                            int status = jsonObject.get("status").getAsInt();
                            if (status == 1) {
                                Intent intent = new Intent(getContext(), ShopDetailsActivity.class);
                                intent.putExtra("response", new Gson().toJson(response.body()));
                                intent.putExtra("Provider_Name", Provider_Name);
                                getContext().startActivity(intent);

                            } else {
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                                Toast.makeText(getContext(), getString(R.string.there_is_no_service), Toast.LENGTH_SHORT).show();
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