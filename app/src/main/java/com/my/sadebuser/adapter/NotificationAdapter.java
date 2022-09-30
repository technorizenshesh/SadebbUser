package com.my.sadebuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.my.sadebuser.R;
import com.my.sadebuser.act.model.notification.ResultItem;
import com.my.sadebuser.databinding.NotificationBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationAdapter_View> {

    private List<ResultItem> results;
    Context context;
    public NotificationAdapter(/*List<ResultItem> results,*/Context context) {
//        this.results=results;
        this.context=context;
    }

    public void setResults(List<ResultItem> results) {
        this.results = results;
    }

    @NonNull
    @NotNull
    @Override
    public NotificationAdapter_View onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        @NonNull NotificationBinding binding = NotificationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NotificationAdapter_View(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NotificationAdapter_View holder, int position) {

        holder.binding.tvDescription.setText(results.get(position).getComment());
        String time =results.get(position).getDateTime();
        holder.binding.tvTime.setText(time.substring(10));

        holder.binding.tvReadMore.setText(String.format(context.getString(R.string.provider_name), results.get(position).getProviderName()));
//        holder.binding.tvReadMore.setText("Provider Name:- "+results.get(position).getProviderName());
        Picasso.get().load(results.get(position).getProvider_image()).placeholder(R.drawable.user_placeholder).into(holder.binding.ivImage);
     }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class NotificationAdapter_View extends RecyclerView.ViewHolder {
       private NotificationBinding binding;
        public NotificationAdapter_View(@NonNull @NotNull NotificationBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
         }
    }
}
