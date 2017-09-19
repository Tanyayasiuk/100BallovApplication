package com.example.studying.a100ballovapplication.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.studying.a100ballovapplication.NavDrawActivity;
import com.example.studying.a100ballovapplication.R;

import static com.example.studying.a100ballovapplication.BasicNotLoggedActivity.KEY_FRAGMENT;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbarLogin = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbarLogin);

        toolbarLogin.setNavigationIcon(R.drawable.ic_arrow_back);
        setTitle(getIntent().getStringExtra(KEY_FRAGMENT));

        toolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Button enterButton = (Button) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, NavDrawActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
