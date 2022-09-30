package com.my.sadebuser.adapter;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    List<String> images;

    public MainSliderAdapter(List<String> images) {
        this.images = images;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder) {

        imageSlideViewHolder.bindImageSlide(images.get(position));

    }
}
