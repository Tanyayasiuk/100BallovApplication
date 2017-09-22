package com.example.studying.a100ballovapplication.registration;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.studying.a100ballovapplication.MyApplication;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BasePresenter;
import com.example.studying.a100ballovapplication.base.BaseView;
import com.example.studying.a100ballovapplication.login.LoginActivity;
import com.example.studying.domain.entity.RegisterDomain;
import com.example.studying.domain.interaction.AuthService;
import com.example.studying.domain.interaction.LoginUseCase;
import com.example.studying.domain.interaction.RegisterUseCase;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class RegistrationPresenter implements BasePresenter{

    @Inject
    public LoginUseCase loginUseCase;

    @Inject
    public RegisterUseCase registerUseCase;

    @Inject
    public AuthService authService;

    private Disposable regDisposable;
    private BaseView view;

    public RegistrationPresenter(BaseView view) {
        MyApplication.appComponent.inject(this);
        this.view = view;
    }

    public void onRegistrationButtonClick(String email, String login, String password){


        if(login.equals("") || email.equals("") || password.equals("")) {
            view.showToast(R.string.error_fields_required);

        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                view.showToast(R.string.error_invalid_email);
            } else {
                RegisterDomain register = new RegisterDomain();
                register.setEmail(email);
                register.setPassword(password);
                register.setLogin(login);

                view.showProgress();
                Log.e("SSS", email + " " + login + " " + password);
                registerUseCase.execute(register, new DisposableObserver<RegisterDomain>() {
                    @Override
                    public void onNext(@NonNull RegisterDomain registerDomain) {
                        Log.e("SSS", "OnNext after registerUseCase");
                        Log.e("SSS", "new user ID" + registerDomain.getUserId());
                        view.dismissProgress();
                        view.logIn(registerDomain.getEmail(), registerDomain.getPassword());


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("SSS", "registerUseCase " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {}
                });
            }
        }


    }
    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRelease() {
        /*registerUseCase.dispose();
        loginUseCase.dispose();*/
    }
}
