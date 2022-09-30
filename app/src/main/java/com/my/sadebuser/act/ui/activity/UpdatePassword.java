package com.my.sadebuser.act.ui.activity;

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
import com.my.sadebuser.act.model.ChangePasswordResponse;
import com.my.sadebuser.act.model.ForgetPasswordResponse;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.network.NetworkConstraint;
 import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.RequestLAuthentication;
import com.my.sadebuser.databinding.ActivityUpdatePasswordBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePassword extends AppCompatActivity {

  private  ActivityUpdatePasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_password);

        SetupUI();
        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void SetupUI() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Validate()){
                    ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
                    binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                    Log.i("Xmxkx", "onClick: "+model.getResult().getId());
                    Log.i("Xmxkx", "onClick: "+binding.etOldPass.getText());
                    Log.i("Xmxkx", "onClick: "+binding.etNewConfirmPass.getText());
                    RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                            .create(RequestLAuthentication.class)
                            .changePassword(model.getResult().getId(),binding.etOldPass.getText().toString()
                            ,binding.etNewConfirmPass.getText().toString())
                            .enqueue(new Callback<JsonElement>() {
                                @Override
                                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                    binding.loaderLayout.loader.setVisibility(View.GONE);
                                    if (response.isSuccessful()){
                                        JsonObject  object=response.body().getAsJsonObject();
                                        int status =object.get("status").getAsInt();
                                        if (status==1){
                                            ForgetPasswordResponse forgetPasswordResponse=new Gson().fromJson(object,ForgetPasswordResponse.class);
                                            Toast.makeText(UpdatePassword.this, forgetPasswordResponse.getResult(), Toast.LENGTH_SHORT).show();
                                            finish();
                                        }else {
                                            Toast.makeText(UpdatePassword.this, "Please enter right old password", Toast.LENGTH_SHORT).show();
                                        }
                                         Log.i("svdvcx", "onResponse: "+response.body());
                                        Log.i("sxvxvx", "onResponse: "+response.toString());

                                    }
                                }
                                @Override
                                public void onFailure(Call<JsonElement> call, Throwable t) {
                                    binding.loaderLayout.loader.setVisibility(View.GONE);
                                    Log.i("dvbfdbc", "onFailure: "+t.getMessage());

                                }
                            });
                }
            }
        });
    }

    private boolean Validate(){
        if (binding.etOldPass.getText().toString().replace(" ", "").length() < 6) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_password,
                    Snackbar.LENGTH_SHORT).show();
            return false;

        }else if (binding.etNewPass.getText().toString().replace(" ", "").length() < 6){
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_password,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }else if (binding.etNewPass.getText()==binding.etNewConfirmPass.getText()){
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_not_matched,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}