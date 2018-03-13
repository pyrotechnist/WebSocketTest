package com.example.longyuan.websockettest.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class SendMessageRequest{

	@SerializedName("from")
	private From from;

	@SerializedName("text")
	private String text;

	@SerializedName("type")
	private String type;

	@SerializedName("name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFrom(From from){
		this.from = from;
	}

	public From getFrom(){
		return from;
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

	@Override
 	public String toString(){
		return 
			"{" +
			"\"from\" :  " + from  +
			",\"text\" : " +"\"" + text +"\"" +
			",\"type\" : " +"\"" + type +"\"" +
					",\"name\" : " +"\"" + name +"\"" +
			"}";
		}
}