package com.example.longyuan.websockettest.pojo.receive;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class SuggestedActions{

	@SerializedName("actions")
	private List<ActionsItem> actions;

	public void setActions(List<ActionsItem> actions){
		this.actions = actions;
	}

	public List<ActionsItem> getActions(){
		return actions;
	}

	@Override
 	public String toString(){
		return 
			"SuggestedActions{" + 
			"actions = '" + actions + '\'' + 
			"}";
		}
}