package com.my.sadebuser.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.booking.ResultItem;
import com.my.sadebuser.databinding.ItemHomeBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.HomeHolder> {

    private final Context mContext;
//    private final Status status;
    public List<ResultItem> list;

    public RemoveBooking removeBooking;

    public BookingAdapter(Context context/*, List<ResultItem> list*//*, Status status*/) {
        this.mContext = context;
//        this.list = list;/
//        this.status = status;

    }

    public void setList(List<ResultItem> list) {
        this.list = list;
    }

    public void setRemoveBooking(RemoveBooking removeBooking) {
        this.removeBooking = removeBooking;
    }

    public BookingAdapter.HomeHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        ItemHomeBinding binding = ItemHomeBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new BookingAdapter.HomeHolder(binding);
    }

    public void onBindViewHolder(BookingAdapter.HomeHolder holder, int position) {

        String serviceName = list.get(position).getServiceDetails().getServiceName();
        holder.binding.tvName.setText(serviceName+"\n$"+list.get(position).getServiceDetails().getServicePrice());//.getProviderResponse().getUserName());

        holder.binding.tvStartTime.setText(String.format(mContext.getString(R.string.start), list.get(position).getStartTime()));
        holder.binding.tvEndTime.setText(String.format(mContext.getString(R.string.end), list.get(position).getEndTime()));
//        holder.binding.tvStartTime.setText("start :- " + list.get(position).getStartTime());
//        holder.binding.tvEndTime.setText("end :- " + list.get(position).getEndTime());
        holder.binding.tvReqType.setText(list.get(position).getProviderResponse().getBusiness_name()+"\nAddress: "+list.get(position).getProviderResponse().getBusiness_address());//getServiceName());
        holder.binding.tvNoOfBooking.setText(list.get(position).getDate());//getProviderResponse().getMobile());
        holder.binding.tvWant.setText(list.get(position).getProviderResponse().getEmail());
        holder.binding.tvEmail.setText(list.get(position).getProviderResponse().getMobile());//getDate());

        holder.binding.providerEmail.setText(list.get(position).getProviderUserResponse().getEmail());
        holder.binding.providerName.setText(list.get(position).getProviderUserResponse().getUserName());
        holder.binding.providerNo.setText(list.get(position).getProviderUserResponse().getMobile());

        Picasso.get().load(list.get(position).getProviderResponse().getImage()).placeholder(R.drawable.user_placeholder).into(holder.binding.roundedUserImage);
        Picasso.get().load(list.get(position).getProviderUserResponse().getImage()).placeholder(R.drawable.user_placeholder).into(holder.binding.ivProviderImage);

        if (list.get(position).getStatus().equals("Cancel")){
            holder.binding.tvStatus.setVisibility(View.VISIBLE);
            holder.binding.tvStatus.setText(R.string.cancel);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#ED0000"));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_red);
            holder.binding.llAction.setVisibility(View.GONE);

        }else if (list.get(position).getStatus().equals("Accept")){
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_storke_green);
            holder.binding.tvStatus.setVisibility(View.VISIBLE);
            holder.binding.tvStatus.setText(R.string.accepted);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#10B234"));
            holder.binding.llAction.setVisibility(View.GONE);
        }else if (list.get(position).getStatus().equals("Pending")){
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_gray);
            holder.binding.tvStatus.setVisibility(View.VISIBLE);
            holder.binding.tvStatus.setText(R.string.pending);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#929292"));
            holder.binding.llAction.setVisibility(View.GONE);
        }else if (list.get(position).getStatus().equals("Complete")){
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_blue);
            holder.binding.tvStatus.setVisibility(View.VISIBLE);
            holder.binding.tvStatus.setText(R.string.completed);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#0053B4"));
            holder.binding.llAction.setVisibility(View.GONE);
        }
        else{
            holder.binding.tvStatus.setVisibility(View.GONE);
            holder.binding.llAction.setVisibility(View.VISIBLE);
        }

        holder.binding.tvAccept.setOnClickListener(v -> {
//            status.acceptcontrol(position, () -> {
//                holder.binding.tvStatus.setText("Accepted");
//                holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_storke_green);
//                holder.binding.tvStatus.setTextColor(Color.parseColor("#10B234"));
//                holder.binding.tvStatus.setVisibility(View.VISIBLE);
//                holder.binding.tvDecline.setVisibility(View.GONE);
//                holder.binding.tvAccept.setVisibility(View.GONE);
//            });
        });
        holder.binding.tvDecline.setOnClickListener(v -> {
//            status.declinetcontrol(position, () -> {
//                holder.binding.tvStatus.setText("Cancel");
//                holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_red);
//                holder.binding.tvStatus.setTextColor(Color.parseColor("#ED0000"));
//                holder.binding.tvStatus.setVisibility(View.VISIBLE);
//                holder.binding.tvDecline.setVisibility(View.GONE);
//                holder.binding.tvAccept.setVisibility(View.GONE);
//            });
        });

        holder.binding.remove.setOnClickListener(v -> {
            removeBooking.removeClick(list.get(position).getId());
        });

    }

    public int getItemCount() {
        return this.list.size();
    }

//    public interface Status {
////        void acceptcontrol(int position, HomeFragment.DoneCallback callback);
////
////        void declinetcontrol(int position, HomeFragment.DoneCallback callback);
////
//    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        ItemHomeBinding binding;

        public HomeHolder(final ItemHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    public interface  RemoveBooking{
        void removeClick(String id);
    }

}
