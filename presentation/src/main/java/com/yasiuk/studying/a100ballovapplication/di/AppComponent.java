package com.yasiuk.studying.a100ballovapplication.di;

import com.yasiuk.studying.a100ballovapplication.MainActivity;
import com.yasiuk.studying.a100ballovapplication.NavDrawActivity;
import com.yasiuk.studying.a100ballovapplication.enroll.EnrollFragmentViewModel;
import com.yasiuk.studying.a100ballovapplication.login.LoginPresenter;
import com.yasiuk.studying.a100ballovapplication.news.NewsFragmentViewModel;
import com.yasiuk.studying.a100ballovapplication.registration.RegistrationPresenter;
import com.yasiuk.studying.domain.interaction.RegisterDeviceUseCase;

import javax.inject.Singleton;

import dagger.Component;

//Настроечный класс. Связующее звено между классами, где исп-зя аннотация Inject, и модулем
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject (MainActivity activity);
    void inject(LoginPresenter logPresenter);
    void inject(RegistrationPresenter regPresenter);
    void inject(NavDrawActivity activity);
    void inject (EnrollFragmentViewModel viewModel);
    void inject (NewsFragmentViewModel newsViewModel);
    void inject(RegisterDeviceUseCase registerDeviceUseCase);


    //Прописываются все классы, где мы хотим использовать inject


}
