package com.example.studying.a100ballovapplication.login;

import com.example.studying.a100ballovapplication.MyApplication;
import com.example.studying.a100ballovapplication.base.BaseView;
import com.example.studying.domain.entity.AuthState;
import com.example.studying.domain.entity.OkDomain;
import com.example.studying.domain.entity.RegisterDomain;
import com.example.studying.domain.interaction.AuthService;
import com.example.studying.domain.interaction.LoginUseCase;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class LoginPresenter implements LoginBasePresenter{

    @Inject
    public LoginUseCase useCase;

    @Inject
    public AuthService authService;

    private Disposable authDisposable;

    private BaseView view;

    public LoginPresenter(BaseView view) {
        MyApplication.appComponent.inject(this);
        this.view = view;
    }


    @Override
    public void onLoginButtonClick(String email, String password) {
        view.showProgress();
        //тут вызываем usecase
//здесь же где-то валидация полей (пустые / нет)
        RegisterDomain register = new RegisterDomain();
        register.setEmail(email);
        register.setPassword(password);

        //отписываться от юзкейса лчше в release
        useCase.execute(register, new DisposableObserver<OkDomain>() {
            @Override
            public void onNext(@NonNull OkDomain okDomain) {
                view.dismissProgress();
                view.goToMainActivity();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError("error on use case execute!" + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onResume(){

        authService.observeState().subscribeWith(new DisposableObserver<AuthState>() {
            @Override
            public void onNext(@NonNull AuthState authState) {
                //проверяем состояние авторизации
                //если подписаны -
                if(authState.isSigned()) {
                    view.goToMainActivity();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {}

            @Override
            public void onComplete() {}
        });
    }

    @Override
    public void onPause(){
        if(authDisposable!=null && !authDisposable.isDisposed()){
            authDisposable.dispose();
        }
    }

}
