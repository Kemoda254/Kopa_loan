package com.shwa.kopes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;



public class Provider2Activity extends AppCompatActivity {
    Toolbar toolbar;

    TextView textView;

    int i = 0;
    int x = 0;

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider2);

        toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle(getResources().getString(R.string.crb_prov2));
        textView=(TextView)findViewById(R.id.tvCountry);
        textView.setText(getResources().getString(R.string.crbb_prov2));



        // Add the ad view to your activity layout
        AdsManager.showBanner(this);

        AdsManager.showLoadAppLovinAds(this,null);
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissBanner(this);
    }

}
