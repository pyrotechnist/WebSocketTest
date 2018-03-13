package com.example.longyuan.websockettest.pojo.message;

import com.example.longyuan.websockettest.pojo.ActivitiesItem;
import com.example.longyuan.websockettest.pojo.From;
import com.example.longyuan.websockettest.pojo.SendMessageRequest;

/**
 * Created by loxu on 13/03/2018.
 */

public class Message {


    public Message(ActivitiesItem activitiesItem) {
        message = activitiesItem.getText();
        sender = activitiesItem.getFrom();
        //createdAt = activitiesItem.getTimestamp();

    }

    public Message(SendMessageRequest sendMessageRequest) {
        message = sendMessageRequest.getText();
        sender = sendMessageRequest.getFrom();
        //createdAt = activitiesItem.getTimestamp();

    }

    String message;
    From sender;
    long createdAt;

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
