package com.my.sadebuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.servicedetail.ServiceUserItem;
import com.my.sadebuser.databinding.ProvideralluserBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProviderAllUserAdapter extends RecyclerView.Adapter<ProviderAllUserAdapter.ProviderAllUserAdapter_View> {
    private final List<ServiceUserItem> list;
    private final SelectedItem selectedItem;
    private int selectedPosition=-1;

    public ProviderAllUserAdapter(List<ServiceUserItem> list, SelectedItem selectedItem) {
        this.list=list;
        this.selectedItem=selectedItem;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public ProviderAllUserAdapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProvideralluserBinding binding = ProvideralluserBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProviderAllUserAdapter_View(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderAllUserAdapter_View holder, int position) {
        holder.binding.tvEmail.setText(list.get(position).getEmail());
        holder.binding.tvName.setText(list.get(position).getUserName());
        holder.binding.tvNo.setText(list.get(position).getMobile());
        Picasso.get().load(list.get(position).getImage()).placeholder(R.drawable.user_placeholder).into(holder.binding.ciImg);

        holder.binding.cvCheck.setOnClickListener(v -> {
            selectedPosition=position;
            notifyDataSetChanged();
        });


        if (selectedPosition==position){
            holder.binding.cvCheck.setChecked(true);
            selectedItem.selectedItem(position);
        }else {
            holder.binding.cvCheck.setChecked(false);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProviderAllUserAdapter_View extends RecyclerView.ViewHolder {

        private final ProvideralluserBinding binding;

        public ProviderAllUserAdapter_View(@NonNull ProvideralluserBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }

    public interface SelectedItem{
        void selectedItem(int position);
    }
}
