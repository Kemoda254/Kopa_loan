package com.shwa.kopes;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.multidex.BuildConfig;

import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class StatusActivity extends AppCompatActivity {
    public static final String DEFAULT = "N/A";
    public TextView tex2;

    private Context context;
    ProgressDialog dialog;

    int i = 0;
    int x = 0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(StatusActivity.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(StatusActivity.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(StatusActivity.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(StatusActivity.this, STATUS.UNKNOWN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);

        context = this.getApplicationContext();
        LinearLayout linearLayout = findViewById(R.id.applied);
        Button applynow  =  findViewById(R.id.apply);

        SharedPreferences prefs = getSharedPreferences("apply", MODE_PRIVATE);
        String name = prefs.getString("apply", "");//"No name defined" is the default value.

        if(name.isEmpty())
        {
            applynow.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }
        else {
            applynow.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }

        // Add the ad view to your activity layout
        AdsManager.showBanner(this);

    }
    public void back(View arg0) {
        StatusActivity.this.startActivity(new Intent(StatusActivity.this, Main2Activity.class));
//        if (Tapdaq.getInstance().isVideoReady(StatusActivity.this,"default")) {
//            Tapdaq.getInstance().showVideo(StatusActivity.this, "default", new InterstitialListener());
//        }
    }
    public void apply(View arg0) {
        StatusActivity.this.startActivity(new Intent(StatusActivity.this, Main2Activity.class));
//        if (Tapdaq.getInstance().isVideoReady(StatusActivity.this,"default")) {
//            Tapdaq.getInstance().showVideo(StatusActivity.this, "default", new InterstitialListener());
//        }
    }

    public void reapply(View arg0) {
        dialog = ProgressDialog.show(StatusActivity.this, BuildConfig.FLAVOR, "Loading...", true);
        dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                StatusActivity.this.dialog.dismiss();
                Intent intent = new Intent(StatusActivity.this,termscnds.class);
                startActivity(intent);
                AdsManager.showLoadAppLovinAds(StatusActivity.this,intent);
//                if (Tapdaq.getInstance().isVideoReady(StatusActivity.this,"default")) {
//                    Tapdaq.getInstance().showVideo(StatusActivity.this, "default", new InterstitialListener());
//                }

            }
        }, 3000);

    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(StatusActivity.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(StatusActivity.this, "default", new InterstitialListener());
        }

    }
}
