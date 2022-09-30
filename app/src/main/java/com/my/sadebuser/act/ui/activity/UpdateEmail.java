package com.my.sadebuser.act.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
 import com.my.sadebuser.R;
 import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.network.NetworkConstraint;
 import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.RequestLAuthentication;
import com.my.sadebuser.databinding.ActivityUpdateEmailBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.my.sadebuser.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateEmail extends AppCompatActivity {

   private ActivityUpdateEmailBinding binding;
    private  ResponseAuthentication model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this, R.layout.activity_update_email);

        SetupUI();

          model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);



          binding.etEmaillogin.setText(model.getResult().getEmail());


        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void SetupUI() {


        binding.loginID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                if (Utils.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmaillogin.getText().toString().replace(" ", "")).matches()
                ){
                    Log.i("cscscs", "onClick: "+model.getResult().getId().toString());
                    Log.i("csvdsv", "onClick: "+binding.etEmaillogin.getText().toString());
                    RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                            .create(RequestLAuthentication.class)
                            .getProfileUpdate(model.getResult().getUserName(),binding.etEmaillogin.getText().toString(),
                                    model.getResult().getMobile(),model.getResult().getId(),model.getResult().getGender())
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
                                    Log.i("dsgvdgvd", "onResponse: "+t.getMessage());

                                }
                            });
                }else {
                    Snackbar.make(findViewById(android.R.id.content),
                            R.string.text_register_correct_email,
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}