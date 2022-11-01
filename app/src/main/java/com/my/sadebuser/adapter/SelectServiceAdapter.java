package com.my.sadebuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.servicelist.ResultItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SelectServiceAdapter extends RecyclerView.Adapter<SelectServiceAdapter.SelectServiceAdapter_View> {

    public int selectedposition = 0;
    private final List<ResultItem> list;
    private final Clickcallback clickcallback;
    public SelectServiceAdapter(List<ResultItem> list, Clickcallback clickcallback) {
        this.list = list;
        this.clickcallback = clickcallback;
    }

    @Override
    public SelectServiceAdapter_View onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_service, parent, false);
        return new SelectServiceAdapter_View(view);
    }
    @Override
    public void onBindViewHolder(SelectServiceAdapter.SelectServiceAdapter_View holder, int position) {
        holder.tv_Service_Name.setText(list.get(position).getServiceName());
        holder.service_price.setText("$" + list.get(position).getServicePrice() );
        Picasso.get().load(list.get(position).getServiceImage()).placeholder(R.drawable.user_placeholder).into(holder.image);

        holder.check.setOnClickListener(v -> {
            selectedposition = position;
            notifyDataSetChanged();
        });
        if (selectedposition == position) {
            clickcallback.click(position);
            holder.check.setChecked(true);
        } else {
            holder.check.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface Clickcallback {
        void click(int position);
    }

    public class SelectServiceAdapter_View extends RecyclerView.ViewHolder {
        private final TextView service_price;
        private final TextView tv_Service_Name;
        private final CheckBox check;
        private final ImageView image;

        public SelectServiceAdapter_View(View itemView) {
            super(itemView);
            check = itemView.findViewById(R.id.check);
            service_price = itemView.findViewById(R.id.service_price);
            image = itemView.findViewById(R.id.image);
            tv_Service_Name = itemView.findViewById(R.id.tv_Service_Name);
        }
    }
}
