package com.example.longyuan.websockettest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.longyuan.websockettest.api.ConversationService;
import com.example.longyuan.websockettest.pojo.ActivitiesItem;
import com.example.longyuan.websockettest.pojo.From;
import com.example.longyuan.websockettest.pojo.InitConversationResponse;
import com.example.longyuan.websockettest.pojo.ReceivedMessage;
import com.example.longyuan.websockettest.pojo.SendMessageRequest;
import com.example.longyuan.websockettest.pojo.SendMessageResponse;
import com.example.longyuan.websockettest.pojo.message.Message;
import com.example.longyuan.websockettest.pojo.receive.ActionsItem;
import com.example.longyuan.websockettest.pojo.receive.SuggestedActions;
import com.example.longyuan.websockettest.utils.MessageListAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button_chatbox_send)
    Button send;

    @BindView(R.id.edittext_chatbox)
    EditText mEditText;

    @BindView(R.id.layout_actions)
    LinearLayout mLinearLayout_Actions;

    @BindView(R.id.reyclerview_message_list)
    RecyclerView mMessageRecycler;

    private List<Message> mMessageList;

    private MessageListAdapter mMessageAdapter;

    private String mConversationId;

    private String mBotId;

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

        // View Injection
        ButterKnife.bind(this);

        // Dependency Injection
        App.getAppComponent().inject(this);

        setUpConversationView();

        InitConversation();

    }

    private void setUpConversationView() {

        mMessageList = new ArrayList<>();
        mMessageAdapter = new MessageListAdapter(this, mMessageList);

        mMessageRecycler.setAdapter(mMessageAdapter);

        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

    }

    private void InitConversation() {

        mConversationService.getConversation("Bearer C96ZXRSP7YY.cwA.vHU.WYe7smPU5rbaiVtZbeD6j03GQ2fnLQcpt-c74E0iShw")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> start(data));
    }

    @OnClick(R.id.button_chatbox_send)
    public void sendMessageButton() {

        String text = mEditText.getText().toString();

        sendMessage(text);

        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    private void sendMessage(String  message){

        mLinearLayout_Actions.removeAllViews();

        mLinearLayout_Actions.invalidate();

        SendMessageRequest sendMessageRequest = new SendMessageRequest();

        sendMessageRequest.setText(message);

        From from = new From("");

        from.setId("xu");

        sendMessageRequest.setFrom(from);

        sendMessageRequest.setType("message");

        mMessageList.add(new Message(sendMessageRequest));

        mMessageAdapter.updateData(mMessageList);

        mConversationService.SendMessage("Bearer C96ZXRSP7YY.cwA.vHU.WYe7smPU5rbaiVtZbeD6j03GQ2fnLQcpt-c74E0iShw",mConversationId,sendMessageRequest.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> process(data));

    }


    private void process(SendMessageResponse data) {

        //output("Message sent OK");
    }


    private void start(InitConversationResponse initConversationResponse) {

        mConversationId = initConversationResponse.getConversationId();

        Request request = new Request.Builder().url(initConversationResponse.getStreamUrl()).build();

        EchoWebSocketListener listener = new EchoWebSocketListener();
        WebSocket ws = mOkHttpClient.newWebSocket(request, listener);
        mOkHttpClient.dispatcher().executorService().shutdown();
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

            sendConnectedEvent();

            Log.d("WebSocket ","WebSocket Connected !!!!!");

           /*  webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");*/
        }
        @Override
        public void onMessage(WebSocket webSocket, String text) {

            Message message ;



            if(text!= null && !text.isEmpty())
            {
                ReceivedMessage receivedMessage  = mGson.fromJson(text, ReceivedMessage.class);

                ActivitiesItem activitiesItem = receivedMessage.getActivities().get(0);

                Log.d("WebSocket in","Type: "+ activitiesItem.getType() + activitiesItem.toString());

                if(activitiesItem.getType().equals("message") && !activitiesItem.getFrom().getId().equals("xu"))
                {
                    message =  new Message(activitiesItem);

                    if(activitiesItem.getSuggestedActions()!=null ){

                        rx.Observable.from(activitiesItem.getSuggestedActions().getActions())
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(action -> addActionsButton(action));

                        Log.d("actions",activitiesItem.getSuggestedActions().toString());
                    }

                    Log.d("WebSocket",message.getMessage());

                    updateData(message);
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

    private void addActionsButton(ActionsItem actionsItem) {

        Button button = new Button(getApplicationContext());
        button.setText(actionsItem.getText());
        button.setTextSize(10);
        button.setGravity(Gravity.CENTER);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendMessage(actionsItem.getText());
            }
        });

        mLinearLayout_Actions.addView(button);

    }



    private void updateData(Message message){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mMessageList.add(message);

                mMessageAdapter.updateData(mMessageList);

                mMessageRecycler.smoothScrollToPosition(mMessageList.size()-1);
            }
        });


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
