package com.yasiuk.studying.a100ballovapplication;

import android.app.Application;

import com.yasiuk.studying.a100ballovapplication.di.AppComponent;
import com.yasiuk.studying.a100ballovapplication.di.AppModule;
import com.yasiuk.studying.a100ballovapplication.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;


public class MyApplication extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
