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

import com.tapdaq.sdk.STATUS;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.TapdaqConfig;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


public class MpesaActivity extends AppCompatActivity {
    ProgressDialog dialog;
    Toolbar mToolbar;
    TextView textView, textView2;
    Button btproceed;

    private Button button1;
    private EditText passEditText;

    String TAG = "FBADS";

    int i = 0;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpesa);
        TapdaqConfig config = Tapdaq.getInstance().config();

        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions

        Tapdaq.getInstance().initialize(MpesaActivity.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
        Tapdaq.getInstance().setUserSubjectToGDPR(MpesaActivity.this, STATUS.TRUE);
        Tapdaq.getInstance().setConsentGiven(MpesaActivity.this, STATUS.FALSE);
        Tapdaq.getInstance().setIsAgeRestrictedUser(MpesaActivity.this, STATUS.UNKNOWN);


        AdsManager.showBanner(this);


        this.button1 = (Button) findViewById(R.id.button1);
        this.passEditText = (EditText) findViewById(R.id.reason);
        this.button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MpesaActivity.this._checkLogin();
            }
        });
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }
    public void _checkLogin() {
        String phn = this.passEditText.getText().toString();
        if (!isValidPhone(phn)) {
            this.passEditText.setError("Use short description!");
        }


        if (isValidPhone(phn)){
            this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Recording data...", true);
            this.dialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    MpesaActivity.this.dialog.dismiss();
                    MpesaActivity.this.getting();
                }
            }, 2000);
        }
    }

    public void getting() {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Data saved...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MpesaActivity.this.dialog.dismiss();
                MpesaActivity.this.fdback();
            }
        }, 2000);
    }

    public void fdback() {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Please wait as we finish...", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MpesaActivity.this.dialog.dismiss();
                    Intent intent= new Intent();
                    intent.setClass(MpesaActivity.this,activity_applyloans.class);
                    AdsManager.showLoadAppLovinAds(MpesaActivity.this,intent);
//                if (Tapdaq.getInstance().isVideoReady(MpesaActivity.this,"default")) {
//                    Tapdaq.getInstance().showVideo(MpesaActivity.this, "default", new InterstitialListener());
//                }


            }
        }, 2000);
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(MpesaActivity.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(MpesaActivity.this, "default", new InterstitialListener());
        }

    }
    private boolean isValidPhone(String phn) {
        if (phn == null || phn.length() < 4 || phn.length() > 15) {
            return false;
        }
        return true;
    }
}
