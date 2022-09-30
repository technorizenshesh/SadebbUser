package com.my.sadebuser.act.ui.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.notification.NotificationResponse;
import com.my.sadebuser.act.model.notification.ResultItem;
import com.my.sadebuser.act.network.FeedBackNotification;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.adapter.NotificationAdapter;
import com.my.sadebuser.databinding.FragmentNotificationBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends Fragment {
    private FragmentNotificationBinding binding;
//    private int API_COUNT,ALL_API_COUNT = 1;
    NotificationAdapter adapter;
    private List<ResultItem> results = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);

        adapter=  new NotificationAdapter(/*results,*/getActivity());
        adapter.setResults(results);
        binding.rvNotification.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        setAdapter();
    }

    private void setAdapter() {
        binding.rvNotification.setLayoutManager(new LinearLayoutManager(getContext()));

        String language= SharedPrefsManager.getInstance().getString("language");

        ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);

        if (model != null) {
            binding.rvNotification.setVisibility(View.GONE);
            binding.loaderLayout.loader.setVisibility(View.VISIBLE);

            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(FeedBackNotification.class)
                    .getFeedbackNotification(model.getResult().getId(), NetworkConstraint.notifytype,language)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
//                            ++API_COUNT;
//                            if(API_COUNT ==ALL_API_COUNT){
                                binding.rvNotification.setVisibility(View.VISIBLE);
                                binding.loaderLayout.loader.setVisibility(View.GONE);
//                            }

                            if (response != null) {
                                if (response.isSuccessful()) {
                                    JsonObject object = response.body().getAsJsonObject();
                                    int status = object.get("status").getAsInt();
                                    Log.i("scvdbx", "onResponse: " + response.toString());
                                    if (status == 1) {
                                        results=new ArrayList<>();
                                        NotificationResponse authentication = new Gson().fromJson(object, NotificationResponse.class);
                                        results.addAll(authentication.getResult());
                                        Log.i("dvdvvxv", "onResponse: " + results.toString());
                                        adapter.setResults(results);
                                        adapter.notifyDataSetChanged();

                                    } else {
                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                        Toast.makeText(getContext(), authentication.getResult(), Toast.LENGTH_SHORT).show();
                                        if (results.size()==0){
                                            binding.tvNoProductFound.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {

//                            ++API_COUNT;
//                            if(API_COUNT ==ALL_API_COUNT){
                                binding.loaderLayout.loader.setVisibility(View.GONE);
//                            }
                        }
                    });

        }
    }

}

