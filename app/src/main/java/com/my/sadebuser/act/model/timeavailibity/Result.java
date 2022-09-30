package com.my.sadebuser.act.model.timeavailibity;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Result{

	@SerializedName("time")
	private List<TimeItem> time;

	public void setTime(List<TimeItem> time){
		this.time = time;
	}

	public List<TimeItem> getTime(){
		return time;
	}

	@Override
 	public String toString(){
		return 
			"Result{" + 
			"time = '" + time + '\'' + 
			"}";
		}
}