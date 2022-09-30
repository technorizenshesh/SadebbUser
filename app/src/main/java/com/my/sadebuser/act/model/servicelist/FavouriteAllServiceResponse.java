package com.my.sadebuser.act.model.servicelist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavouriteAllServiceResponse {

	@SerializedName("result")
	private List<ResultItem> result;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private String status;

	public void setResult(List<ResultItem> result){
		this.result = result;
	}

	public List<ResultItem> getResult(){
		return result;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

//	@Override
// 	public String toString(){
//		return
//			"AllServiceResponse{" +
//			"result = '" + result + '\'' +
//			",message = '" + message + '\'' +
//			",status = '" + status + '\'' +
//			"}";
//		}

	public class ResultItem {
		@SerializedName("service_details")
		private ServiceDetail service_details;

		@SerializedName("id")
		private String id;
		@SerializedName("user_name")
		private String user_name;
		@SerializedName("user_image")
		private String user_image;

		public ServiceDetail getService_details() {
			return service_details;
		}

		public void setService_details(ServiceDetail service_details) {
			this.service_details = service_details;
		}

		public String getId() {
			return id;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getUser_image() {
			return user_image;
		}

		public void setUser_image(String user_image) {
			this.user_image = user_image;
		}

		public void setId(String id) {
			this.id = id;
		}

		public class ServiceDetail{
			@SerializedName("open_time_wednesday")
			private String openTimeWednesday;

			@SerializedName("customization")
			private String customization;

			@SerializedName("close_time_friday")
			private String closeTimeFriday;

			@SerializedName("description")
			private String description;

			@SerializedName("open_time_sunday")
			private String openTimeSunday;

			@SerializedName("weekly_close")
			private String weeklyClose;

			@SerializedName("close_time_sunday")
			private String closeTimeSunday;

			@SerializedName("category_id")
			private String categoryId;

			@SerializedName("date_time")
			private String dateTime;

			@SerializedName("service_offer")
			private String serviceOffer;

			@SerializedName("close_time_saturday")
			private String closeTimeSaturday;

			@SerializedName("id")
			private String id;

			@SerializedName("open_time_saturday")
			private String openTimeSaturday;

			@SerializedName("image5")
			private String image5;

			@SerializedName("close_time_wednesday")
			private String closeTimeWednesday;

			@SerializedName("service_time")
			private String serviceTime;

			@SerializedName("image6")
			private String image6;

			@SerializedName("close_time_tuesday")
			private String closeTimeTuesday;

			@SerializedName("image3")
			private String image3;

			@SerializedName("image4")
			private String image4;

			@SerializedName("service_price")
			private String servicePrice;

			@SerializedName("open_time_tuesday")
			private String openTimeTuesday;

			@SerializedName("service_name")
			private String serviceName;

			@SerializedName("image7")
			private String image7;

			@SerializedName("image1")
			private String image1;

			@SerializedName("close_time_monday")
			private String closeTimeMonday;

			@SerializedName("image2")
			private String image2;

			@SerializedName("close_time_thursday")
			private String closeTimeThursday;

			@SerializedName("user_id")
			private String userId;

			@SerializedName("open_time_thursday")
			private String openTimeThursday;

			@SerializedName("open_time_friday")
			private String openTimeFriday;

			@SerializedName("open_time_monday")
			private String openTimeMonday;


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


			public String getOpenTimeWednesday() {
				return openTimeWednesday;
			}

			public void setOpenTimeWednesday(String openTimeWednesday) {
				this.openTimeWednesday = openTimeWednesday;
			}

			public String getCustomization() {
				return customization;
			}

			public void setCustomization(String customization) {
				this.customization = customization;
			}

			public String getCloseTimeFriday() {
				return closeTimeFriday;
			}

			public void setCloseTimeFriday(String closeTimeFriday) {
				this.closeTimeFriday = closeTimeFriday;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getOpenTimeSunday() {
				return openTimeSunday;
			}

			public void setOpenTimeSunday(String openTimeSunday) {
				this.openTimeSunday = openTimeSunday;
			}

			public String getWeeklyClose() {
				return weeklyClose;
			}

			public void setWeeklyClose(String weeklyClose) {
				this.weeklyClose = weeklyClose;
			}

			public String getCloseTimeSunday() {
				return closeTimeSunday;
			}

			public void setCloseTimeSunday(String closeTimeSunday) {
				this.closeTimeSunday = closeTimeSunday;
			}

			public String getCategoryId() {
				return categoryId;
			}

			public void setCategoryId(String categoryId) {
				this.categoryId = categoryId;
			}

			public String getDateTime() {
				return dateTime;
			}

			public void setDateTime(String dateTime) {
				this.dateTime = dateTime;
			}

			public String getServiceOffer() {
				return serviceOffer;
			}

			public void setServiceOffer(String serviceOffer) {
				this.serviceOffer = serviceOffer;
			}

			public String getCloseTimeSaturday() {
				return closeTimeSaturday;
			}

			public void setCloseTimeSaturday(String closeTimeSaturday) {
				this.closeTimeSaturday = closeTimeSaturday;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public String getOpenTimeSaturday() {
				return openTimeSaturday;
			}

			public void setOpenTimeSaturday(String openTimeSaturday) {
				this.openTimeSaturday = openTimeSaturday;
			}

			public String getImage5() {
				return image5;
			}

			public void setImage5(String image5) {
				this.image5 = image5;
			}

			public String getCloseTimeWednesday() {
				return closeTimeWednesday;
			}

			public void setCloseTimeWednesday(String closeTimeWednesday) {
				this.closeTimeWednesday = closeTimeWednesday;
			}

			public String getServiceTime() {
				return serviceTime;
			}

			public void setServiceTime(String serviceTime) {
				this.serviceTime = serviceTime;
			}

			public String getImage6() {
				return image6;
			}

			public void setImage6(String image6) {
				this.image6 = image6;
			}

			public String getCloseTimeTuesday() {
				return closeTimeTuesday;
			}

			public void setCloseTimeTuesday(String closeTimeTuesday) {
				this.closeTimeTuesday = closeTimeTuesday;
			}

			public String getImage3() {
				return image3;
			}

			public void setImage3(String image3) {
				this.image3 = image3;
			}

			public String getImage4() {
				return image4;
			}

			public void setImage4(String image4) {
				this.image4 = image4;
			}

			public String getServicePrice() {
				return servicePrice;
			}

			public void setServicePrice(String servicePrice) {
				this.servicePrice = servicePrice;
			}

			public String getOpenTimeTuesday() {
				return openTimeTuesday;
			}

			public void setOpenTimeTuesday(String openTimeTuesday) {
				this.openTimeTuesday = openTimeTuesday;
			}

			public String getServiceName() {
				return serviceName;
			}

			public void setServiceName(String serviceName) {
				this.serviceName = serviceName;
			}

			public String getImage7() {
				return image7;
			}

			public void setImage7(String image7) {
				this.image7 = image7;
			}

			public String getImage1() {
				return image1;
			}

			public void setImage1(String image1) {
				this.image1 = image1;
			}

			public String getCloseTimeMonday() {
				return closeTimeMonday;
			}

			public void setCloseTimeMonday(String closeTimeMonday) {
				this.closeTimeMonday = closeTimeMonday;
			}

			public String getImage2() {
				return image2;
			}

			public void setImage2(String image2) {
				this.image2 = image2;
			}

			public String getCloseTimeThursday() {
				return closeTimeThursday;
			}

			public void setCloseTimeThursday(String closeTimeThursday) {
				this.closeTimeThursday = closeTimeThursday;
			}

			public String getUserId() {
				return userId;
			}

			public void setUserId(String userId) {
				this.userId = userId;
			}

			public String getOpenTimeThursday() {
				return openTimeThursday;
			}

			public void setOpenTimeThursday(String openTimeThursday) {
				this.openTimeThursday = openTimeThursday;
			}

			public String getOpenTimeFriday() {
				return openTimeFriday;
			}

			public void setOpenTimeFriday(String openTimeFriday) {
				this.openTimeFriday = openTimeFriday;
			}

			public String getOpenTimeMonday() {
				return openTimeMonday;
			}

			public void setOpenTimeMonday(String openTimeMonday) {
				this.openTimeMonday = openTimeMonday;
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
		}
	}
	}