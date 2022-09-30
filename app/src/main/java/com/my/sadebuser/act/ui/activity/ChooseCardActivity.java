package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityChooseCardBinding;

import java.util.ArrayList;
import java.util.List;

public class ChooseCardActivity extends AppCompatActivity {

    private ActivityChooseCardBinding binding;
    private final List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChooseCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        binding.ivAdd.setOnClickListener(v -> {
            startActivity(new Intent(this,AddCardActivity.class));
        });

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
        binding.tvPay.setOnClickListener(v -> {
            startActivity(new Intent(this,PaymentSuccessActivity.class));
        });
    }

    private void init() {
        images.add(String.valueOf(R.drawable.creditcard));
        images.add(String.valueOf(R.drawable.creditcardtwo));
        images.add(String.valueOf(R.drawable.creditcard));
        images.add(String.valueOf(R.drawable.creditcardtwo));


//        binding.bannerSlider1.setAdapter(new MainSliderAdapter(images));
//        binding.bannerSlider1.setInterval(4000);
    }
}