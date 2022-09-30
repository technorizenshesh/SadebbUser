package com.my.sadebuser.act.model.timeavailibity;

import com.google.gson.annotations.SerializedName;

public class TimeItem{

	@SerializedName("start")
	private String start;

	@SerializedName("end")
	private String end;

	@SerializedName("status")
	private String status;

	public void setStart(String start){
		this.start = start;
	}

	public String getStart(){
		return start;
	}

	public void setEnd(String end){
		this.end = end;
	}

	public String getEnd(){
		return end;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"TimeItem{" + 
			"start = '" + start + '\'' + 
			",end = '" + end + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}