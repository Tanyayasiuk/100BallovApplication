package com.yasiuk.studying.a100ballovapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.yasiuk.studying.a100ballovapplication.login.LoginActivity;
import com.yasiuk.studying.a100ballovapplication.registration.RegistrationActivity;
import com.yasiuk.studying.domain.entity.AuthState;
import com.yasiuk.studying.domain.interaction.AuthService;

import javax.inject.Inject;

import io.fabric.sdk.android.Fabric;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_ACCESS_TOKEN;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.SHARED_PREFS_NAME;

public class MainActivity extends AppCompatActivity {

    @Inject
    public AuthService authService;
    private Disposable authDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        MyApplication.appComponent.inject(this);

        setContentView(R.layout.activity_main);

        final Intent newIntent = new Intent(MainActivity.this, BasicNotLoggedActivity.class);

        final Button aboutUsButton = (Button) findViewById(R.id.about_us_button);
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(KEY_FRAGMENT, aboutUsButton.getText());
                startActivity(newIntent);
            }
        });

        final Button scheduleButton = (Button) findViewById(R.id.schedule_button);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(KEY_FRAGMENT, scheduleButton.getText());
                startActivity(newIntent);
            }
        });

        final Button enrollButton = (Button) findViewById(R.id.enroll_button);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(KEY_FRAGMENT,enrollButton.getText());
                startActivity(newIntent);
            }
        });

        final Button contactsButton = (Button) findViewById(R.id.contacts_button);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(KEY_FRAGMENT, contactsButton.getText());
                startActivity(newIntent);
            }
        });

        final Button enterButton = (Button) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra(KEY_FRAGMENT, enterButton.getText());
                startActivity(intent);
            }
        });

        final TextView registerButton = (TextView) findViewById(R.id.register_link);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                intent.putExtra(KEY_FRAGMENT, registerButton.getText());
                startActivity(intent);
            }
        });

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        if(!preferences.getString(KEY_ACCESS_TOKEN, "").equals("")){
            Intent intent = new Intent(MainActivity.this, NavDrawActivity.class);
            intent.putExtra(KEY_FRAGMENT, R.string.news_item);
            startActivity(intent);
            finish();
        }


    }

    @Override
    public void onResume(){
        Log.e("SSS", "Main Activity - onResume ");
        authDisposable = authService.observeState().subscribeWith(new DisposableObserver<AuthState>() {
            @Override
            public void onNext(@NonNull AuthState authState) {
                if(authState.isSigned()) {
                    TextView reg = (TextView) findViewById(R.id.register_link);
                    reg.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("SSS", "MAin Activity - onResume - Auth. Error " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {}
        });
        super.onResume();
    }

    @Override
    public void onPause(){
        Log.e("SSS", "Main Activity - onPause ");
        super.onPause();
        if(authDisposable!=null && !authDisposable.isDisposed()){
            authDisposable.dispose();
        }
    }

    @Override
    protected void onDestroy() {
        Log.e("SSS", "Main Activity - onDestroy ");
        super.onDestroy();
    }


    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
}
