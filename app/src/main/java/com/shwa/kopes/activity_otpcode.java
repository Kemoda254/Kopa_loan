package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.BuildConfig;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class activity_otpcode extends AppCompatActivity {

    Toolbar mToolbar;
    TextView textView, textView2;
    Button btproceed;
    ProgressDialog dialog;

    TMBannerAdView adbanner;
    int i = 0;
    int x = 0;
    private EditText emailEditText;
    private EditText passEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpcode);
        AdsManager.showBanner(this);

        this.emailEditText = (EditText) findViewById(R.id.loanamount);
        this.passEditText = (EditText) findViewById(R.id.password);

//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(activity_otpcode.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(activity_otpcode.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(activity_otpcode.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(activity_otpcode.this, STATUS.UNKNOWN);
//        adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }


    public void requestotp(View arg0) {

        String email = this.emailEditText.getText().toString();
        if (!isValidPhone(email)) {
            this.emailEditText.setError("Incorrect Mobile number!");
        }
        if (isValidPhone(email)) {
            this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Requesting OTP..", true);
            this.dialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    activity_otpcode.this.dialog.dismiss();
                    passEditText.setText("687209");
                }
            }, 2000);
        }

    }

    private boolean isValidPhone(String phn) {
        if (phn == null || phn.length() < 10 || phn.length() > 15) {
            return false;
        }
        return true;
    }

    private boolean isValidOTP(String email) {
        if (email == null || email.length() < 4) {
            return false;
        }
        return true;
    }


    public void next(View arg0) {

        String otp = this.passEditText.getText().toString();
        if (!isValidOTP(otp)) {
            this.emailEditText.setError("Request OTP Code!");
        }

        if (isValidOTP(otp) ){
            this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please wait...", true);
            this.dialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    activity_otpcode.this.dialog.dismiss();

                        Intent intent= new Intent();
                        intent.setClass(activity_otpcode.this,activity_register.class);
                    //startActivity(intent);
                        AdsManager.showLoadAppLovinAds(activity_otpcode.this,intent);
//                    if (Tapdaq.getInstance().isVideoReady(activity_otpcode.this,"default")) {
//                        Tapdaq.getInstance().showVideo(activity_otpcode.this, "default", new InterstitialListener());
//                    }

                }
            }, 1600);

        }
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(activity_otpcode.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(activity_otpcode.this, "default", new InterstitialListener());
        }

    }
}
