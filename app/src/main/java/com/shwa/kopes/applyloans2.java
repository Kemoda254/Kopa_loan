package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.BuildConfig;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class applyloans2 extends AppCompatActivity {
    private CheckBox checkBox;
    boolean ckd = true;
    ProgressDialog dialog;
    TMBannerAdView adbanner;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_applyloans2);
        AdsManager.showBanner(this);
//        AdsManager.showLoadAppLovinAds(this,null);
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(applyloans2.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(applyloans2.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(applyloans2.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(applyloans2.this, STATUS.UNKNOWN);
////        adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());

    }

    public void next2(View arg0) {

        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Saving .. Please Wait...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();

                    Intent intent= new Intent();
                    intent.setClass(applyloans2.this,applyloans3.class);
                AdsManager.showLoadAppLovinAds(applyloans2.this,intent);
                //startActivity(intent);
//                    if (Tapdaq.getInstance().isVideoReady(applyloans2.this,"default")) {
//                    Tapdaq.getInstance().showVideo(applyloans2.this, "default", new InterstitialListener());
//                }

            }
        }, 1500);

    }
    public void back(View arg0) {
        startActivity(new Intent(this, com.shwa.kopes.applyloans1.class));
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(applyloans2.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(applyloans2.this, "default", new InterstitialListener());
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        AdsManager.dismissInters(this);

    }
}
