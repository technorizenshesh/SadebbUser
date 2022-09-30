package com.my.sadebuser.act.ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.category.CategoryResponse;
import com.my.sadebuser.act.model.servicelist.FavouriteAllServiceResponse;
import com.my.sadebuser.act.model.serviceprovider.ResultItem;
import com.my.sadebuser.act.model.serviceprovider.ServiceProviderListResponse;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.category.CategoryRequest;
import com.my.sadebuser.act.network.request.service.Serviecs;
import com.my.sadebuser.act.network.request.serviceprovider.ServiceProvider;
import com.my.sadebuser.act.ui.activity.AllCategoryActivity;
import com.my.sadebuser.act.ui.activity.NearMeAllListActivity;
import com.my.sadebuser.act.ui.activity.SearchActivity;
import com.my.sadebuser.act.ui.activity.ShopDetailsActivity;
import com.my.sadebuser.adapter.HomeCategoryRecyclerViewAdapter;
import com.my.sadebuser.adapter.HomeSaloonRecyclerViewAdapter;
import com.my.sadebuser.adapter.ServiceListByCatAdapter;
import com.my.sadebuser.adapter.ServiceListByCatFavouriteAdapter;
import com.my.sadebuser.adapter.ViewPagerDetailAdapter;
import com.my.sadebuser.model.HomeModel;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private List<com.my.sadebuser.act.model.category.ResultItem> list = new ArrayList<>();
    com.my.sadebuser.databinding.FragmentHomeBinding binding;
    private Fragment fragment;
    private List<ResultItem> serviceproviderlist = new ArrayList<>();
    private String Provider_Name,Provider_id,provider_img;
    private HomeSaloonRecyclerViewAdapter mAdapter;
    private HomeCategoryRecyclerViewAdapter mAdapterCategory;

    private List<FavouriteAllServiceResponse.ResultItem> serviceproviderlistFavourite = new ArrayList<>();
    ServiceListByCatFavouriteAdapter listByCatFavouriteAdapter;
    private int API_COUNT;
//    private final int ALL_API_COUNT = 2;
    private final List<com.my.sadebuser.act.model.servicelist.ResultItem> servicelist = new ArrayList<>();
    private final ArrayList<HomeModel> modelListCategory = new ArrayList<>();

    ResponseAuthentication model;
    int dotsCount;
    ImageView[] dots;
    ViewPagerDetailAdapter viewPagerAdapter;
    ArrayList<Integer> listImages;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
        if (model!=null){
             Picasso.get().load(model.getResult().getImage()).placeholder(R.drawable.user_placeholder).into(binding.cvImage);
        }

        binding.txtNearme.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), NearMeAllListActivity.class));
        });

        binding.tvAllcategory.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AllCategoryActivity.class));
        });

        binding.relativeSearchBtn.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SearchActivity.class));
        });

        binding.relativeSearchBtn.setFocusable(true);
        binding.relativeSearchBtn.setCursorVisible(true);

        binding.tvNear.setOnClickListener(v -> {
            binding.tvNear.setBackgroundResource(R.drawable.search_background);
            binding.tvFavourite.setBackgroundResource(R.drawable.bg_round_white);
            binding.txtNearme.setVisibility(View.VISIBLE);
            binding.recyclernearfavourite.setVisibility(View.GONE);
            binding.recyclernearme.setVisibility(View.VISIBLE);
        });

        binding.tvFavourite.setOnClickListener(v -> {
            binding.tvFavourite.setBackgroundResource(R.drawable.search_background);
            binding.tvNear.setBackgroundResource(R.drawable.bg_round_white);
            binding.txtNearme.setVisibility(View.INVISIBLE);
            binding.recyclernearfavourite.setVisibility(View.VISIBLE);
            binding.recyclernearme.setVisibility(View.GONE);
            favouriteList();
        });

        listImages = new ArrayList<>();
        listImages.add(R.drawable.barbershop);
        listImages.add(R.drawable.spa);
        viewPagerAdapter = new ViewPagerDetailAdapter(context);
        viewPagerAdapter.setImages(listImages);
        binding.viewPager.setAdapter(viewPagerAdapter);
        setViewPager();
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        providerList();
        CategoryResponse();
    }

    private void setViewPager() {

        binding.layoutBars.removeAllViews();
        viewPagerAdapter.setImages(listImages);
        viewPagerAdapter.notifyDataSetChanged();
        dotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(context);
            dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            binding.layoutBars.addView(dots[i], params);
        }
        if (dotsCount > 0) {
            dots[binding.viewPager.getCurrentItem()].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));
        }

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (listImages.size() > 1) {
            binding.layoutBars.setVisibility(View.VISIBLE);
        } else {
            binding.layoutBars.setVisibility(View.GONE);
        }
    }
    private void CategoryResponse() {
        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        String language= SharedPrefsManager.getInstance().getString("language");

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(CategoryRequest.class)
                .getCategory(language)
                .enqueue(new Callback<CategoryResponse>() {
                    @Override
                    public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
                            binding.llMain.setVisibility(View.VISIBLE);
                            binding.loaderLayout.loader.setVisibility(View.GONE);
//                        }
                        if (response != null) {
                            list=new ArrayList<>();
                            list.addAll(response.body().getResult());
                            mAdapterCategory = new HomeCategoryRecyclerViewAdapter(getActivity(), list);
                            binding.recyclerCategory.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            binding.recyclerCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
                            binding.recyclerCategory.setAdapter(mAdapterCategory);
                            mAdapterCategory.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onFailure(Call<CategoryResponse> call, Throwable t) {
//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
//                        }
                    }
                });
    }

    private void providerList() {

        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(ServiceProvider.class)
                .getServiceProviderList()
                .enqueue(new Callback<ServiceProviderListResponse>() {
                    @Override
                    public void onResponse(Call<ServiceProviderListResponse> call, Response<ServiceProviderListResponse> response) {
//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
                            binding.llMain.setVisibility(View.VISIBLE);
                            binding.loaderLayout.loader.setVisibility(View.GONE);

//                        }

                        serviceproviderlist=new ArrayList<>();
                        serviceproviderlist.addAll(response.body().getResult());
                        Log.i("sdvdc", "onResponse: "+response.toString());
                        Log.i("sdvdc", "onResponse: "+response.body());
                        mAdapter = new HomeSaloonRecyclerViewAdapter(getActivity(),serviceproviderlist, new HomeSaloonRecyclerViewAdapter.ItemPos() {
                            @Override
                            public void selectedpos(int pos) {
                                allServicelist(serviceproviderlist.get(pos).getId());
                                Provider_Name = serviceproviderlist.get(pos).getBusiness_name();//UserName();
                                Provider_id=serviceproviderlist.get(pos).getId();
                                provider_img=serviceproviderlist.get(pos).getBusiness_profile_image();
                                binding.loaderLayout.loader.setVisibility(View.VISIBLE);
                            }
                        });
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        binding.recyclernearme.setLayoutManager(linearLayoutManager);
                        binding.recyclernearme.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ServiceProviderListResponse> call, Throwable t) {
//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
//                        }
                        Log.i("sfvvs", "onFailure: " + t.getMessage());
                    }
                });
    }

    private void allServicelist(String id) {
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(Serviecs.class)
                .getServices(id)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                        if (response.isSuccessful()) {

                            binding.loaderLayout.loader.setVisibility(View.GONE);

                            JsonObject jsonObject = response.body().getAsJsonObject();
                            int status = jsonObject.get("status").getAsInt();
                            if (status == 1) {
                                Intent intent = new Intent(getContext(), ShopDetailsActivity.class);
                                intent.putExtra("response", new Gson().toJson(response.body()));
                                intent.putExtra("Provider_Name", Provider_Name);
                                intent.putExtra("Provider_id", Provider_id);
                                intent.putExtra("provider_img", provider_img);
                                getContext().startActivity(intent);
                            } else {
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                                Toast.makeText(getContext(), getString(R.string.there_is_no_service), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        Log.i("sfdgvcv", "onFailure: " + t.getMessage());
                    }
                });
    }

    private void favouriteList() {

//        binding.llMain.setVisibility(View.GONE);
//        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(ServiceProvider.class)
                .get_favorite_service(model.getResult().getId())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.isSuccessful()) {
                                String responseData = response.body() != null ? response.body().string() : "";
                                JSONObject object = new JSONObject(responseData);
                                if (object.optString("status").equals("1")) {
//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
//                            binding.llMain.setVisibility(View.VISIBLE);
//                            binding.loaderLayout.loader.setVisibility(View.GONE);
//
//                        }
                                    serviceproviderlistFavourite = new ArrayList<>();

                                    FavouriteAllServiceResponse favouriteAllServiceResponse = new Gson().fromJson(responseData, FavouriteAllServiceResponse.class);

                                    serviceproviderlistFavourite.addAll(favouriteAllServiceResponse.getResult());
                                    Log.i("sdvdc", "onResponse: " + response.toString());
                                    Log.i("sdvdc", "onResponse: " + response.body());
                                    listByCatFavouriteAdapter = new ServiceListByCatFavouriteAdapter(serviceproviderlistFavourite, new ServiceListByCatFavouriteAdapter.ItemPos() {
                                        @Override
                                        public void selectedpos(int pos) {
                                            allServicelist(serviceproviderlistFavourite.get(pos).getService_details().getUserId());
                                            Provider_Name = serviceproviderlistFavourite.get(pos).getService_details().getBusiness_name();//getUser_name();
                                            Provider_id = serviceproviderlistFavourite.get(pos).getService_details().getUserId();
                                            provider_img = serviceproviderlistFavourite.get(pos).getService_details().getImage1();
                                            binding.loaderLayout.loader.setVisibility(View.VISIBLE);

                                        }
                                    });
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                    binding.recyclernearfavourite.setLayoutManager(linearLayoutManager);
                                    binding.recyclernearfavourite.setAdapter(listByCatFavouriteAdapter);
                                    mAdapter.notifyDataSetChanged();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        ++API_COUNT;
//                        if (API_COUNT == ALL_API_COUNT) {
//                            binding.loaderLayout.loader.setVisibility(View.GONE);
//                        }
//                        Log.i("sfvvs", "onFailure: " + t.getMessage());
                    }
                });
    }


}