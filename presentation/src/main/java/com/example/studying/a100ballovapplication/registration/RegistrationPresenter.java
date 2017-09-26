package com.example.studying.a100ballovapplication.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.studying.a100ballovapplication.MyApplication;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BasePresenter;
import com.example.studying.a100ballovapplication.base.BaseView;
import com.example.studying.a100ballovapplication.login.LoginActivity;
import com.example.studying.domain.entity.RegisterDomain;
import com.example.studying.domain.entity.RegisterRequestDomain;
import com.example.studying.domain.entity.RegisterResponseDomain;
import com.example.studying.domain.interaction.AuthService;
import com.example.studying.domain.interaction.LoginUseCase;
import com.example.studying.domain.interaction.RegisterDeviceUseCase;
import com.example.studying.domain.interaction.RegisterUseCase;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.example.studying.a100ballovapplication.base.Defaults.CLASS_NUM;
import static com.example.studying.a100ballovapplication.base.Defaults.SHARED_PREFS_NAME;

public class RegistrationPresenter implements BasePresenter{

    @Inject
    public LoginUseCase loginUseCase;

    @Inject
    public RegisterUseCase registerUseCase;

    @Inject
    public AuthService authService;

    @Inject
    public RegisterDeviceUseCase registerDeviceUseCase;

    private BaseView view;
    private Activity activity;

    public RegistrationPresenter(BaseView view, Activity activity) {
        MyApplication.appComponent.inject(this);
        this.view = view;
        this.activity = activity;
    }

    public void onRegistrationButtonClick(String email, String login, String password, final int classNum){

        if(login.equals("") || email.equals("") || password.equals("") || classNum == 0) {
            view.showToast(R.string.error_fields_required);

        } else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                view.showToast(R.string.error_invalid_email);
            } else {
                RegisterDomain register = new RegisterDomain();
                register.setEmail(email);
                register.setPassword(password);
                register.setLogin(login);
                register.setClassNum(classNum);
                view.showProgress();

                registerUseCase.execute(register, new DisposableObserver<RegisterDomain>() {
                    @Override
                    public void onNext(@NonNull final RegisterDomain registerDomain) {
                        Log.e("SSS", "new user ID: " + registerDomain.getUserId());

                        /*уже нет смысла тут его сохранять
                        SharedPreferences preferences = activity
                                .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
                        preferences.edit().putInt(CLASS_NUM, classNum).apply();
                        Log.e("SSS", "added to prefs: " + preferences.getInt(CLASS_NUM, 0));*/

                        RegisterRequestDomain requestDomain = new RegisterRequestDomain(activity);
                        registerDeviceUseCase.execute(requestDomain, new DisposableObserver<Boolean>() {
                                    @Override
                                    public void onNext(@NonNull Boolean aBoolean) {
                                        Log.e("SSS", "OnNExt after registerDeviceUseCase");
                                        view.dismissProgress();
                                        view.logIn(registerDomain.getEmail());
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.e("SSS", "REG DEVICE ERROR: " + e.getLocalizedMessage());
                                        view.showError(e.getLocalizedMessage());
                                        view.goToMainActivity();
                                    }

                                    @Override
                                    public void onComplete() {
                                        registerDeviceUseCase.dispose();
                                    }
                                });

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("SSS", "registerUseCase " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        /*
                        registerUseCase.dispose();*/}
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
    }
}
