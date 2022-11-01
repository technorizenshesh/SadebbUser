package com.my.sadebuser.act.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityPaymentOptionBinding;

public class PaymentOption extends AppCompatActivity {

    ActivityPaymentOptionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_option);

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
        binding.RRCreditCard.setOnClickListener(v -> {
            startActivity(new Intent(this,ChooseCardActivity.class));
        });

//        binding.RRBankTransFr.setOnClickListener(v -> {
//        });

    }
}