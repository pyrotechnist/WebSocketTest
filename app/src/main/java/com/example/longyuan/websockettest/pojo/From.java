package com.example.longyuan.websockettest.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class From{

	public From(String id) {
		this.id = id;
	}

	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"{" +
			"\"id\" :" +"\""+ id +"\"" +
			"}";
		}
}