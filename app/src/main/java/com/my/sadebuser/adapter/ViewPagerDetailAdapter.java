package com.my.sadebuser.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


import com.my.sadebuser.R;
import com.my.sadebuser.model.SuccessResGetBanner;
import com.my.sadebuser.utils.RoundedCornersTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPagerDetailAdapter extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    ArrayList<SuccessResGetBanner.Result> images;

    // Layout Inflater
    LayoutInflater mLayoutInflater;

    // Viewpager Constructor
    public ViewPagerDetailAdapter(Context context) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setImages(ArrayList<SuccessResGetBanner.Result> images) {
        this.images = images;
    }

    @Override
    public int getCount() {
        // return the number of images
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.adapter_view_pager, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewMain);

        // setting the image in the imageView
//        imageView.setImageResource(images[position]);
//        Glide.with(context).load(images.get(position)).into(imageView);
        Picasso.get().load(images.get(position).getImage())/*.transform(new RoundedCornersTransform())*/.into(imageView);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        imageView.setOnClickListener(v -> {
//            Intent fullImageIntent = new Intent(context, FullImageViewActivity.class);
//// uriString is an ArrayList<String> of URI of all images
//            fullImageIntent.putExtra(FullImageViewActivity.URI_LIST_DATA,  images);
//// pos is the position of image will be showned when open
//            fullImageIntent.putExtra(FullImageViewActivity.IMAGE_SCREEN_CURRENT_POS, position);
//            context.startActivity(fullImageIntent);
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}