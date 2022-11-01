package com.my.sadebuser.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.my.sadebuser.R;
import com.my.sadebuser.act.model.serviceprovider.ResultItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class HomeSaloonRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private   List<com.my.sadebuser.act.model.sevicelistbycat.ResultItem> serviceproviderlist;
    private   ItemPos itemPos;
    private Context mContext;
    private List<ResultItem> list;
    private OnItemClickListener mItemClickListener;
    private Fragment fragment;
    boolean isLike=true;

    private String from="";

    public HomeSaloonRecyclerViewAdapter(  List<ResultItem> list ,ItemPos itemPos) {
         this.list = list;
        this.itemPos=itemPos;
    }

    public HomeSaloonRecyclerViewAdapter(String from,Context context, List<ResultItem> list, ItemPos itemPos) {
        this.mContext = context;
        this.list = list;
        this.from = from;
        this.itemPos=itemPos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_salooon, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you2 can fill your row view
        if (holder instanceof ViewHolder) {
            final ResultItem model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            ((ViewHolder) holder).tv_Name.setText(list.get(position).getBusiness_name());//UserName());
            ((ViewHolder) holder).tv_Number.setText("Cellf: "+list.get(position).getMobile()+"\n"+"Telf: "+list.get(position).getBusiness_landline());
            ((ViewHolder) holder).tv_address.setText(list.get(position).getAddress());

            if(list.get(position).getOffer_home_delivery().equals("true")){
                ((ViewHolder) holder).tv_home_service.setVisibility(View.VISIBLE);
            }else {
                ((ViewHolder) holder).tv_home_service.setVisibility(View.GONE);
            }
            if(list.get(position).getAddress().equals("")){
                ((ViewHolder) holder).tv_address.setVisibility(View.GONE);
            }else {
                ((ViewHolder) holder).tv_address.setVisibility(View.VISIBLE);
            }

            try {
                if (list.get(position).getRating().equals("")||list.get(position).getRating().equals("0")||list.get(position).getRating().equals("0.00" +
                        "")) {
                    ((ViewHolder) holder).rb_Rating.setRating(0);
                }else {
                    ((ViewHolder) holder).rb_Rating.setRating(Float.parseFloat(list.get(position).getRating()));
                }
            }catch (Exception exception){
                exception.printStackTrace();
                ((ViewHolder) holder).rb_Rating.setRating(0);
            }

            if (list.get(position).getFavProvider().equals("Yes")) {
                ((ViewHolder) holder).favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
            } else {
                ((ViewHolder) holder).favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            }

            if(from.equalsIgnoreCase("home") || from.equalsIgnoreCase("fav"))
            {
                ((ViewHolder) holder).favorite.setVisibility(View.VISIBLE);
            }
            else
            {
                ((ViewHolder) holder).favorite.setVisibility(View.GONE);
            }

//            ((ViewHolder) holder).favorite.setVisibility(View.GONE);

            ((ViewHolder) holder).favorite.setOnClickListener(v ->
                    {

                        if(list.get(position).getFavProvider().equalsIgnoreCase("No"))
                        {
                            list.get(position).setFavProvider("Yes");
                        } else
                        {
                            list.get(position).setFavProvider("No");
                        }
                        itemPos.addFavourite(position);
                        notifyDataSetChanged();

                    }
                    );

//            if (list.get(position).getDescription().equals("")){
//                ((ViewHolder) holder).tv_Detail.setText("Lorem ipsum is simply dummy text of the printing and typesetting industry. Lorem ipsum has been the industry's standard");
//            }
//            else{
                ((ViewHolder) holder).tv_Detail.setText(mContext.getResources().getString(R.string.address_)+list.get(position).getBusiness_address());//getDescription());
//            }
            try {
            Picasso.get().load(list.get(position).getBusiness_profile_image()).placeholder(R.drawable.user_placeholder).into(((ViewHolder) holder).img1);
            }catch (Exception exception){
                exception.printStackTrace();
            }
            ((ViewHolder) holder).txtBook.setOnClickListener(v -> {
                 itemPos.selectedpos(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private ResultItem getItem(int position) {
        return list.get(position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position, ResultItem model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

         private TextView txtBook,tv_Detail,tv_Name,tv_Number,tv_address,tv_home_service;
         private ImageView img1,favorite;

        private RatingBar rb_Rating;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.tv_address=itemView.findViewById(R.id.tv_address);
            this.tv_home_service=itemView.findViewById(R.id.tv_home_service);

          this.tv_Number=itemView.findViewById(R.id.tv_Number);
          this.txtBook=itemView.findViewById(R.id.txtBook);
          this.tv_Detail=itemView.findViewById(R.id.tv_Detail);
          this.tv_Name=itemView.findViewById(R.id.tv_Name);
          this.img1=itemView.findViewById(R.id.img1);
          this.rb_Rating=itemView.findViewById(R.id.rb_Rating);

          this.favorite=itemView.findViewById(R.id.favorite);
         }
    }

    public interface ItemPos{
        void selectedpos(int pos);
        void addFavourite(int pos);
    }

}

