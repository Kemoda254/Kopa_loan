package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.facebook.BuildConfig;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;

public class termscnds extends AppCompatActivity {



    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.terms_conditions);

//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(termscnds.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(termscnds.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(termscnds.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(termscnds.this, STATUS.UNKNOWN);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    public void proceed(View arg0) {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please Wait...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                termscnds.this.dialog.dismiss();
                    Intent intent = new Intent(termscnds.this, firsttime.class);
                AdsManager.showLoadAppLovinAds(termscnds.this,intent);
//                    startActivity(intent);
//                if (Tapdaq.getInstance().isVideoReady(termscnds.this,"default")) {
//                    Tapdaq.getInstance().showVideo(termscnds.this, "default", new InterstitialListener());
//                }

            }
        }, 1500);

    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(termscnds.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(termscnds.this, "default", new InterstitialListener());
        }

    }
    public void cancel(View arg0) {

        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please Wait...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                termscnds.this.dialog.dismiss();
                termscnds.this.startActivity(new Intent(termscnds.this, Main2Activity.class));
            }
        }, 500);

    }
}
