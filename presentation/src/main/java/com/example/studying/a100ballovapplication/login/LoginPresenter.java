package com.example.studying.a100ballovapplication.login;

import android.content.Context;

import com.example.studying.a100ballovapplication.MyApplication;
import com.example.studying.a100ballovapplication.R;
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
        if(email.equals("") || password.equals("")){
            view.showToast(R.string.error_fields_required);
        } else {
            view.showProgress();
            final RegisterDomain register = new RegisterDomain();
            register.setEmail(email.trim());
            register.setPassword(password.trim());

            //отписываться от юзкейса лчше в release
            useCase.execute(register, new DisposableObserver<OkDomain>() {
                @Override
                public void onNext(@NonNull OkDomain okDomain) {
                    view.dismissProgress();
                    view.logIn(register.getEmail());
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    view.showToast(R.string.login_error);
                    view.dismissProgress();
                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
    @Override
    public void onResume(){

        authDisposable = authService.observeState().subscribeWith(new DisposableObserver<AuthState>() {
            @Override
            public void onNext(@NonNull AuthState authState) {
                //проверяем состояние авторизации
                //если подписаны -
                if(authState.isSigned()) {
                    view.logIn("authDisposable");
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

    @Override
    public void onRelease() {
        useCase.dispose();
    }
}
