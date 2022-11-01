package com.my.sadebuser.act.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.category.CategoryResponse;
import com.my.sadebuser.act.model.category.ResultItem;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.category.CategoryRequest;
import com.my.sadebuser.act.ui.Fragment.HomeFragment;
import com.my.sadebuser.adapter.HomeCategoryRecyclerViewAdapter;
import com.my.sadebuser.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.sadebuser.databinding.ActivityAllCategoryBinding;
import com.my.sadebuser.utils.SharedPrefsManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoryActivity extends AppCompatActivity {

    private ActivityAllCategoryBinding binding;
    private HomeCategoryRecyclerViewAdapter mAdapterCategory;
    private final List<ResultItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        CategoryResponse();

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

    }

    private void CategoryResponse() {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        String language= SharedPrefsManager.getInstance().getString("language");
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(CategoryRequest.class)
                .getCategory(language)
                .enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                        binding.loaderLayout.loader.setVisibility(View.GONE);

                        if (response != null) {
                            list.addAll(response.body().getResult());
                            mAdapterCategory = new HomeCategoryRecyclerViewAdapter(AllCategoryActivity.this, list, new HomeCategoryRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position, ResultItem model) {

                                }
                            });
                            binding.recyclerCategory.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AllCategoryActivity.this);
                            binding.recyclerCategory.setLayoutManager(new GridLayoutManager(AllCategoryActivity.this,3));
                            binding.recyclerCategory.setAdapter(mAdapterCategory);

                            mAdapterCategory.SetOnItemClickListener(new HomeCategoryRecyclerViewAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position, com.my.sadebuser.act.model.category.ResultItem model) {

                                }
                            });
                            mAdapterCategory.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<CategoryResponse> call, Throwable t) {

                        binding.loaderLayout.loader.setVisibility(View.GONE);

                    }
                });
    }

}