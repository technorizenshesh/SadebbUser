package com.my.sadebuser.act.model.booking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingAppointmentResponse{

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

	@Override
 	public String toString(){
		return 
			"BookingAppointmentResponse{" + 
			"result = '" + result + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}