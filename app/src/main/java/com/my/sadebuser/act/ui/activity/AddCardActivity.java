package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.my.sadebuser.databinding.ActivityAddCardBinding;

public class AddCardActivity extends AppCompatActivity {

    private ActivityAddCardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
        binding.RRBook.setOnClickListener(v -> {
            finish();
        });
    }
}