package com.example.longyuan.websockettest.api;

import com.example.longyuan.websockettest.pojo.InitConversationResponse;
import com.example.longyuan.websockettest.pojo.SendMessageResponse;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by loxu on 06/03/2018.
 */

public interface ConversationService {

    @POST("conversations")
    Observable<InitConversationResponse> getConversation(@Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @POST("conversations/{conversationId}/activities")
    Observable<SendMessageResponse> SendMessage(@Header("Authorization") String token,
                                                @Path("conversationId") String conversationId,
                                                @Body String body);
}
