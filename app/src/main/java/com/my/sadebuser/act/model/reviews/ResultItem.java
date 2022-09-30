package com.my.sadebuser.act.model.reviews;

import com.google.gson.annotations.SerializedName;
import com.my.sadebuser.act.model.addreview.AddReviewResponse;
import com.my.sadebuser.act.model.addreview.Result;

public class ResultItem{

	@SerializedName("date_time")
	private String dateTime;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("rating")
	private String rating;

	@SerializedName("provider_id")
	private String providerId;

	@SerializedName("comment")
	private String comment;

	@SerializedName("id")
	private String id;



	@SerializedName("provider_name")
	private String providerName;

	@SerializedName("provider_image")
	private String providerImage;

	@SerializedName("user_image")
	private String userImage;

	@SerializedName("user_name")
	private String userName;

	public ResultItem(Result result) {
		this.dateTime = result.getDateTime();
		this.userId = result.getUserId();
		this.rating = result.getRating();
		this.providerId = result.getProviderId();
		this.comment = result.getComment();
		this.id = result.getId();
	}

	public void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}

	public String getDateTime(){
		return dateTime;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserImage(String userImage){
		this.userImage = userImage;
	}

	public String getUserImage(){
		return userImage;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setRating(String rating){
		this.rating = rating;
	}

	public String getRating(){
		return rating;
	}

	public void setProviderId(String providerId){
		this.providerId = providerId;
	}

	public String getProviderId(){
		return providerId;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return comment;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setProviderName(String providerName){
		this.providerName = providerName;
	}

	public String getProviderName(){
		return providerName;
	}

	public void setProviderImage(String providerImage){
		this.providerImage = providerImage;
	}

	public String getProviderImage(){
		return providerImage;
	}

	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"date_time = '" + dateTime + '\'' + 
			",user_id = '" + userId + '\'' + 
			",user_image = '" + userImage + '\'' + 
			",user_name = '" + userName + '\'' + 
			",rating = '" + rating + '\'' + 
			",provider_id = '" + providerId + '\'' + 
			",comment = '" + comment + '\'' + 
			",id = '" + id + '\'' + 
			",provider_name = '" + providerName + '\'' + 
			",provider_image = '" + providerImage + '\'' + 
			"}";
		}
}