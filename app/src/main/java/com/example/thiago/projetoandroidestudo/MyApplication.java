package com.example.thiago.projetoandroidestudo;

import android.app.Application;

import com.example.thiago.projetoandroidestudo.util.AppUtil;

/**
 * Created by Thiago on 27/07/2015.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        AppUtil.CONTEXT = getApplicationContext();
        super.onCreate();
    }
}
