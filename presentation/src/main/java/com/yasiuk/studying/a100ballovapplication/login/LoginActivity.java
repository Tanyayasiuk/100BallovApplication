package com.yasiuk.studying.a100ballovapplication.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yasiuk.studying.a100ballovapplication.MainActivity;
import com.yasiuk.studying.a100ballovapplication.Manifest;
import com.yasiuk.studying.a100ballovapplication.NavDrawActivity;
import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.base.BaseView;
import com.yasiuk.studying.a100ballovapplication.registration.RegistrationActivity;

import io.reactivex.functions.Consumer;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;
import static com.yasiuk.studying.a100ballovapplication.base.Defaults.SHARED_PREFS_NAME;
import static com.yasiuk.studying.a100ballovapplication.registration.RegistrationActivity.KEY_EMAIL;

public class LoginActivity extends AppCompatActivity implements BaseView {

    private LoginPresenter presenter;

    private Toolbar toolbarLogin;
    private Button enterButton;
    private ProgressBar progressBar;
    private EditText enterEmail;
    private EditText enterPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenter(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        toolbarLogin = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbarLogin);

        toolbarLogin.setNavigationIcon(R.drawable.ic_arrow_back);
        setTitle(getIntent().getStringExtra(KEY_FRAGMENT));

        toolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        enterButton = (Button) findViewById(R.id.enter_button);
        enterEmail = (EditText) findViewById(R.id.enter_login);
        enterPassword = (EditText) findViewById(R.id.enter_password);

        SharedPreferences preferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);

        enterEmail.setText(getIntent().getStringExtra(KEY_EMAIL));

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = enterEmail.getText().toString().trim();
                String password = enterPassword.getText().toString().trim();
                presenter.onLoginButtonClick(email, password);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void goToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void askPermission() {
        RxPermissions rxPermissions = new RxPermissions(LoginActivity.this);
        rxPermissions
                .request(Manifest.permission.C2D_MESSAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                        } else {
                            Toast.makeText(LoginActivity.this, "Уведомления недоступны", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }

    @Override
    public void logIn(String login) {
        Intent intent = new Intent(LoginActivity.this, NavDrawActivity.class);
        intent.putExtra(KEY_FRAGMENT, R.string.profile_item);
        startActivity(intent);
    }

    @Override
    public void showToast(int message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
