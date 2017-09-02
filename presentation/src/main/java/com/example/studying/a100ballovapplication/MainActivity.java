package com.example.studying.a100ballovapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.studying.a100ballovapplication.about_us.AboutUsActivity;
import com.example.studying.a100ballovapplication.about_us.FragmentOne;
import com.example.studying.a100ballovapplication.contacts.ContactsActivity;
import com.example.studying.a100ballovapplication.contacts.ContactsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent newIntent = new Intent(MainActivity.this, BasicNotLoggedActivity.class);


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
                Intent intent = new Intent(MainActivity.this, NavDrawActivity.class);
                startActivity(intent);
            }
        });

        TextView registerButton = (TextView) findViewById(R.id.register_link);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Здесь будет ссылка на  страницу регистрации. А пока:
                Log.e("SSS", "Register - onClick");
            }
        });



    }
}
