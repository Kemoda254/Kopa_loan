package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.view.View;

import com.applovin.mediation.ads.MaxAdView;
import com.facebook.BuildConfig;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class activity_getstarted extends AppCompatActivity {
    private MaxAdView adView;


    int i = 0;
    int x = 0;
    ProgressDialog dialog;
    TMBannerAdView adbanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);

        // Add the ad view to your activity layout
        AdsManager.showBanner(this);
        adView = findViewById(R.id.maxbanner);
        adView.setVisibility( View.VISIBLE );
        adView.startAutoRefresh();

//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(activity_getstarted.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(activity_getstarted.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(activity_getstarted.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(activity_getstarted.this, STATUS.UNKNOWN);
//        adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());
        AdsManager.showLoadAppLovinAds(this,null);

        }

    public void next(View arg0) {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please Wait...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                activity_getstarted.this.dialog.dismiss();
                    Intent intent= new Intent();
                    intent.setClass(activity_getstarted.this,activity_acceptstart.class);
                AdsManager.showLoadAppLovinAds(activity_getstarted.this,intent);
                    //tartActivity(intent);
//                if (Tapdaq.getInstance().isVideoReady(activity_getstarted.this,"default")) {
//                    Tapdaq.getInstance().showVideo(activity_getstarted.this, "default", new InterstitialListener());
//                }

            }
        }, 1900);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(activity_getstarted.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(activity_getstarted.this, "default", new InterstitialListener());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);

    }
}







