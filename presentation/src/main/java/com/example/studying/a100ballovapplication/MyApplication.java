package com.example.studying.a100ballovapplication;

import android.app.Application;

import com.example.studying.a100ballovapplication.di.AppComponent;
import com.example.studying.a100ballovapplication.di.AppModule;
import com.example.studying.a100ballovapplication.di.DaggerAppComponent;


public class MyApplication extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
/*
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        Realm.init(this);*/

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
