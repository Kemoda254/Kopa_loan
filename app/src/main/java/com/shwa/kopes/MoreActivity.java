package com.shwa.kopes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


public class MoreActivity extends AppCompatActivity {

    int i = 0;
    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        AdsManager.showLoadAppLovinAds(this,null);

        // Add the ad view to your activity layout
        AdsManager.showBanner(this);

        // Request an ad

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
