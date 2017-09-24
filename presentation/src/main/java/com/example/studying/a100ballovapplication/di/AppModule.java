package com.example.studying.a100ballovapplication.di;

import android.app.Activity;
import android.content.Context;
import android.view.View;


import com.example.studying.a100ballovapplication.base.BaseView;
import com.example.studying.a100ballovapplication.registration.RegistrationPresenter;
import com.example.studying.domain.interaction.RegisterDeviceUseCase;

import dagger.Module;
import dagger.Provides;

//По сути фабрика для создания объектов, которые мы потом хотим инджектить
@Module
public class AppModule {

    private Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

    @Provides
    public RegisterDeviceUseCase registerDeviceUseCase(){
        return new RegisterDeviceUseCase();
    }





}
