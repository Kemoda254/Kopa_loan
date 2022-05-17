package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.multidex.BuildConfig;

import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class loginreg extends AppCompatActivity {

    Toolbar mToolbar;
    TextView textView, textView2;
    Button btproceed;

    ProgressDialog dialog;

    String TAG = "FBADS";

    int activity = 0;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logreg);

        AdsManager.showBanner(this);

    }

    public void login(View arg0) {

        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please Wait...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginreg.this.dialog.dismiss();

                    Intent intent= new Intent();
                    intent.setClass(loginreg.this,activity_login.class);
               // startActivity(intent);
                    AdsManager.showLoadAppLovinAds(loginreg.this,intent);
//                if (Tapdaq.getInstance().isVideoReady(loginreg.this,"default")) {
//                    Tapdaq.getInstance().showVideo(loginreg.this, "default", new InterstitialListener());
//                }

                }

        }, 2750);

    }

    public void register(View arg0) {

        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please Wait...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginreg.this.dialog.dismiss();

                    Intent intent= new Intent();
                    intent.setClass(loginreg.this,activity_otpcode.class);
                    AdsManager.showLoadAppLovinAds(loginreg.this,intent);

                }

        }, 2800);

    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(loginreg.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(loginreg.this, "default", new InterstitialListener());
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }
}
