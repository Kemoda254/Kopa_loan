package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.applovin.mediation.ads.MaxAdView;
import com.facebook.BuildConfig;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.onesignal.OneSignal;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


import java.util.regex.Pattern;

import static android.service.controls.ControlsProviderService.TAG;

public class activity_login extends AppCompatActivity {
    TMBannerAdView adbanner;
    Toolbar mToolbar;
    TextView textView, textView2;
    Button btproceed;
    private MaxAdView adView;
    ProgressDialog dialog;
    private InterstitialAd mInterstitialAd;
    private static final String ONESIGNAL_APP_ID = "bd6e369e-5959-4f0c-88ba-3419e755a6bc";


    int i = 0;
    int x = 0;
    private EditText emailEditText;
    private EditText passEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        AdsManager.showBanner(activity_login.this);
        MobileAds.initialize(this, initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();
        adView = findViewById(R.id.maxbanner);
         AdsManager.showBanner(this);
        adView.loadAd();
        adView.startAutoRefresh();

        InterstitialAd.load(this,getString(R.string.ad_interstitial), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(activity_login.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(activity_login.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(activity_login.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(activity_login.this, STATUS.UNKNOWN);
////        adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());
        this.emailEditText = (EditText) findViewById(R.id.loanamount);
        this.passEditText = (EditText) findViewById(R.id.password);
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }

    public void registerMember(View arg0) {

        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please wait...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                activity_login.this.dialog.dismiss();
                Intent intent =
                        new Intent( activity_login.this, activity_register.class);
               // startActivity(intent);
                AdsManager.showLoadAppLovinAds(activity_login.this,intent);
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(activity_login.this);}
//                if (Tapdaq.getInstance().isVideoReady(activity_login.this,"default")) {
//                    Tapdaq.getInstance().showVideo(activity_login.this, "default", new InterstitialListener());
//                }
                  //  Intent intent= new Intent();
                   // intent.setClass(activity_login.this,activity_otpcode.class);


            }
        }, 1600);
    }
    public void checkLogin(View arg0) {

        String email = this.emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            this.emailEditText.setError("Invalid Email");
        }
        String pass = this.passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            this.passEditText.setError("Incorrect details try again");
        }
        if (isValidEmail(email) && isValidPassword(pass)) {
            this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Authenticating...", true);
            this.dialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    activity_login.this.dialog.dismiss();
                        Intent intent= new Intent();
                        intent.setClass(activity_login.this,Main2Activity.class);
                  //  startActivity(intent);
                        AdsManager.showLoadAppLovinAds(activity_login.this,intent);

                }
            }, 3000);
        }
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(activity_login.this,  "default",new InterstitialListener());
            // Ads may now be requested
        }

        @Override
        public void didFailToInitialise(TMAdError error) {
            super.didFailToInitialise(error);
            //Tapdaq failed to initialise
        }
    }
//    @Override
//    protected void onDestroy() {
//        if (adbanner != null) {
//            adbanner.destroy(MainActivity.this);
//        }
//        super.onDestroy();
//    }


    public class BannerListener extends TMAdListener {
        @Override
        public void didLoad() {
            // First banner loaded into view
        }

        @Override
        public void didFailToLoad(TMAdError error) {
            // No banners available. View will stop refreshing
        }

        @Override
        public void didRefresh() {
            // Subequent banner loaded, this view will refresh every 30 seconds
        }

        @Override
        public void didFailToRefresh(TMAdError error) {
            // Banner could not load, this view will attempt another refresh every 30 seconds
        }

        @Override
        public void didClick() {
            // User clicked on banner
        }

    }

    public class InterstitialListener extends TMAdListener {

        @Override
        public void didLoad() {
            // Ready to display the interstitial
            Tapdaq.getInstance().loadVideo(activity_login.this, "default", new InterstitialListener());
        }

    }
    private boolean isValidEmail(String email) {
        return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }
    private boolean isValidPassword(String pass) {
        if (pass == null || pass.length() < 4) {
            return false;
        }
        return true;
    }
}
