package com.example.studying.a100ballovapplication.news;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.Subscription;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.messaging.Message;
import com.backendless.messaging.MessageStatus;
import com.backendless.messaging.PublishOptions;
import com.backendless.messaging.PublishStatusEnum;
import com.backendless.messaging.SubscriptionOptions;
import com.example.studying.a100ballovapplication.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.functions.Consumer;


public class NewsActivity extends AppCompatActivity {

   /* private EditText history;
    private EditText messageField;

    private PublishOptions publishOptions;
    private SubscriptionOptions subscriptionOptions;
    private Subscription subscription;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initUI();
        Backendless.initApp( this, Defaults.APPLICATION_ID, Defaults.API_KEY );
        publishOptions = new PublishOptions();
        publishOptions.setPublisherId( ChatUser.currentUser().getNickname() );

        subscriptionOptions = new SubscriptionOptions();


        Backendless.Messaging.subscribe( Defaults.DEFAULT_CHANNEL, new AsyncCallback<List<Message>>()
        {
            @Override
            public void handleResponse( List<Message> response )
            {
                onReceiveMessage( response );
            }

            @Override
            public void handleFault( BackendlessFault fault )
            {
                Toast.makeText( NewsActivity.this, fault.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        }, subscriptionOptions, new DefaultCallback<Subscription>( NewsActivity.this )
        {
            @Override
            public void handleResponse( Subscription response )
            {
                super.handleResponse( response );
                subscription = response;
            }
        } );
    }

    private void initUI()
    {
        history = (EditText) findViewById( R.id.historyField );
        messageField = (EditText) findViewById( R.id.messageField );
        SharedPreferences settings = getSharedPreferences( "com.backendless.settings", Context.MODE_PRIVATE );
        settings.edit().putString("APPLICATION_ID", "33732170-B3D2-FFA4-FFD4-AF51778ED800").apply();

        ChatUser.currentUser().setNickname( "vanya" );
        ChatUser.currentUser().setDeviceId( "2128506" );
        ChatUser.currentUser().setObjectId("33732170-B3D2-FFA4-FFD4-AF51778ED800");


        messageField.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View view, int keyCode, KeyEvent keyEvent )
            {
                return onSendMessage( keyCode, keyEvent );
            }
        } );
    }

    private void onReceiveMessage( List<Message> messages )
    {

            String messageN = messages.iterator().next().getData().toString();
            Toast.makeText( this, messageN, Toast.LENGTH_SHORT ).show();


        for( Message message : messages )
        {
            history.setText( history.getText() + "\n" + message.getPublisherId() + ": " + message.getData() );
        }
    }

    private boolean onSendMessage( int keyCode, KeyEvent keyEvent )
    {
        if( keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP )
        {
            String message = messageField.getText().toString();

            if( message == null || message.equals( "" ) )
                return true;

            Backendless.Messaging.publish( (Object) message, publishOptions, new DefaultCallback<MessageStatus>( NewsActivity.this )
            {
                @Override
                public void handleResponse( MessageStatus response )
                {
                    super.handleResponse( response );

                    PublishStatusEnum messageStatus = response.getStatus();

                    if( messageStatus == PublishStatusEnum.SCHEDULED )
                    {
                        messageField.setText( "" );
                    }
                    else
                    {
                        Toast.makeText( NewsActivity.this, "Message status: " + messageStatus.toString(), Toast.LENGTH_SHORT );
                    }
                }
            } );

            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        if( subscription != null )
            subscription.cancelSubscription();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if( subscription != null )
            subscription.resumeSubscription();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if( subscription != null )
            subscription.pauseSubscription();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() ==  MotionEvent.ACTION_DOWN) hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }*/
}
