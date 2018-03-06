package com.example.longyuan.websockettest.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class InitConversationResponse{

	@SerializedName("streamUrl")
	private String streamUrl;

	@SerializedName("conversationId")
	private String conversationId;

	@SerializedName("referenceGrammarId")
	private String referenceGrammarId;

	@SerializedName("expires_in")
	private int expiresIn;

	@SerializedName("token")
	private String token;

	public void setStreamUrl(String streamUrl){
		this.streamUrl = streamUrl;
	}

	public String getStreamUrl(){
		return streamUrl;
	}

	public void setConversationId(String conversationId){
		this.conversationId = conversationId;
	}

	public String getConversationId(){
		return conversationId;
	}

	public void setReferenceGrammarId(String referenceGrammarId){
		this.referenceGrammarId = referenceGrammarId;
	}

	public String getReferenceGrammarId(){
		return referenceGrammarId;
	}

	public void setExpiresIn(int expiresIn){
		this.expiresIn = expiresIn;
	}

	public int getExpiresIn(){
		return expiresIn;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"InitConversationResponse{" + 
			"streamUrl = '" + streamUrl + '\'' + 
			",conversationId = '" + conversationId + '\'' + 
			",referenceGrammarId = '" + referenceGrammarId + '\'' + 
			",expires_in = '" + expiresIn + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}