package com.yasiuk.studying.a100ballovapplication.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yasiuk.studying.a100ballovapplication.MainActivity;
import com.yasiuk.studying.a100ballovapplication.Manifest;
import com.yasiuk.studying.a100ballovapplication.R;
import com.yasiuk.studying.a100ballovapplication.base.BaseView;
import com.yasiuk.studying.a100ballovapplication.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

import static com.yasiuk.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;

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

    @BindView(R.id.spinner_reg)
    Spinner spinner;


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

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(this, R.array.choose_class, R.layout.spinner_item_my);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item_my);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int classNum;
                String email = regEmail.getText().toString().trim();
                String login = regLogin.getText().toString().trim();
                String password = regPassword.getText().toString().trim();
                String classString = spinner.getSelectedItem().toString();
                try {
                    classNum = Integer.parseInt(classString.replaceAll("\\D+",""));
                } catch (NumberFormatException e){
                    classNum = 0;
                }
                presenter.onRegistrationButtonClick(email, login, password, classNum);

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
        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }


    @Override
    public void logIn(String email) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        intent.putExtra(KEY_EMAIL, email);
        intent.putExtra(KEY_FRAGMENT, "Вход");
        startActivity(intent);
    }

    @Override
    public void showToast(int message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void askPermission(){
        RxPermissions rxPermissions = new RxPermissions(RegistrationActivity.this);
        rxPermissions
                .request(Manifest.permission.C2D_MESSAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean){
                        } else {
                        }
                    }
                });
    }
}
