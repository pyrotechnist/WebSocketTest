package com.example.longyuan.websockettest.pojo;

import javax.annotation.Generated;

import com.example.longyuan.websockettest.pojo.receive.SuggestedActions;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ActivitiesItem{

	@SerializedName("serviceUrl")
	private String serviceUrl;

	@SerializedName("from")
	private From from;

	@SerializedName("id")
	private String id;

	@SerializedName("text")
	private String text;

	@SerializedName("type")
	private String type;

	@SerializedName("channelId")
	private String channelId;

	@SerializedName("conversation")
	private Conversation conversation;

	@SerializedName("timestamp")
	private String timestamp;

	@SerializedName("suggestedActions")
	private SuggestedActions suggestedActions;

	public SuggestedActions getSuggestedActions() {
		return suggestedActions;
	}

	public void setSuggestedActions(SuggestedActions suggestedActions) {
		this.suggestedActions = suggestedActions;
	}

	public void setServiceUrl(String serviceUrl){
		this.serviceUrl = serviceUrl;
	}

	public String getServiceUrl(){
		return serviceUrl;
	}

	public void setFrom(From from){
		this.from = from;
	}

	public From getFrom(){
		return from;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setChannelId(String channelId){
		this.channelId = channelId;
	}

	public String getChannelId(){
		return channelId;
	}

	public void setConversation(Conversation conversation){
		this.conversation = conversation;
	}

	public Conversation getConversation(){
		return conversation;
	}

	public void setTimestamp(String timestamp){
		this.timestamp = timestamp;
	}

	public String getTimestamp(){
		return timestamp;
	}

	@Override
 	public String toString(){
		return 
			"ActivitiesItem{" + 
			"serviceUrl = '" + serviceUrl + '\'' + 
			",from = '" + from + '\'' + 
			",id = '" + id + '\'' + 
			",text = '" + text + '\'' + 
			",type = '" + type + '\'' + 
			",channelId = '" + channelId + '\'' + 
			",conversation = '" + conversation + '\'' + 
			",timestamp = '" + timestamp + '\'' + 
			"}";
		}
}