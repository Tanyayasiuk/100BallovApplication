package com.example.studying.a100ballovapplication;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.example.studying.a100ballovapplication.login.LoginActivity;
import com.example.studying.a100ballovapplication.news.NewsActivity;
import com.example.studying.a100ballovapplication.registration.RegistrationActivity;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        setContentView(R.layout.activity_main);
        final Intent newIntent = new Intent(MainActivity.this, BasicNotLoggedActivity.class);

        final Button askButton = (Button) findViewById(R.id.ask_button);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                //newIntent.putExtra(BasicNotLoggedActivity.KEY_FRAGMENT, askButton.getText());
                startActivity(intent);
            }
        });

        final Button aboutUsButton = (Button) findViewById(R.id.about_us_button);
        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(BasicNotLoggedActivity.KEY_FRAGMENT, aboutUsButton.getText());
                startActivity(newIntent);
            }
        });

        final Button scheduleButton = (Button) findViewById(R.id.schedule_button);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(BasicNotLoggedActivity.KEY_FRAGMENT, scheduleButton.getText());
                startActivity(newIntent);
                Log.e("SSS", "Schedule - onClick");
            }
        });

        final Button enrollButton = (Button) findViewById(R.id.enroll_button);
        enrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(BasicNotLoggedActivity.KEY_FRAGMENT,enrollButton.getText());
                startActivity(newIntent);
                Log.e("SSS", "Enroll - onClick");
            }
        });

        final Button contactsButton = (Button) findViewById(R.id.contacts_button);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIntent.putExtra(BasicNotLoggedActivity.KEY_FRAGMENT, contactsButton.getText());
                startActivity(newIntent);
                Log.e("SSS", "Contacts - onClick");
            }
        });

        final Button enterButton = (Button) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra(BasicNotLoggedActivity.KEY_FRAGMENT, enterButton.getText());
                startActivity(intent);

                Log.e("SSS", "Enter - onClick");
            }
        });

        final TextView registerButton = (TextView) findViewById(R.id.register_link);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                intent.putExtra(BasicNotLoggedActivity.KEY_FRAGMENT, registerButton.getText());
                startActivity(intent);
                Log.e("SSS", "Register - onClick");
            }
        });


    }

    public void forceCrash(View view) {
        throw new RuntimeException("This is a crash");
    }
}
