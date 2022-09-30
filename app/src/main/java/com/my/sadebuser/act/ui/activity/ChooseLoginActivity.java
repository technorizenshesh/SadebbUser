package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityChooseLoginBinding;

public class ChooseLoginActivity extends AppCompatActivity {

    ActivityChooseLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_choose_login);

        binding.txtSingIn.setOnClickListener(v -> {

            Intent i = new Intent(ChooseLoginActivity.this, Login.class);
            startActivity(i);
            finish();

        });

        binding.txtSingup.setOnClickListener(v -> {

            Intent i = new Intent(ChooseLoginActivity.this, Register.class);
            startActivity(i);
            finish();

        });
    }
}