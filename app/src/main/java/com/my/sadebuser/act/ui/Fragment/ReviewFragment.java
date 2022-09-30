package com.my.sadebuser.act.ui.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthError;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.addreview.AddReviewResponse;
import com.my.sadebuser.act.model.reviews.AllReviewsResponse;
import com.my.sadebuser.act.model.reviews.ResultItem;
import com.my.sadebuser.act.network.NetworkConstraint;
import com.my.sadebuser.act.network.RetrofitClient;
import com.my.sadebuser.act.network.request.reviews.AddReviewReq;
import com.my.sadebuser.act.network.request.reviews.GetAllReviews;
import com.my.sadebuser.adapter.ReviewAdapter;
import com.my.sadebuser.databinding.FragmentReviewBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.my.sadebuser.utils.Utils;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewFragment extends Fragment {

    private FragmentReviewBinding binding;
    private String Provider_id;
    String provider_Name,provider_img;
    private final List<ResultItem> list = new ArrayList<>();
    private ReviewAdapter adapter;

    public static ReviewFragment newInstance(String Provider_id, String provider_Name, String provider_img) {
        ReviewFragment f = new ReviewFragment();
        Bundle args = new Bundle();
        args.putString("Provider_id", Provider_id);
        args.putString("Provider_Name",provider_Name);
        args.putString("provider_img",provider_img);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getContext() fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_review, container, false);

        binding.rvReviews.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter=new ReviewAdapter(list);
        binding.rvReviews.setAdapter(adapter);
        Bundle args = getArguments();
        Log.i("Xsvd", "onCreateView: +" + 1);
        if (args != null) {
            Provider_id = args.getString("Provider_id");
            provider_Name=args.getString("Provider_Name");
            provider_img=args.getString("provider_img");
            Log.i(" mnvc", "onCreateView: "+Provider_id);
        }
        getReviews();

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.tvYourreview.setOnClickListener(v -> {
            showAddReviewDialog();
        });
    }

    private void getReviews() {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(GetAllReviews.class)
                .getAllreviews(Provider_id)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                        binding.loaderLayout.loader.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            binding.loaderLayout.loader.setVisibility(View.GONE);
                            JsonObject jsonObject = response.body().getAsJsonObject();
                            int status = jsonObject.get("status").getAsInt();
                            if (status == 1) {
                                AllReviewsResponse authentication = new Gson().fromJson(jsonObject, AllReviewsResponse.class);
                                list.addAll(authentication.getResult());
                                adapter.notifyDataSetChanged();
                            }else{
                                binding.loaderLayout.loader.setVisibility(View.GONE);

                                ResponseAuthError authentication = new Gson().fromJson(jsonObject, ResponseAuthError.class);
                                Log.i("dscbhs", "onResponse: " + authentication);
                                Toast.makeText(getContext(), authentication.getResult(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);

                    }
                });
    }

    private void showAddReviewDialog() {

        DialogPlus dialogPlus =  DialogPlus.newDialog(getContext())
                .setContentHolder(new ViewHolder(R.layout.review_dialog))
                .setGravity(Gravity.CENTER)
                .setCancelable(true)
                .setMargin(Utils.dpToPx(getContext(),20),Utils.dpToPx(getContext(),50),Utils.dpToPx(getContext(),20),Utils.dpToPx(getContext(),50))
                .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                .create();

        View holderView = (LinearLayout)dialogPlus.getHolderView();

        TextView tv_Yes = holderView.findViewById(R.id.tv_Yes);
        TextView tv_No = holderView.findViewById(R.id.tv_No);
        RatingBar rb_Main = holderView.findViewById(R.id.rb_Main);
        EditText ev_Review = holderView.findViewById(R.id.ev_Review);

//        if(!status){
//            rb_Main.setRating(Float.parseFloat(order.getRating()));
//            ev_Review.setText(order.getComment());
//        }

        tv_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPlus.dismiss();
            }
        });

        tv_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidEditTextInput(ev_Review)){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.loaderLayout.loader.setVisibility(View.VISIBLE);
                            ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
                            addReview(Provider_id,model.getResult().getId(),ev_Review.getText().toString(),Float.toString(rb_Main.getRating()));
                            dialogPlus.dismiss();
                            Log.i("scsfcdcdc", "run: "+Provider_id);
                            Log.i("scsfcdcdc", "run: "+model.getResult().getId());
                            Log.i("scsfcdcdc", "run: "+model.getResult().getUserName());
                            Log.i("scsfcdcdc", "run: "+ev_Review.getText().toString());
                            Log.i("scsfcdcdc", "run: "+Float.toString(rb_Main.getRating()));

                        }
                    },500);
                }
            }
        });

        dialogPlus.show();
    }
    public static boolean isValidEditTextInput(EditText editText) {
        String text = editText.getText().toString();
        if (text.isEmpty()) {
            editText.setError(editText.getContext().getString(R.string.field_required));
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public void addReview(String provider_id, String user_id,String comment,String rating){

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(AddReviewReq.class)
                .addReviews(provider_id,user_id,comment,rating)
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);

                        if (response.body() != null) {
                            if (response.isSuccessful()) {
                                JsonObject object = response.body().getAsJsonObject();
                                int status = object.get("status").getAsInt();
                                if (status == 1) {
                                    ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);
                                    AddReviewResponse authentication = new Gson().fromJson(object, AddReviewResponse.class);
                                     ResultItem item=new ResultItem(authentication.getResult());
                                     item.setProviderImage(provider_img);
                                     item.setProviderName(provider_Name);
                                     item.setUserName(model.getResult().getUserName());
                                     item.setUserImage(model.getResult().getImage());
                                     list.add(item);
                                     adapter.notifyDataSetChanged();
                                    Log.i("svfdvdcdc", "onResponse: "+authentication.toString());
                                } else {
                                    ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                    Log.i("dscbhs", "onResponse: " + authentication);
                                    Toast.makeText(getContext(), authentication.getResult(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);

                    }
                });
    }
}