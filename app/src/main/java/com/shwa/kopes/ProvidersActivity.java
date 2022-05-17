package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class ProvidersActivity extends AppCompatActivity {
    Toolbar toolbar;

    String TAG = "FBADS";

    int i = 0;
    int x = 0;
    int activity = 0;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_providers);
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(ProvidersActivity.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(ProvidersActivity.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(ProvidersActivity.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(ProvidersActivity.this, STATUS.UNKNOWN);
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle(getResources().getString(R.string.crb_prov));


        // Add the ad view to your activity layout
        AdsManager.showBanner(this);


    }

    public void next(View arg0) {

           Intent intent =  new Intent(this, Provide1Activity.class);
           startActivity(intent);
        if (Tapdaq.getInstance().isVideoReady(ProvidersActivity.this,"default")) {
            Tapdaq.getInstance().showVideo(ProvidersActivity.this, "default", new InterstitialListener());
        }
    }
    public void nextb(View arg0){
            Intent intent =  new Intent(this, Provider2Activity.class);
        AdsManager.showLoadAppLovinAds(this,intent);

    }
    public void nexta(View arg0) {
            Intent intent =  new Intent(this, Provide3Activity.class);
            startActivity(intent);
            if (Tapdaq.getInstance().isVideoReady(ProvidersActivity.this,"default")) {
            Tapdaq.getInstance().showVideo(ProvidersActivity.this, "default", new InterstitialListener());
        }


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(ProvidersActivity.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(ProvidersActivity.this, "default", new InterstitialListener());
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }

}
