package com.example.studying.a100ballovapplication.news;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studying.a100ballovapplication.R;
import com.example.studying.domain.entity.EnrollDomain;
import com.example.studying.domain.entity.OkDomain;
import com.example.studying.domain.interaction.EnrollUseCase;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.HashMap;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;


public class NewsActivity extends AppCompatActivity {

    private EnrollUseCase useCase = new EnrollUseCase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Скачивать и показывать pdf
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://che.org.il/wp-content/uploads/2016/12/pdf-sample.pdf")));


        Button askButton = (Button) findViewById(R.id.ask_button);
        askButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxPermissions rxPermissions = new RxPermissions(NewsActivity.this);
                rxPermissions
                        .request(Manifest.permission.ACCESS_FINE_LOCATION)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean){
                                    Toast.makeText(NewsActivity.this, "Got it!", Toast.LENGTH_SHORT)
                                            .show();
                                } else {
                                    Toast.makeText(NewsActivity.this, "Access denied!", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });
            }
        });

        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();
                map.put("textmessage", "well, hello internet");
                //map.put("htmlmessage", "my html-try");
                String[] toto = new String[]{"100ballov.application@gmail.com"};

                EnrollDomain enrollDomain = new EnrollDomain();
                enrollDomain.setSubject("HelloWorld!");
                //enrollDomain.setTextmessage("This is my first try to send an email");
                enrollDomain.setBodyparts(map);
                enrollDomain.setTo(toto);

                useCase.execute(enrollDomain, new DisposableObserver<OkDomain>() {
                    @Override
                    public void onNext(@NonNull OkDomain okDomain) {
                        Toast.makeText(NewsActivity.this, "On Next after email sent", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("SSS", "OnNExt enroll useCase");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(NewsActivity.this, "Oh shit! An error occured!", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("SSS", "ERROR enroll useCase " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();
        useCase.dispose();
    }
}
