package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import com.my.sadebuser.act.ui.Fragment.MapFragment;
import com.my.sadebuser.act.ui.Fragment.NearestProviderListFragment;
import com.my.sadebuser.R;
import com.my.sadebuser.databinding.ActivityNearMeAllListBinding;

public class NearMeAllListActivity extends AppCompatActivity {

   private ActivityNearMeAllListBinding binding;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_near_me_all_list);

        replace(new NearestProviderListFragment());
        binding.RRBack.setOnClickListener(v -> {
            onBackPressed();
        });

        binding.btnList.setOnClickListener(v -> {
            binding.btnList.setBackgroundResource(R.drawable.bg_blue_border);
            binding.btnMap.setBackgroundResource(R.drawable.border_blue);
            binding.btnList.setTextColor(Color.parseColor("#FFFFFF"));
            binding.btnMap.setTextColor(Color.parseColor("#0053B4"));
            replace(new NearestProviderListFragment());

        });

        binding.btnMap.setOnClickListener(v -> {
            binding.btnList.setBackgroundResource(R.drawable.border_blue);
            binding.btnMap.setBackgroundResource(R.drawable.bg_blue_border);
            binding.btnList.setTextColor(Color.parseColor("#0053B4"));
            binding.btnMap.setTextColor(Color.parseColor("#FFFFFF"));
            replace(new MapFragment());
        });

    }

    public void replace(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
         transaction.commit();
    }

}