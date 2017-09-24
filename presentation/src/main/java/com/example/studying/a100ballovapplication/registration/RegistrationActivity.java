package com.example.studying.a100ballovapplication.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.studying.a100ballovapplication.NavDrawActivity;
import com.example.studying.a100ballovapplication.R;
import com.example.studying.a100ballovapplication.base.BaseView;
import com.example.studying.a100ballovapplication.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;

public class RegistrationActivity extends AppCompatActivity
                    implements BaseView {

    public static final String KEY_EMAIL = "KEY_EMAIL";
    public static final String KEY_PASS = "KEY_PASS";

    private RegistrationPresenter presenter;

    @BindView(R.id.toolbar_reg)
    Toolbar toolbarReg;

    @BindView(R.id.register_button)
    Button registerButton;

    @Nullable @BindView(R.id.progressBar_reg)
    ProgressBar progressBar;

    @BindView(R.id.reg_login)
    EditText regLogin;

    @BindView(R.id.reg_email)
    EditText regEmail;

    @BindView(R.id.reg_password)
    EditText regPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        presenter = new RegistrationPresenter(this, this);

        toolbarReg.setTitle(getIntent().getStringExtra(KEY_FRAGMENT));
        toolbarReg.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbarReg);

        toolbarReg.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = regEmail.getText().toString().trim();
                String login = regLogin.getText().toString().trim();
                String password = regPassword.getText().toString().trim();

                presenter.onRegistrationButtonClick(email, login, password);

            }
        });

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
    protected void onDestroy() {
        super.onDestroy();
        presenter.onRelease();
    }

    @Override
    public void showProgress() { progressBar.setVisibility(View.VISIBLE); }

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
        startActivity(new Intent(RegistrationActivity.this, NavDrawActivity.class));
    }

    @Override
    public void logIn(String email, String password) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        intent.putExtra(KEY_EMAIL, email);
        intent.putExtra(KEY_PASS, password);
        intent.putExtra(KEY_FRAGMENT, "Вход");
        startActivity(intent);
    }

    @Override
    public void showToast(int message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
