package com.example.wilian.jogovelha;

import android.app.Application;
import android.widget.TextView;

import com.facebook.AccessTokenTracker;
import com.facebook.FacebookSdk;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Andreatto on 23/11/16.
 */

public class FacebookData extends Application {

    AccessTokenTracker accessTokenTracker;
    ProfileTracker profileTracker;
    TextView userName;

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize( getApplicationContext() );
        AppEventsLogger.activateApp( this );

    }

}
