package com.my.sadebuser.act.model.booking;

import com.google.gson.annotations.SerializedName;

public class ProviderResponse {

    @SerializedName("image")
    private String image;

    @SerializedName("gender")
    private String gender;

    @SerializedName("date_time")
    private String dateTime;

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
    @Override
    public String toString() {
        return "ProviderUserResponse{" +
                "image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", booking_status='" + booking_status + '\'' +
                ", business_name='" + business_name + '\'' +
                ", business_address='" + business_address + '\'' +
                ", business_cell_phone='" + business_cell_phone + '\'' +
                ", business_landline='" + business_landline + '\'' +
                ", offer_home_delivery='" + offer_home_delivery + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    @SerializedName("user_id")
    private String userId;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;


    @SerializedName("booking_status")
    private String booking_status;

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
}
