package com.example.studying.domain.interaction;

import com.example.studying.data.entity.StudentLoginData;
import com.example.studying.data.net.RestService;
import com.example.studying.domain.entity.RegisterDomain;
import com.example.studying.domain.interaction.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class RegisterUseCase extends UseCase<RegisterDomain, RegisterDomain> {


    @Inject
    public RegisterUseCase() {}

    @Override
    protected Observable<RegisterDomain> buildUseCase(RegisterDomain registerDomain) {
        return RestService.getInstance().createUser(convert(registerDomain))
                .map(new Function<StudentLoginData, RegisterDomain>() {
                    @Override
                    public RegisterDomain apply(@NonNull StudentLoginData loginData) throws Exception {
                        RegisterDomain newUser = new RegisterDomain();
                        newUser.setEmail(loginData.getEmail());
                        newUser.setPassword(loginData.getPassword());
                        newUser.setUserId(loginData.getObjectId());
                        newUser.setLogin(loginData.getLogin());
                        newUser.setClassNum(loginData.getClassNum());
                        return newUser;
                    }
                });

    }

    private StudentLoginData convert(RegisterDomain registerDomain){
        StudentLoginData studentLoginData = new StudentLoginData();
        studentLoginData.setEmail(registerDomain.getEmail());
        studentLoginData.setPassword(registerDomain.getPassword());
        studentLoginData.setLogin(registerDomain.getLogin());
        studentLoginData.setClassNum(registerDomain.getClassNum());
        return studentLoginData;
    }
}
