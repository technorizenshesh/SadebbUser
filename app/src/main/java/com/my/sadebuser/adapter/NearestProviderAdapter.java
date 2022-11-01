package com.my.sadebuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.nearbymepro.ResultItem;
import com.my.sadebuser.databinding.ItemSalooonBinding;
import com.my.sadebuser.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NearestProviderAdapter extends RecyclerView.Adapter<NearestProviderAdapter.NearestProviderAdapter_View> {

    private List<ResultItem> list;
    private  SelectedPosition   selectedPosition;

    private Context mContext;

    public NearestProviderAdapter(Context mContext,List<ResultItem> list,SelectedPosition selectedPosition) {
        this.list=list;
        this.selectedPosition=selectedPosition;
        this.mContext = mContext;
    }

    @Override
    public NearestProviderAdapter_View onCreateViewHolder( ViewGroup parent, int viewType) {
        ItemSalooonBinding binding= ItemSalooonBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
         return new NearestProviderAdapter_View(binding);
    }

    @Override
    public void onBindViewHolder( NearestProviderAdapter.NearestProviderAdapter_View holder, int position) {
        holder.binding.tvName.setText(list.get(position).getBusiness_name());//UserName());
        holder.binding.tvDetail.setText(mContext.getString(R.string.address_)+list.get(position).getBusiness_address());//Description());
        holder.binding.tvNumber.setText("Cellf: "+list.get(position).getMobile()+"\n"+"Telf: "+list.get(position).getBusiness_landline());

//        Picasso.get().load(list.get(position).getImage()).placeholder(R.drawable.user_placeholder).into(holder.binding.img1);
        try{
            Picasso.get().load(list.get(position).getBusiness_profile_image()).placeholder(R.drawable.user_placeholder).into(holder.binding.img1);
        }catch (Exception e){
            e.printStackTrace();
        }

        holder.binding.txtBook.setOnClickListener(v -> {
            selectedPosition.curruntitem(position);
        });

        holder.binding.favorite.setVisibility(View.GONE);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class NearestProviderAdapter_View extends RecyclerView.ViewHolder {
        private ItemSalooonBinding binding;
        public NearestProviderAdapter_View(ItemSalooonBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public interface SelectedPosition{
        void curruntitem(int position);
    }

}
