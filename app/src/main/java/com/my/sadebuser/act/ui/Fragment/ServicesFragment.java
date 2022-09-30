package com.my.sadebuser.act.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.my.sadebuser.act.ui.activity.ShopTimeAvailavility;
import com.my.sadebuser.adapter.SelectServiceAdapter;
import com.my.sadebuser.utils.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;


public class ServicesFragment extends Fragment {

    private View view;
    private RecyclerView rv_services;
    private RelativeLayout RR_book;
    private SelectServiceAdapter adapter;
    private String id;
    private List<ResultItem> servicelist = new ArrayList<>();
    private ResultItem item;

    String provider_Name,provider_img;
    public static ServicesFragment newInstance(List<ResultItem> servicelist, String provider_img, String provider_Name) {
        ServicesFragment f = new ServicesFragment();
        Bundle args = new Bundle();
        args.putString("servicelist", new Gson().toJson(servicelist));
        args.putString("Provider_Name",provider_Name);
        args.putString("provider_img",provider_img);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_services, container, false);

        rv_services = view.findViewById(R.id.rv_services);
        RR_book = view.findViewById(R.id.RR_book);

        Bundle args = getArguments();
        Log.i("Xsvd", "onCreateView: +" + 1);
        if (args != null) {
            Log.i("Xsvd", "onCreateView: +" + 2);
            SharedPrefsManager.GenericType type = new SharedPrefsManager.GenericType(ResultItem.class);
            servicelist = new Gson().fromJson(args.getString("servicelist"), type);
            provider_Name=args.getString("Provider_Name");
            provider_img=args.getString("provider_img");
            Log.i("xfdvcv", "onCreateView: " + servicelist);
            Log.i("xfdvcv", "onCreateView: " + servicelist.size());
        }

        rv_services.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SelectServiceAdapter(servicelist, new SelectServiceAdapter.Clickcallback() {
            @Override
            public void click(int position) {
                  id =servicelist.get(position).getId();
                  item =servicelist.get(position);
                Log.i("dczvvxvx", "click: "+id);
            }
        });
        rv_services.setAdapter(adapter);

        RR_book.setOnClickListener(v -> {

            Intent intent = new Intent(getContext(), ShopTimeAvailavility.class);
            intent.putExtra("service_id", id);
            intent.putExtra("provider_Name", provider_Name);
            intent.putExtra("provider_img", provider_img);
//            intent.putExtra("providerUserId", providerUserId);
            intent.putExtra("item", new Gson().toJson(item));
            startActivity(intent);

        });
        return view;
    }
}