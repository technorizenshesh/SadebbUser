package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.bookingdetail.BookingResponse;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.bookingdetail.BookingRequest;
import com.my.sadebuser.databinding.ActivityBookingDetailsBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingDetails extends AppCompatActivity {

    private final Calendar selectedDateTime = Calendar.getInstance();
    private ActivityBookingDetailsBinding binding;
    private ResponseAuthentication model;
    private String service_id, start_time, end_time, date, email, no;
    private ResultItem item;
    private String providerUserId;

    private Boolean test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_details);
        init();
        binding.RRBook.setOnClickListener(v -> {
            booking();
        });
        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
    }
    private void init() {
        model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        item = new Gson().fromJson(getIntent().getStringExtra("item"), ResultItem.class);
        service_id = getIntent().getStringExtra("service_id");
        end_time = getIntent().getStringExtra("end_time");
        start_time = getIntent().getStringExtra("start_time");
        date = getIntent().getStringExtra("date");
        email = getIntent().getStringExtra("email");
        no = getIntent().getStringExtra("no");
        providerUserId = getIntent().getStringExtra("providerUserId");
        Picasso.get().load(item.getImage1()).into(binding.img);
        binding.tvServiceName.setText(item.getServiceName());
        binding.tvServiceCost.setText("$0.00");
        binding.tvTax.setText("$0.00");
        binding.tvServicePrice.setText("$" + item.getServicePrice() + ".00");
        binding.tvShopname.setText(item.getServiceName());
        binding.tvTotal.setText("$" + item.getServicePrice() + ".00");
        binding.tvAppointmentTime.setText(date + " " + start_time);
        Log.i("dsfsfsfds", "provi: "+providerUserId);
    }

    private void booking() {

        String language= SharedPrefsManager.getInstance().getString("language");

        if (model != null) {
            binding.loaderLayout.loader.setVisibility(View.VISIBLE);
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(BookingRequest.class)
                    .booking(model.getResult().getId(),
                            service_id,
                            date,
                            start_time, end_time,
                            email,
                            no,
                            "22.7196°",
                            "75.8577°",
                            providerUserId,
                            language)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                            Log.i("adsfscs", "onResponse: " + response.toString());
                            Log.i("adsfscs", "onResponse: " + response.body());
                            if (response != null) {
                                if (response.isSuccessful()) {
                                    JsonObject object = response.body().getAsJsonObject();
                                    int status = object.get("status").getAsInt();
                                    if (status == 1) {
                                        Toast.makeText(BookingDetails.this, getString(R.string.your_request_is_successfully_sent_to_admin), Toast.LENGTH_SHORT).show();
                                        BookingResponse authentication = new Gson().fromJson(object, BookingResponse.class);
                                        Log.i("adsfscs", "authen: " + authentication.getResult().toString());
//                                        startActivity(new Intent(BookingDetails.this, PaymentOption.class));
                                    } else {
                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
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
                            Log.i("adsfscs", "failure: " + 1);
                            Log.i("adsfscs", "failure: " + t.getMessage());
                        }
                    });
        }
    }
}