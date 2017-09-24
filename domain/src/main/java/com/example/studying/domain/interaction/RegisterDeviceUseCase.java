package com.example.studying.domain.interaction;

import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.studying.data.entity.Defaults;
import com.example.studying.domain.entity.RegisterRequestDomain;
import com.example.studying.domain.interaction.base.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;


public class RegisterDeviceUseCase extends UseCase<RegisterRequestDomain, Boolean> {

    private boolean result;

    @Inject
    public RegisterDeviceUseCase(){}

    @Override
    protected Observable<Boolean> buildUseCase(final RegisterRequestDomain registerRequestDomain) {

        Backendless.initApp(registerRequestDomain.getActivity(), Defaults.APPLICATION_ID, Defaults.API_KEY);
        Backendless.Messaging.registerDevice(Defaults.SENDER_ID, Defaults.NEWS_CHANNEL, new AsyncCallback<Void>() {
            @Override
            public void handleResponse(Void response) {
                Log.e("SSS", "Device has been registered");
                result = true;
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e("SSS", "Registration ERROR " + fault.getMessage());
                result = false;
            }
        });
        return Observable.just(result);

    }

}
