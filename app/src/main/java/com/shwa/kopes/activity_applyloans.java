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

import com.applovin.mediation.ads.MaxAdView;
import com.facebook.BuildConfig;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class activity_applyloans extends AppCompatActivity {
    private Button button1;
    ProgressDialog dialog;
    Toolbar mToolbar;
    TextView textView, textView2;
    private EditText emailEditText;
    private MaxAdView adView;
    Button btproceed;
    TMBannerAdView adbanner;
    private EditText prd_;



    int i = 0;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applyloans);
        adView = findViewById(R.id.maxbanner);

        adView.loadAd();
        adView.startAutoRefresh();
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(activity_applyloans.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(activity_applyloans.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(activity_applyloans.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(activity_applyloans.this, STATUS.UNKNOWN);
//        adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());

        btproceed = (Button) findViewById(R.id.proceed);

       // LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        AdsManager.showBanner(this);


        this.emailEditText = (EditText) findViewById(R.id.name);
        this.button1 = (Button) findViewById(R.id.request_loan);

        this.button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity_applyloans.this._checkLogin();
              //  AdsManager.showLoadAppLovinAds(activity_applyloans.this,null);
//                if (Tapdaq.getInstance().isVideoReady(activity_applyloans.this,"default")) {
//                    Tapdaq.getInstance().showVideo(activity_applyloans.this, "default", new InterstitialListener());
//                }
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }
    public void _checkLogin() {

        String email = this.emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            this.emailEditText.setError("Request atleast Ksh.500");
        }


        if (isValidEmail(email) ) {
            this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Requesting Loan...", true);
            this.dialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    activity_applyloans.this.dialog.dismiss();
                    activity_applyloans.this.getting();
                }
            }, 3000);
        }
    }

    public void getting() {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please wait just a moment...", true);

        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                activity_applyloans.this.dialog.dismiss();
                activity_applyloans.this.fdback();
            }
        }, 1000);
    }

    public void fdback() {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Request Submitted...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                activity_applyloans.this.dialog.dismiss();
                Intent intent = new Intent(activity_applyloans.this, till_mpesa.class);
                AdsManager.showLoadAppLovinAds(activity_applyloans.this,intent);
                //startActivity(intent);
//                if (Tapdaq.getInstance().isVideoReady(activity_applyloans.this,"default")) {
//                    Tapdaq.getInstance().showVideo(activity_applyloans.this, "default", new InterstitialListener());
//                }

            }
        }, 2000);
    }

    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(activity_applyloans.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(activity_applyloans.this, "default", new InterstitialListener());
        }

    }


    private boolean isValidEmail(String email) {
        if (email == null || email.length() < 3) {
            return false;
        }
        return true;
    }
}
