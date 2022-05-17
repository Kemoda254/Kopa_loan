package com.shwa.kopes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.tapdaq.sdk.TMBannerAdView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;

import java.util.regex.Pattern;

public class activity_register extends AppCompatActivity {

    Toolbar mToolbar;
    TextView textView, textView2;
    Button btproceed;
    private EditText passEditText;
    private TextView txtVw;
    private EditText uname;
    private EditText uphone;
    private CheckBox checkBox;
    boolean ckd = true;
    ProgressDialog dialog;
    private EditText emailEditText;
    TMBannerAdView adbanner;


    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        this.emailEditText = (EditText) findViewById(R.id.loanamount);
        this.passEditText = (EditText) findViewById(R.id.password);
        this.uname = (EditText) findViewById(R.id.name);
        this.uphone = (EditText) findViewById(R.id.phone);
        this.checkBox = (CheckBox) findViewById(R.id.checkbox_id);

//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(activity_register.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(activity_register.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(activity_register.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(activity_register.this, STATUS.UNKNOWN);
//        adbanner = (TMBannerAdView) findViewById(R.id.adBanner);
//        adbanner.load(this, TMBannerAdSizes.STANDARD, new TMAdListener()); // Load banner with predefined size using default placement
//        adbanner.load (this, "banner", TMBannerAdSizes.STANDARD, new TMAdListener());
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }

    public void readMore(View arg0) {
        startActivity(new Intent(this, terms.class));
    }

    public void checkLogin(View arg0) {
        String email = this.emailEditText.getText().toString();
        if (!isValidEmail(email)) {
            this.emailEditText.setError("Invalid Email!");
        }
        String pass = this.passEditText.getText().toString();
        if (!isValidPassword(pass)) {
            this.passEditText.setError("Weak Password!");
        }

        String un = this.uname.getText().toString();
        if (!isValidPassword(un)) {
            this.uname.setError("Incomplete Name!");
        }
        if (!chkBox()) {
            this.checkBox.setError("Agree to our terms!");
        }
        if (isValidEmail(email) && isValidPassword(pass) && chkBox()  && isValidName(un)) {
            this.dialog = ProgressDialog.show(this, "Registering...","" ,true);
            this.dialog.show();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    activity_register.this.dialog.dismiss();

                        Intent intent= new Intent();
                        intent.setClass(activity_register.this,Main2Activity.class);
                    //startActivity(intent);
                        AdsManager.showLoadAppLovinAds(activity_register.this,intent);
//                    if (Tapdaq.getInstance().isVideoReady(activity_register.this,"default")) {
//                        Tapdaq.getInstance().showVideo(activity_register.this, "default", new InterstitialListener());
//                    }

                }
            }, 3000);
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(email).matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass == null || pass.length() < 4) {
            return false;
        }
        return true;
    }



    private boolean isValidName(String un) {
        if (un == null || un.length() < 4) {
            return false;
        }
        return true;
    }

    public boolean chkBox() {
        if (this.checkBox.isChecked()) {
            this.ckd = true;
        }
        if (!this.checkBox.isChecked()) {
            this.ckd = false;
        }
        return this.ckd;
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(activity_register.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(activity_register.this, "default", new InterstitialListener());
        }

    }

}
