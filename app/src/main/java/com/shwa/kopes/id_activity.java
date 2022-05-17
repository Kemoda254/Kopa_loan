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
import androidx.multidex.BuildConfig;

import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class id_activity extends AppCompatActivity {
    ProgressDialog dialog;
    Toolbar mToolbar;
    TextView textView, textView2;
    Button btproceed;

    private EditText nd_;
    private EditText uphone;

    String TAG = "FBADS";

    int i = 0;
    int x = 0;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id);
       //` AdsManager.showLoadAppLovinAds(this,null);
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(id_activity.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(id_activity.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(id_activity.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(id_activity.this, STATUS.UNKNOWN);

        mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        textView = (TextView) findViewById(R.id.tvCountry);
        btproceed = (Button) findViewById(R.id.proceed);

        AdsManager.showBanner(this);

        this.button1 = (Button) findViewById(R.id.request_loan);
        this.uphone = (EditText) findViewById(R.id.phone);
        this.nd_ = (EditText) findViewById(R.id.nd);
        this.button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                id_activity.this._checkLogin();
            }
        });
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }
    public void _checkLogin() {
        String phn = this.uphone.getText().toString();
        if (!isValidPhone(phn)) {
            this.uphone.setError("Incorrect Mobile number!");
        }

        String nd = this.nd_.getText().toString();
        if (!isValidID(nd)) {
            this.nd_.setError("Invalid National ID!");
        }


        if (isValidPhone(phn)  && isValidID(nd)) {
            this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Verifying details...", true);
            this.dialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    id_activity.this.dialog.dismiss();
                    id_activity.this.fdback();
                }
            }, 2000);
        }
    }


    public void fdback() {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please wait just a moment...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                id_activity.this.dialog.dismiss();

                    Intent intent = new Intent( id_activity.this, MpesaActivity.class);
        AdsManager.showLoadAppLovinAds(id_activity.this,intent);
//                if (Tapdaq.getInstance().isVideoReady(id_activity.this,"default")) {
//                    Tapdaq.getInstance().showVideo(id_activity.this, "default", new InterstitialListener());
//                }
                }

        }, 2000);
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(id_activity.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(id_activity.this, "default", new InterstitialListener());
        }

    }
    private boolean isValidPhone(String phn) {
        if (phn == null || phn.length() < 10 || phn.length() > 15) {
            return false;
        }
        return true;
    }

    private boolean isValidID(String nd) {
        if (nd == null || nd.length() < 1 || nd.length() > 20) {
            return false;
        }
        return true;
    }
}
