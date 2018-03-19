package com.example.longyuan.websockettest.pojo.message;

import com.example.longyuan.websockettest.pojo.ActivitiesItem;
import com.example.longyuan.websockettest.pojo.From;
import com.example.longyuan.websockettest.pojo.SendMessageRequest;
import com.example.longyuan.websockettest.pojo.receive.AttachmentsItem;
import com.example.longyuan.websockettest.utils.Constant;

/**
 * Created by loxu on 13/03/2018.
 */

public class Message {


    public Message(ActivitiesItem activitiesItem) {
        message = activitiesItem.getText();
        sender = activitiesItem.getFrom();
        //createdAt = activitiesItem.getTimestamp();

    }

    public Message(From from, AttachmentsItem attachmentsItem) {
        message = attachmentsItem.getContent().getText();
        sender = from;
        attachmentUrl =  getImageUrl(attachmentsItem);
        //createdAt = activitiesItem.getTimestamp();

    }

    private String getImageUrl(AttachmentsItem attachmentsItem) {


        if(attachmentsItem.getContentType().contains(Constant.CONTENT_TYPE_ANIMATION))
        {
            return attachmentsItem.getContent().getMedia().get(0).getUrl();

        }else if(attachmentsItem.getContentType().contains(Constant.CONTENT_TYPE_AUDIO) || attachmentsItem.getContentType().contains(Constant.CONTENT_TYPE_VIDEO))
        {
            return attachmentsItem.getContent().getImage().getUrl();
        }

        return null;
    }

    public Message(SendMessageRequest sendMessageRequest) {
        message = sendMessageRequest.getText();
        sender = sendMessageRequest.getFrom();
        //createdAt = activitiesItem.getTimestamp();

    }

    String message;
    From sender;
    long createdAt;
    private String attachmentUrl;

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public From getSender() {
        return sender;
    }

    public void setSender(From sender) {
        this.sender = sender;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }
}
