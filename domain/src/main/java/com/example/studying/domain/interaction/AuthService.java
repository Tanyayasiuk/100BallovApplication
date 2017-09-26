package com.example.studying.domain.interaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.example.studying.domain.entity.AuthState;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

//По сути реализует паттерн observable
@Singleton
public class AuthService {

    private static final String KEY_ACCESS_TOKEN = "accessToken";
    private static final String KEY_USER_LOGIN = "login";
    private static final String KEY_USER_EMAIL = "email";
    public static final String CLASS_NUM = "classNumber";
    private static final String KEY_OBJECT_ID = "objectId";
    private static final String SHARED_PREFS_NAME = "sharedPrefs";
    private Context context;

    private BehaviorSubject<AuthState> state = BehaviorSubject
                                .createDefault(new AuthState(false));

    @Inject
    public AuthService(Context context){
        this.context = context;
        restoreAccessToken();
    }

    void saveAccessToken (String accessToken){
        //save token locally
        SharedPreferences preferences = context
                .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putString(KEY_ACCESS_TOKEN, accessToken).apply();

        state.onNext(new AuthState(true));
    }

    void saveUserData (String login, String objectId, String email, int classNum){
        Log.e("SSS", "saveUserData");
        SharedPreferences prefs = context
                .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_USER_LOGIN, login)
                          .putString(KEY_OBJECT_ID, objectId)
                          .putString(KEY_USER_EMAIL, email)
                          .putInt(CLASS_NUM, classNum)
                          .apply();
    }

     void removeAccessToken(){
        //удаляем токен, затем уведомляем подписчиков, что разлогинились
        SharedPreferences preferences = context
                .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().remove(KEY_ACCESS_TOKEN).apply();
        Log.e("SSS", "removing AccessToken");
        state.onNext(new AuthState(false));
    }

    //для подписки на уведомления о статусе
    public Observable<AuthState> observeState(){return state;}

    private void restoreAccessToken(){

        SharedPreferences preferences = context
                .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        String token = preferences.getString(KEY_ACCESS_TOKEN, null);

        //если токен не null - значит, мы залогинены
        if(!TextUtils.isEmpty(token)){
            state.onNext(new AuthState(true));
            Log.e("SSS", "new AuthState(true)");
        } else {
            state.onNext(new AuthState(false));
            Log.e("SSS", "new AuthState(false)");
        }

    }


}
