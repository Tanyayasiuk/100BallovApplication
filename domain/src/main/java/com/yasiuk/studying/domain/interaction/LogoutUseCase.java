package com.yasiuk.studying.domain.interaction;

import android.util.Log;

import com.yasiuk.studying.data.net.RestService;
import com.yasiuk.studying.domain.entity.OkDomain;
import com.yasiuk.studying.domain.interaction.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class LogoutUseCase extends UseCase<Void, OkDomain> {

    AuthService authService;

    @Inject
    public LogoutUseCase(AuthService authService) {
        this.authService = authService;
    }

    @Override
    protected Observable<OkDomain> buildUseCase(Void aVoid) {
        Log.e("SSS", "log out ");
        return RestService.getInstance().logout()
                .doOnNext(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) throws Exception {
                        authService.removeAccessToken();
                    }
                })
                .map(new Function<Void, OkDomain>() {
                    @Override
                    public OkDomain apply(@NonNull Void aVoid) throws Exception {
                        return new OkDomain();
                    }
                });
    }
}
