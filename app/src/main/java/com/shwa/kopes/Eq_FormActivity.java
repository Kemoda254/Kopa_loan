package com.shwa.kopes;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.facebook.BuildConfig;
import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class Eq_FormActivity extends AppCompatActivity implements Spinner.OnItemSelectedListener {
    ArrayAdapter adapter;
    Spinner grades;
    TextView textView;
    ProgressDialog dialog;
    Toolbar toolbar;

    int i = 0;
    int x = 0;
    TMBannerAdView adbanner;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eq__form);
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(Eq_FormActivity.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(Eq_FormActivity.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(Eq_FormActivity.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(Eq_FormActivity.this, STATUS.UNKNOWN);
//        adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());
        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle(getResources().getString(R.string.crb_form));

        grades = (Spinner)findViewById(R.id.spinnerCountries);
        textView = (TextView) findViewById(R.id.editTextPhone);

        adapter = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);

        grades.setAdapter(adapter);
        grades.setOnItemSelectedListener(Eq_FormActivity .this);


        // Add the ad view to your activity layout
        AdsManager.showBanner(this);

        AdsManager.showLoadAppLovinAds(this,null);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView spinner_text = (TextView) view;


        textView.setText(grades.getSelectedItem().toString());
    }
    public void next(View arg0) {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "submitting message...",true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Eq_FormActivity.this.dialog.dismiss();
                Toast tt = Toast.makeText(Eq_FormActivity.this,"Message Submitted Successfully...Will Get Back to You Soon..", Toast.LENGTH_LONG);
                tt.setGravity(Gravity.CENTER, 0, 0);
                tt.show();

//                if (Tapdaq.getInstance().isVideoReady(Eq_FormActivity.this,"default")) {
//                    Tapdaq.getInstance().showVideo(Eq_FormActivity.this, "default", new InterstitialListener());
//                }
            }
        }, 4000);
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(Eq_FormActivity.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(Eq_FormActivity.this, "default", new InterstitialListener());
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }

}
