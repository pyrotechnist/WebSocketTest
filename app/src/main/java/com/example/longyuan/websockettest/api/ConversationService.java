package com.example.longyuan.websockettest.api;

import com.example.longyuan.websockettest.pojo.InitConversationResponse;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by loxu on 06/03/2018.
 */

public interface ConversationService {

    @POST("conversations")
    Observable<InitConversationResponse> getConversation(@Header("Authorization") String token);
}
