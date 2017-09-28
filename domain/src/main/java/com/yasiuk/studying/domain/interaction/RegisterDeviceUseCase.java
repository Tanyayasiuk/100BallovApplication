package com.yasiuk.studying.domain.interaction;

import android.util.Log;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.yasiuk.studying.data.entity.Defaults;
import com.yasiuk.studying.domain.entity.RegisterRequestDomain;
import com.yasiuk.studying.domain.interaction.base.UseCase;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.inject.Inject;

import io.reactivex.Observable;


public class RegisterDeviceUseCase extends UseCase<RegisterRequestDomain, Boolean> {

    private boolean result;

    @Inject
    public RegisterDeviceUseCase(){}

    @Override
    protected Observable<Boolean> buildUseCase(final RegisterRequestDomain registerRequestDomain) {

        Defaults.NEWS_CHANNEL.add("news");
        Backendless.initApp(registerRequestDomain.getActivity(), Defaults.APPLICATION_ID, Defaults.API_KEY);
        Backendless.Messaging.registerDevice(Defaults.SENDER_ID, Defaults.NEWS_CHANNEL, getExpirationDate(), new AsyncCallback<Void>() {
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

    private Date getExpirationDate(){
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();

    }

}
