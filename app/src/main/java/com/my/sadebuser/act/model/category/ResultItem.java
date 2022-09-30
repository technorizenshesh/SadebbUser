package com.my.sadebuser.act.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultItem{

	@SerializedName("id")
	@Expose
	public String id;
	@SerializedName("business_id")
	@Expose
	public String businessId;
	@SerializedName("category_name")
	@Expose
	public String categoryName;
	@SerializedName("category_name_sp")
	@Expose
	public String categoryNameSp;
	@SerializedName("image")
	@Expose
	public String image;
	@SerializedName("status")
	@Expose
	public String status;
	@SerializedName("date_time")
	@Expose
	public String dateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryNameSp() {
		return categoryNameSp;
	}

	public void setCategoryNameSp(String categoryNameSp) {
		this.categoryNameSp = categoryNameSp;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
	@Override
 	public String toString(){
		return 
			"ResultItem{" + 
			"image = '" +  + '\'' +
			",category_name = '" + categoryName + '\'' + 
			",date_time = '" + dateTime + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}