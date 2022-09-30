package com.my.sadebuser.act.model.addreview;

import com.google.gson.annotations.SerializedName;

public class Result{

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

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"date_time = '" + dateTime + '\'' + 
			",user_id = '" + userId + '\'' + 
			",rating = '" + rating + '\'' + 
			",provider_id = '" + providerId + '\'' + 
			",comment = '" + comment + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}