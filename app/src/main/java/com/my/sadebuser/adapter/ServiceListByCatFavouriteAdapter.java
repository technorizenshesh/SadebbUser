package com.my.sadebuser.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.my.sadebuser.R;
import com.my.sadebuser.act.model.servicelist.FavouriteAllServiceResponse;
import com.my.sadebuser.act.model.sevicelistbycat.ResultItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ServiceListByCatFavouriteAdapter extends RecyclerView.Adapter<ServiceListByCatFavouriteAdapter.ServiceListByCatAdapter_View> {

    private final List<FavouriteAllServiceResponse.ResultItem> serviceproviderlist;
    private final ServiceListByCatFavouriteAdapter.ItemPos itemPos;

    FavoriteClick favoriteClick;

    public ServiceListByCatFavouriteAdapter(List<FavouriteAllServiceResponse.ResultItem> serviceproviderlist, ServiceListByCatFavouriteAdapter.ItemPos itemPos) {
         this.serviceproviderlist = serviceproviderlist;
         this.itemPos=itemPos;
    }

    public void setFavoriteClick(FavoriteClick favoriteClick) {
        this.favoriteClick = favoriteClick;
    }

    @NonNull
    @Override
    public ServiceListByCatAdapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_salooon, parent, false);
        return new ServiceListByCatAdapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListByCatAdapter_View holder, int position) {
        holder.tv_Detail.setText("Address: "+serviceproviderlist.get(position).getService_details().getBusiness_address());//Description());
        holder.tv_Name.setText(serviceproviderlist.get(position).getService_details().getServiceName());
        Picasso.get().load(serviceproviderlist.get(position).getService_details().getImage1()).placeholder(R.drawable.user_placeholder).into(holder.img1);
        holder.tv_Number.setText(serviceproviderlist.get(position).getService_details().getBusiness_name()+"\nCellf: "+serviceproviderlist.get(position).getService_details().getMobile()
                +"\nTelf: "+serviceproviderlist.get(position).getService_details().getBusiness_landline());

//        holder.tv_Detail.setText(serviceproviderlist.get(position).getService_details().getDescription());
//        holder.tv_Name.setText(serviceproviderlist.get(position).getService_details().getServiceName());
//        Picasso.get().load(serviceproviderlist.get(position).getService_details().getImage1()).placeholder(R.drawable.user_placeholder).into(holder.img1);

        holder.txtBook.setOnClickListener(v -> {
            itemPos.selectedpos(position);
        });
        holder.rb_Rating.setVisibility(View.GONE);
//        try {
//            if (serviceproviderlist.get(position).getService_details().getFavorite().equals("like")) {
//                holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
//            } else {
//                holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        holder.favorite.setOnClickListener(v -> {
//            favoriteClick.onClick(serviceproviderlist.get(position));
//        });
        holder.favorite.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return serviceproviderlist.size();
    }

    public class ServiceListByCatAdapter_View extends RecyclerView.ViewHolder {
        private TextView txtBook,tv_Detail,tv_Name,tv_Number;
        private ImageView img1,favorite;
        private RatingBar rb_Rating;


        public ServiceListByCatAdapter_View(@NonNull View itemView) {
            super(itemView);
            this.txtBook=itemView.findViewById(R.id.txtBook);
            this.tv_Detail=itemView.findViewById(R.id.tv_Detail);
            this.tv_Name=itemView.findViewById(R.id.tv_Name);
            this.tv_Number=itemView.findViewById(R.id.tv_Number);

            this.img1=itemView.findViewById(R.id.img1);
            this.favorite=itemView.findViewById(R.id.favorite);
            this.rb_Rating=itemView.findViewById(R.id.rb_Rating);
        }
    }

    public interface ItemPos{
        void selectedpos(int pos);
    }

    public interface FavoriteClick{
        void onClick(ResultItem resultItem);
    }


}
