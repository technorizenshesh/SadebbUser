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
import com.my.sadebuser.act.model.ForgetPasswordResponse;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.RequestLAuthentication;
import com.my.sadebuser.databinding.ActivityForogotPasswordBinding;
import com.my.sadebuser.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForogotPassword extends AppCompatActivity {

    private ActivityForogotPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forogot_password);


        SetupUI();
        init();
    }

    private void init() {
        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void SetupUI() {

        binding.loginID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                if (Utils.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText().toString().replace(" ", "")).matches()) {
                    RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                            .create(RequestLAuthentication.class)
                            .getForgetPass(binding.etEmail.getText().toString())
                            .enqueue(new Callback<JsonElement>() {
                                @Override
                                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                    binding.loaderLayout.loader.setVisibility(View.GONE);
                                    if (response.isSuccessful()) {
                                        if (response.body() != null) {
                                            JsonObject object = response.body().getAsJsonObject();
                                            int status = object.get("status").getAsInt();
                                            if (status == 1) {
                                                ForgetPasswordResponse authentication = new Gson().fromJson(object, ForgetPasswordResponse.class);
                                                Log.i("dscbhs", "onResponse: " + object.toString());
                                                Toast.makeText(ForogotPassword.this,String.format(getString(R.string.password_sent_to_email), authentication.getMessage()) /*"Password "+authentication.getMessage()+" sent to your registered email"*/, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(ForogotPassword.this, OtpVerificationActivity.class));
                                                finish();
                                            } else {
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
                                    Log.i("vgdxvd", "onFailure: " + t.getMessage());
                                    Log.i("vgdxvd", "onFailure: " + t.getMessage());
                                }
                            });
                } else {
                    binding.loaderLayout.loader.setVisibility(View.GONE);
                    Snackbar.make(findViewById(android.R.id.content),
                            R.string.text_register_correct_email,
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}