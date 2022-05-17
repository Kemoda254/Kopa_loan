package com.shwa.kopes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class CRB_InfoActivity extends AppCompatActivity {
Toolbar toolbar;
    TextView textView;
    TMBannerAdView adbanner;
    int i = 0;
    int x = 0;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crb__info);
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(CRB_InfoActivity.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(CRB_InfoActivity.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(CRB_InfoActivity.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(CRB_InfoActivity.this, STATUS.UNKNOWN);
//                adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle(getResources().getString(R.string.crb_info));
        textView=(TextView)findViewById(R.id.tvCountry);
        textView.setText(getResources().getString(R.string.crb_detaik));


        // Add the ad view to your activity layout
        AdsManager.showBanner(this);
        AdsManager.showLoadAppLovinAds(this,null);
//        if (Tapdaq.getInstance().isVideoReady(CRB_InfoActivity.this,"default")) {
//            Tapdaq.getInstance().showVideo(CRB_InfoActivity.this, "default", new InterstitialListener());
//        }


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(CRB_InfoActivity.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(CRB_InfoActivity.this, "default", new InterstitialListener());
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }

}
