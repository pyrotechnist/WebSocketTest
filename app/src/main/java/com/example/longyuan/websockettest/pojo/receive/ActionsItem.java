package com.example.longyuan.websockettest.pojo.receive;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ActionsItem{

	@SerializedName("displayText")
	private String displayText;

	@SerializedName("text")
	private String text;

	@SerializedName("type")
	private String type;

	@SerializedName("title")
	private String title;

	@SerializedName("value")
	private String value;

	public void setDisplayText(String displayText){
		this.displayText = displayText;
	}

	public String getDisplayText(){
		return displayText;
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

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"ActionsItem{" + 
			"displayText = '" + displayText + '\'' + 
			",text = '" + text + '\'' + 
			",type = '" + type + '\'' + 
			",title = '" + title + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}