package com.example.longyuan.websockettest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.longyuan.websockettest.api.ConversationService;
import com.example.longyuan.websockettest.pojo.ActivitiesItem;
import com.example.longyuan.websockettest.pojo.From;
import com.example.longyuan.websockettest.pojo.InitConversationResponse;
import com.example.longyuan.websockettest.pojo.ReceivedMessage;
import com.example.longyuan.websockettest.pojo.SendMessageRequest;
import com.example.longyuan.websockettest.pojo.SendMessageResponse;
import com.example.longyuan.websockettest.pojo.message.Message;
import com.example.longyuan.websockettest.utils.MessageListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient client;
    private Button start;
    private Button hi;
    private TextView output;

    private String mWssUrl;

    private List<Message> mMessageList;

    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;

    private String mConversationId;

    @Inject
    Gson mGson;

    @Inject
    OkHttpClient mOkHttpClient;

    @Inject
    ConversationService mConversationService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        App.getAppComponent().inject(this);

        mMessageList = new ArrayList<>();
        mMessageRecycler = (RecyclerView) findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(this, mMessageList);

        mMessageRecycler.setAdapter(mMessageAdapter);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

        InitConversation();

       /* start = (Button) findViewById(R.id.start);
        hi = (Button) findViewById(R.id.hi);
        output = (TextView) findViewById(R.id.output);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InitConversation();
            }
        });

        hi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayHi();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void InitConversation() {

        mConversationService.getConversation("Bearer C96ZXRSP7YY.cwA.vHU.WYe7smPU5rbaiVtZbeD6j03GQ2fnLQcpt-c74E0iShw")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> start(data));
    }


    private void sayHi() {


        SendMessageRequest sendMessageRequest = new SendMessageRequest();

        sendMessageRequest.setText("Hello");

        From from = new From("");

        from.setId("XU");

        sendMessageRequest.setFrom(from);

        sendMessageRequest.setType("message");


        mConversationService.SendMessage("Bearer C96ZXRSP7YY.cwA.vHU.WYe7smPU5rbaiVtZbeD6j03GQ2fnLQcpt-c74E0iShw",mConversationId,sendMessageRequest.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> process(data));
    }

    private void process(SendMessageResponse data) {

        //output("Message sent OK");
    }


    private void start(InitConversationResponse initConversationResponse) {
        //Request request = new Request.Builder().url("ws://echo.websocket.org").build();

        mConversationId = initConversationResponse.getConversationId();

        Request request = new Request.Builder().url(initConversationResponse.getStreamUrl()).build();

        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = mOkHttpClient.newWebSocket(request, listener);
        mOkHttpClient.dispatcher().executorService().shutdown();
    }
    private void output(final String txt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setText(output.getText().toString() + "\n\n" + txt);
            }
        });
    }


    private void sendConnectedEvent(){
        SendMessageRequest sendMessageRequest = new SendMessageRequest();

        From from = new From("");

        from.setId("xu");

        sendMessageRequest.setFrom(from);

        sendMessageRequest.setType("event");

        sendMessageRequest.setName("connected");


        mConversationService.SendMessage("Bearer C96ZXRSP7YY.cwA.vHU.WYe7smPU5rbaiVtZbeD6j03GQ2fnLQcpt-c74E0iShw",mConversationId,sendMessageRequest.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> process(data));
    }

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;
        @Override
        public void onOpen(WebSocket webSocket, Response response) {

            //output("Connected");

            sendConnectedEvent();

            //webSocket.send("{\"type\":\"message\",\"text\":\"hello again\",\"from\":{\"id\":\"user\",\"name\":\"xu\"}}");
         /*   webSocket.send("What's up ?");
            webSocket.send(ByteString.decodeHex("deadbeef"));
            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");*/
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {

            Message message ;

            Log.d("",text);

            if(text!= null && !text.isEmpty())
            {

                ReceivedMessage receivedMessage  = mGson.fromJson(text, ReceivedMessage.class);


                ActivitiesItem activitiesItem = receivedMessage.getActivities().get(0);

                if(activitiesItem.getType().equals("message"))
                {
                    message =  new Message(receivedMessage.getActivities().get(0));

                    mMessageList.add(message);

                    mMessageAdapter.updateData(mMessageList);
                }

            }
            else
            {
                text = "Ping";
            }

           // output("Receiving : " + text);
        }
        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
           // output("Receiving bytes : " + bytes.hex());
        }
        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
            //output("Closing : " + code + " / " + reason);
        }
        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
          //  output("Error : " + t.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
