package com.example.longyuan.websockettest.pojo.receive;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Content{

	@SerializedName("shareable")
	private boolean shareable;

	@SerializedName("image")
	private Image image;

	@SerializedName("buttons")
	private List<ButtonsItem> buttons;

	@SerializedName("text")
	private String text;

	@SerializedName("media")
	private List<MediaItem> media;

	@SerializedName("title")
	private String title;

	@SerializedName("autostart")
	private boolean autostart;

	@SerializedName("autoloop")
	private boolean autoloop;

	public void setShareable(boolean shareable){
		this.shareable = shareable;
	}

	public boolean isShareable(){
		return shareable;
	}

	public void setImage(Image image){
		this.image = image;
	}

	public Image getImage(){
		return image;
	}

	public void setButtons(List<ButtonsItem> buttons){
		this.buttons = buttons;
	}

	public List<ButtonsItem> getButtons(){
		return buttons;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setMedia(List<MediaItem> media){
		this.media = media;
	}

	public List<MediaItem> getMedia(){
		return media;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setAutostart(boolean autostart){
		this.autostart = autostart;
	}

	public boolean isAutostart(){
		return autostart;
	}

	public void setAutoloop(boolean autoloop){
		this.autoloop = autoloop;
	}

	public boolean isAutoloop(){
		return autoloop;
	}

	@Override
 	public String toString(){
		return 
			"Content{" + 
			"shareable = '" + shareable + '\'' + 
			",image = '" + image + '\'' + 
			",buttons = '" + buttons + '\'' + 
			",text = '" + text + '\'' + 
			",media = '" + media + '\'' + 
			",title = '" + title + '\'' + 
			",autostart = '" + autostart + '\'' + 
			",autoloop = '" + autoloop + '\'' + 
			"}";
		}
}