package com.example.studying.domain.interaction;

import android.util.Log;

import com.example.studying.data.entity.StudentLoginData;
import com.example.studying.data.net.RestService;
import com.example.studying.domain.entity.OkDomain;
import com.example.studying.domain.entity.RegisterDomain;
import com.example.studying.domain.interaction.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class LoginUseCase extends UseCase<RegisterDomain, OkDomain> {

    AuthService authService;

    @Inject
    public LoginUseCase(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected Observable<OkDomain> buildUseCase(final RegisterDomain registerDomain) {
        Log.e("SSS", "useCase working ");
        Log.e("SSS", "email " + registerDomain.getEmail());
        Log.e("SSS", "password " + registerDomain.getPassword());
        return RestService.getInstance().login(convert(registerDomain))
                .doOnNext(new Consumer<StudentLoginData>() {
                    @Override
                    public void accept(StudentLoginData loginData) throws Exception {
                        authService.saveAccessToken(loginData.getToken());
                        authService.saveUserData(loginData.getLogin(),
                                loginData.getObjectId(),
                                loginData.getEmail());
                        Log.e("SSS", "Saving userData in SharedPrefs");
                    }
                })
                .map(new Function<StudentLoginData, OkDomain>() {
                    @Override
                    public OkDomain apply(@NonNull StudentLoginData studentLoginData) throws Exception {
                        return new OkDomain();
                    }
                });
    }

    private StudentLoginData convert(RegisterDomain registerDomain){
        StudentLoginData studentLoginData = new StudentLoginData();
        studentLoginData.setLogin(registerDomain.getEmail());
        studentLoginData.setPassword(registerDomain.getPassword());
        return studentLoginData;
    }

}
