package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.network.NetworkConstraint;
 import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.RequestLAuthentication;
import com.my.sadebuser.databinding.ActivityRegisterBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.my.sadebuser.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.my.sadebuser.R.string.text_register_not_matched;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private String called_from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);

        try {
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get new FCM registration token
                String token = task.getResult();
                SharedPrefsManager.getInstance().setString(SharePrefrenceConstraint.register_id,token);

                Log.d("TAG-token", token);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.tvSignIn.setOnClickListener(v -> {
            startActivity(new Intent(Register.this, Login.class));
        });

        SetupUI();

        if (called_from != null && called_from.equalsIgnoreCase("add"))
            binding.ccp.registerPhoneNumberTextView(binding.etNo);
            binding.ccp.setCountryForPhoneCode(57);

        Log.i("zfcsfsfvszf", "onCreate: "+binding.ccp.getSelectedCountryCodeWithPlus());
        Log.i("zfcsfsfvszf", "onCreate: "+binding.ccp.getSelectedCountryCode());
        Log.i("zfcsfsfvszf", "onCreate: "+binding.ccp.getSelectedCountryNameCode());

    }

    private void SetupUI() {
        binding.btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                    RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                            .create(RequestLAuthentication.class)
                            .getSignUp(binding.etFullName.getText().toString(),binding.etSurName.getText().toString(), binding.etPassword.getText().toString(), binding.etEmail.getText().toString()
                                    , NetworkConstraint.type, binding.etNo.getText().toString(), "",SharedPrefsManager.getInstance().getString(SharePrefrenceConstraint.register_id),

                            "","","","","","",""
                            )
                            .enqueue(new Callback<JsonElement>() {
                                @Override
                                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                                    binding.loaderLayout.loader.setVisibility(View.GONE);
                                    if (response.body() != null) {
                                        if (response.isSuccessful()) {
                                            JsonObject object = response.body().getAsJsonObject();
                                            int status = object.get("status").getAsInt();
                                            if (status == 1) {
//                                                SharedPrefsManager.getInstance().setObject(SharePrefrenceConstraint.user, object);
                                                SharedPrefsManager.getInstance().setString("ccp",binding.ccp.getSelectedCountryCode());
                                                ResponseAuthentication authentication = new Gson().fromJson(object, ResponseAuthentication.class);
                                                Log.i("dscbhs", "onResponse: " + authentication);
                                                Toast.makeText(Register.this, getString(R.string.message_active_account), Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Register.this, WaitingActivity.class));//Login.class));//MainActivity.class));
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
                                    Log.i("acsxgvdgf", "onFailure: " + t.getMessage());
                                }
                            });
                }
            }
        });
    }

    private boolean validate() {
        Log.i("fzsfsgsg", "validate: "+binding.ccp.getSelectedCountryCode());

        if (TextUtils.isEmpty(binding.etFullName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.enter_first_name, Snackbar.LENGTH_SHORT).show();
            return false;
        }else  if (TextUtils.isEmpty(binding.etSurName.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.enter_last_name, Snackbar.LENGTH_SHORT).show();
            return false;
        }else if (TextUtils.isEmpty(binding.etNo.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content), R.string.text_register_no, Snackbar.LENGTH_SHORT).show();
            return false;
        }

         else if (binding.ccp.getSelectedCountryCode().equals("57")&&binding.etNo.getText().toString().replace(" ", "").length()!=10) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }

        else if (binding.ccp.getSelectedCountryCode().equals("507")&&binding.etNo.getText().toString().replace(" ", "").length()!=8) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.ccp.getSelectedCountryCode().equals("56")&&binding.etNo.getText().toString().replace(" ", "").length()!=11) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.ccp.getSelectedCountryCode().equals("1")&&binding.etNo.getText().toString().replace(" ", "").length()!=10) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.ccp.getSelectedCountryCode().equals("91")&&binding.etNo.getText().toString().replace(" ", "").length()!=10) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_right_no,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else  if (TextUtils.isEmpty(binding.etEmail.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_email,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }else if (!Utils.EMAIL_ADDRESS_PATTERN.matcher(binding.etEmail.getText().toString().replace(" ", "")).matches()
        ) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_correct_email,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else  if (TextUtils.isEmpty(binding.etPassword.getText().toString().replace(" ", ""))) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.enter_pass,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else if (binding.etPassword.getText().toString().replace(" ", "").length() < 6) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_password,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (!binding.etConfirmPassword.getText().toString().equals(binding.etPassword.getText().toString())) {
            Snackbar.make(findViewById(android.R.id.content),
                    text_register_not_matched,
                    Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (binding.etConfirmPassword.getText().toString().replace(" ", "").length() < 6) {
            Snackbar.make(findViewById(android.R.id.content),
                    R.string.text_register_password,
                    Snackbar.LENGTH_SHORT).show();
            return false;

        } else if (!binding.cbBtn.isChecked()) {
            Snackbar.make(binding.getRoot(),
                    R.string.text_register_term,
                    Snackbar.LENGTH_SHORT).show();
            return false;

        } else {
            return true;
        }
    }

}