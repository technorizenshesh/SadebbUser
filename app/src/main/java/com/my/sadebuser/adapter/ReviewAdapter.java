package com.my.sadebuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.ResponseAuthentication;
import com.my.sadebuser.act.model.reviews.ResultItem;
import com.my.sadebuser.databinding.ReviewBinding;
import com.my.sadebuser.utils.SharePrefrenceConstraint;
import com.my.sadebuser.utils.SharedPrefsManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapter_View> {


    private final List<ResultItem> list;

    public ReviewAdapter(List<ResultItem> list) {
        this.list=list;
    }

    @Override
    public ReviewAdapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        ReviewBinding binding=ReviewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.review, parent, false);
        return new ReviewAdapter_View(binding);
    }

    @Override
    public void onBindViewHolder( ReviewAdapter_View holder, int position) {
        ResponseAuthentication model = SharedPrefsManager.getInstance().getObject(SharePrefrenceConstraint.user, ResponseAuthentication.class);

        holder.binding.tvReview.setText(list.get(position).getComment());
        holder.binding.rbRating.setRating(Float.parseFloat(list.get(position).getRating()));
        holder.binding.tvName.setText(list.get(position).getUserName());
        Picasso.get().load(model.getResult().getImage()).placeholder(R.drawable.user_placeholder).into(holder.binding.ivUserImage);
     }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReviewAdapter_View extends RecyclerView.ViewHolder {
        private ReviewBinding binding;
        public ReviewAdapter_View(ReviewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }

}
