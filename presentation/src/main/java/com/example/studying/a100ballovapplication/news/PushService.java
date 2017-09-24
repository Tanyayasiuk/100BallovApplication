
package com.example.studying.a100ballovapplication.news;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.backendless.messaging.PublishOptions;
import com.backendless.push.BackendlessPushService;
import com.example.studying.a100ballovapplication.NavDrawActivity;
import com.example.studying.a100ballovapplication.R;

import static com.example.studying.a100ballovapplication.base.Defaults.KEY_FRAGMENT;

public class PushService extends BackendlessPushService {

    @Override
    public boolean onMessage( Context context, Intent intent ) {
        CharSequence tickerText = intent.getStringExtra( PublishOptions.ANDROID_TICKER_TEXT_TAG );
        CharSequence contentTitle = intent.getStringExtra( PublishOptions.ANDROID_CONTENT_TITLE_TAG );
        CharSequence contentText = intent.getStringExtra( PublishOptions.ANDROID_CONTENT_TEXT_TAG );
        String subtopic = intent.getStringExtra( "message" );
        Log.e("SSS", "PUSH Service working! " + subtopic);

        if( tickerText != null && tickerText.length() > 0 ){

            int appIcon = context.getApplicationInfo().icon;
            if( appIcon == 0 ) appIcon = android.R.drawable.sym_def_app_icon;

            Intent notificationIntent = new Intent( context, NavDrawActivity.class );
            notificationIntent.putExtra( KEY_FRAGMENT, String.valueOf(R.string.news_item));
            PendingIntent contentIntent = PendingIntent.getActivity( context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT );

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder( context );
            notificationBuilder.setSmallIcon( appIcon );
            notificationBuilder.setTicker( tickerText );
            notificationBuilder.setWhen( System.currentTimeMillis() );
            notificationBuilder.setContentTitle(contentTitle);
            notificationBuilder.setContentText(contentText);
            notificationBuilder.setAutoCancel( true );
            notificationBuilder.setContentIntent( contentIntent);

            Notification notification = notificationBuilder.build();

            NotificationManager notificationManager = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE );
            notificationManager.notify( 0, notification);
        }

    return false;
  }
}
                                            