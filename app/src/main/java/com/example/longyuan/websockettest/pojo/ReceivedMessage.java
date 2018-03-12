package com.example.longyuan.websockettest.pojo;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ReceivedMessage{

	@SerializedName("activities")
	private List<ActivitiesItem> activities;

	public void setActivities(List<ActivitiesItem> activities){
		this.activities = activities;
	}

	public List<ActivitiesItem> getActivities(){
		return activities;
	}

	@Override
 	public String toString(){
		return 
			"ReceivedMessage{" + 
			"activities = '" + activities + '\'' + 
			"}";
		}
}