package com.example.longyuan.websockettest.pojo.receive;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class AttachmentsItem {

	@SerializedName("contentType")
	private String contentType;

	@SerializedName("content")
	private Content content;

	public void setContentType(String contentType){
		this.contentType = contentType;
	}

	public String getContentType(){
		return contentType;
	}

	public void setContent(Content content){
		this.content = content;
	}

	public Content getContent(){
		return content;
	}

	@Override
 	public String toString(){
		return 
			"AttachmentsItem{" +
			"contentType = '" + contentType + '\'' + 
			",content = '" + content + '\'' + 
			"}";
		}
}