package com.example.studying.a100ballovapplication.di;

import android.app.Activity;
import android.content.Context;


import dagger.Module;
import dagger.Provides;

//По сути фабрика для создания объектов, которые мы потом хотим инджектить
@Module
public class AppModule {

    private Context context;
    private Activity activity;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    public Activity provideActivity(){return activity;}


}
