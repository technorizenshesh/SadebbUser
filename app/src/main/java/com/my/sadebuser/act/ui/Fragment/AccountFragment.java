package com.my.sadebuser.act.ui.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.my.sadebuser.R;
import com.my.sadebuser.act.ui.activity.ChooseLanguageActivity;
import com.my.sadebuser.act.ui.activity.MainActivity;
import com.my.sadebuser.act.ui.activity.PaymentOption;
import com.my.sadebuser.act.ui.activity.ProfileActivity;
import com.my.sadebuser.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private Fragment fragment;
    FragmentAccountBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false);

        binding.RRProfile.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ProfileActivity.class));
//            ((MainActivity)getActivity()).finishAct();
        });

        binding.RRpayment.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), PaymentOption.class));
        });
        binding.RRLanguage.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChooseLanguageActivity.class).putExtra("from","Account"));
        });

        return binding.getRoot();
    }



    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_homeContainer, fragment);
        transaction.addToBackStack("home");
        transaction.commit();
    }


}