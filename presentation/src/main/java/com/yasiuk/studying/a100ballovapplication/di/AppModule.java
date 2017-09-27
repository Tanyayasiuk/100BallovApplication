package com.yasiuk.studying.a100ballovapplication.di;

import android.content.Context;


import com.yasiuk.studying.domain.interaction.RegisterDeviceUseCase;

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
