package com.my.sadebuser.act.model.sevicelistbycat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultItem {

    @SerializedName("image5")
    private String image5;

    @SerializedName("service_time")
    private String serviceTime;

    @SerializedName("image6")
    private String image6;

    @SerializedName("image3")
    private String image3;

    @SerializedName("image4")
    private String image4;

    @SerializedName("service_price")
    private String servicePrice;

    @SerializedName("customization")
    private String customization;

    @SerializedName("service_name")
    private String serviceName;

    @SerializedName("image7")
    private String image7;

    @SerializedName("provider_user_id")
    private String providerUserId;

    @SerializedName("description")
    private String description;

    @SerializedName("lon")
    private String lon;

    @SerializedName("image1")
    private String image1;

    @SerializedName("image2")
    private String image2;

    @SerializedName("category_id")
    private String categoryId;

    @SerializedName("date_time")
    private String dateTime;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("provider_name")
    private String provider_name;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("business_name")
    private String business_name;

    @SerializedName("business_address")
    private String business_address;

    @SerializedName("business_cell_phone")
    private String business_cell_phone;

    @SerializedName("business_landline")
    private String business_landline;


    @SerializedName("offer_home_delivery")
    private String offer_home_delivery;

    @SerializedName("provider_image")
    private String provider_image;

    @SerializedName("service_offer")
    private String serviceOffer;

    @SerializedName("estimate_time")
    private String estimateTime;

    @SerializedName("id")
    private String id;

    @SerializedName("lat")
    private String lat;

    @SerializedName("service_like_unlike")
    private String favorite;
    @SerializedName("fav_provider")
    @Expose
    private String favProvider;

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getImage5() {
        return image5;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public String getFavProvider() {
        return favProvider;
    }

    public void setFavProvider(String favProvider) {
        this.favProvider = favProvider;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public String getImage6() {
        return image6;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage4() {
        return image4;
    }

    public void setServicePrice(String servicePrice) {
        this.servicePrice = servicePrice;
    }

    public String getServicePrice() {
        return servicePrice;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }

    public String getCustomization() {
        return customization;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setImage7(String image7) {
        this.image7 = image7;
    }

    public String getImage7() {
        return image7;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLon() {
        return lon;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage2() {
        return image2;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getProvider_image() {
        return provider_image;
    }

    public void setProvider_image(String provider_image) {
        this.provider_image = provider_image;
    }

    public void setServiceOffer(String serviceOffer) {
        this.serviceOffer = serviceOffer;
    }

    public String getServiceOffer() {
        return serviceOffer;
    }

    public void setEstimateTime(String estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getEstimateTime() {
        return estimateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
    }

    public String getBusiness_cell_phone() {
        return business_cell_phone;
    }

    public void setBusiness_cell_phone(String business_cell_phone) {
        this.business_cell_phone = business_cell_phone;
    }

    public String getBusiness_landline() {
        return business_landline;
    }

    public void setBusiness_landline(String business_landline) {
        this.business_landline = business_landline;
    }

    public String getOffer_home_delivery() {
        return offer_home_delivery;
    }

    public void setOffer_home_delivery(String offer_home_delivery) {
        this.offer_home_delivery = offer_home_delivery;
    }

    @Override
    public String toString() {
        return
                "ResultItem{" +
                        "image5 = '" + image5 + '\'' +
                        ",service_time = '" + serviceTime + '\'' +
                        ",image6 = '" + image6 + '\'' +
                        ",image3 = '" + image3 + '\'' +
                        ",image4 = '" + image4 + '\'' +
                        ",service_price = '" + servicePrice + '\'' +
                        ",customization = '" + customization + '\'' +
                        ",service_name = '" + serviceName + '\'' +
                        ",image7 = '" + image7 + '\'' +
                        ",provider_user_id = '" + providerUserId + '\'' +
                        ",description = '" + description + '\'' +
                        ",lon = '" + lon + '\'' +
                        ",image1 = '" + image1 + '\'' +
                        ",image2 = '" + image2 + '\'' +
                        ",category_id = '" + categoryId + '\'' +
                        ",date_time = '" + dateTime + '\'' +
                        ",user_id = '" + userId + '\'' +
                        ",provider_name = '" + provider_name + '\'' +
                        ",provider_image = '" + provider_image + '\'' +
                        ",service_offer = '" + serviceOffer + '\'' +
                        ",estimate_time = '" + estimateTime + '\'' +
                        ",id = '" + id + '\'' +
                        ",lat = '" + lat + '\'' +
                        ",mobile = '" + mobile + '\'' +
                        ",business_name = '" + business_name + '\'' +
                        ",favorite = '" + favorite + '\'' +
                        ",business_address = '" + business_address + '\'' +
                        ",business_cell_phone = '" + business_cell_phone + '\'' +
                        ",business_landline = '" + business_landline + '\'' +
                        ",offer_home_delivery = '" + offer_home_delivery + '\'' +
                        "}";
    }
}