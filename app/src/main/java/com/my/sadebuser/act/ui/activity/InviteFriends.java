package com.my.sadebuser.act.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityInviteFriendsBinding;

public class InviteFriends extends AppCompatActivity {

   ActivityInviteFriendsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_invite_friends);
        binding.ivBack.setOnClickListener(v -> {
            onBackPressed();
        });
        binding.tvSkip.setOnClickListener(v -> {
            finish();
        });
    }
}