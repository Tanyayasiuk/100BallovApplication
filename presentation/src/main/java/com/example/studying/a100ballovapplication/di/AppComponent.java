package com.example.studying.a100ballovapplication.di;

import com.example.studying.a100ballovapplication.NavDrawActivity;
import com.example.studying.a100ballovapplication.enroll.EnrollFragmentViewModel;
import com.example.studying.a100ballovapplication.login.LoginPresenter;
import com.example.studying.a100ballovapplication.registration.RegistrationPresenter;

import javax.inject.Singleton;

import dagger.Component;

//Настроечный класс. Связующее звено между классами, где исп-зя аннотация Inject, и модулем
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(LoginPresenter logPresenter);
    void inject(RegistrationPresenter regPresenter);
    void inject(NavDrawActivity activity);
    void inject (EnrollFragmentViewModel viewModel);

    //Прописываются все классы, где мы хотим использовать inject


}
