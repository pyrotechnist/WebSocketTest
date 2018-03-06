package com.example.longyuan.websockettest;



import com.example.longyuan.websockettest.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by loxu on 09/08/2017.
 */


@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    }
