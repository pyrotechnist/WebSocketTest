package com.example.longyuan.websockettest;

import android.app.Application;

import com.example.longyuan.websockettest.network.NetworkModule;


/**
 * Created by loxu on 07/08/2017.
 */

public class App extends Application {


        private static AppComponent mAppComponent;

        @Override
        public void onCreate() {
            super.onCreate();

            mAppComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule("https://europe.directline.botframework.com/v3/directline/",this))
                    .build();

        }

        public static AppComponent getAppComponent() {

            return  mAppComponent;
        }




}
