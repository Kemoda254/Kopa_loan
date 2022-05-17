package com.shwa.kopes;

import android.app.Application;
import android.widget.Toast;

import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.facebook.ads.AdSettings;
import com.onesignal.OneSignal;
import com.facebook.FacebookSdk;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.appevents.AppEventsLogger;


public class app extends Application {
    private static final String ONESIGNAL_APP_ID = "8f0fa981-6405-4b53-a669-f55472981fb7";
    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        AdSettings.addTestDevice("c8d68f9c-6e4e-4529-8883-a9fa81515752");
        AudienceNetworkAds.initialize(this);


        AppLovinSdk.getInstance(this).setMediationProvider("max");
        AppLovinSdk.initializeSdk(this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(AppLovinSdkConfiguration config) {
                Toast.makeText(app.this, "Welcome", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
