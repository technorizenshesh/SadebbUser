package com.my.sadebuser.act.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.RequestLAuthentication;
import com.my.sadebuser.adapter.SpinnerGender;
import com.my.sadebuser.databinding.ActivityUpdateGenderBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateGender extends AppCompatActivity {

    private ActivityUpdateGenderBinding binding;
    private  ResponseAuthentication model;

    private SpinnerGender adapter;
    private  TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_gender);


        binding.ivBack.setOnClickListener(v -> {

            onBackPressed();

        });

        binding.tvSave.setOnClickListener(v -> {
            SetupUI();
        });

        setAdapter();
    }

    private void setAdapter() {
        String[] s = {"Male", "Female", "Other"};
        adapter = new SpinnerGender(this, s);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout linearLayout = (LinearLayout) binding.spinner.getSelectedView();
                  textView = linearLayout.findViewById(R.id.text);
                if (textView.getText().equals("Female")) {

                    binding.tvFemale.setTextColor(Color.parseColor("#0053B4"));
                    binding.tvOther.setTextColor(Color.parseColor("#9098B1"));
                    binding.tvMale.setTextColor(Color.parseColor("#9098B1"));

                } else if (textView.getText().equals("Male")) {
                    binding.tvFemale.setTextColor(Color.parseColor("#9098B1"));
                    binding.tvOther.setTextColor(Color.parseColor("#9098B1"));
                    binding.tvMale.setTextColor(Color.parseColor("#0053B4"));

                } else if (textView.getText().equals("Other")) {
                    binding.tvFemale.setTextColor(Color.parseColor("#9098B1"));
                    binding.tvOther.setTextColor(Color.parseColor("#0053B4"));
                    binding.tvMale.setTextColor(Color.parseColor("#9098B1"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void SetupUI() {
        binding.tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);

                binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                        .create(RequestLAuthentication.class)
                        .getProfileUpdate(model.getResult().getUserName(),model.getResult().getEmail(),
                                model.getResult().getMobile(),model.getResult().getId(),
                                textView.getText().toString())
                        .enqueue(new Callback<ResponseAuthentication>() {
                            @Override
                            public void onResponse(Call<ResponseAuthentication> call, Response<ResponseAuthentication> response) {

                                if (response.isSuccessful()){
                                    binding.loaderLayout.loader.setVisibility(View.GONE);
                                    SharedPrefsManager.getInstance().setObject(SharePrefrenceConstraint.user, response.body());
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseAuthentication> call, Throwable t) {
                                binding.loaderLayout.loader.setVisibility(View.GONE);

                            }
                        });

            }
        });
    }


}