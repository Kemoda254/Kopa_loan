package com.shwa.kopes;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.facebook.BuildConfig;

import com.google.android.material.navigation.NavigationView;
import com.tapdaq.sdk.Tapdaq;
import com.tapdaq.sdk.common.TMAdError;
import com.tapdaq.sdk.listeners.TMAdListener;
import com.tapdaq.sdk.listeners.TMInitListener;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    ImageButton imageButton;
    AlertDialog.Builder builder;
    ProgressDialog dialog;

    String TAG = "FBADS";
    String countryName = "";
    int activity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        TapdaqConfig config = Tapdaq.getInstance().config();
//
//        config.setUserSubjectToGdprStatus(STATUS.TRUE); //GDPR declare if user is in EU
//        config.setConsentStatus(STATUS.TRUE); //GDPR consent must be obtained from the user
//        config.setAgeRestrictedUserStatus(STATUS.FALSE); //Is user subject to COPPA or GDPR age restrictions

//        Tapdaq.getInstance().initialize(Main2Activity.this, getResources().getString(R.string.applicationid), getResources().getString(R.string.appkey), config, new TapdaqInitListener());
//        Tapdaq.getInstance().setUserSubjectToGDPR(Main2Activity.this, STATUS.TRUE);
//        Tapdaq.getInstance().setConsentGiven(Main2Activity.this, STATUS.FALSE);
//        Tapdaq.getInstance().setIsAgeRestrictedUser(Main2Activity.this, STATUS.UNKNOWN);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));

        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        final Button button = findViewById(R.id.button_apply);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.dialog = ProgressDialog.show(Main2Activity.this,"","Please wait...",true);
                Main2Activity.this.dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Main2Activity.this.dialog.dismiss();

                            Intent intent= new Intent();

                            intent.setClass(Main2Activity.this,termscnds.class);
                       // startActivity(intent);
                            AdsManager.showLoadAppLovinAds(Main2Activity.this,intent);
//                        if (Tapdaq.getInstance().isVideoReady(Main2Activity.this,"default")) {
//                            Tapdaq.getInstance().showVideo(Main2Activity.this, "default", new InterstitialListener());
//                        }

                    }
                }, 2100);



            }
        });


        final Button buttonstatus = findViewById(R.id.statuss);
        buttonstatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Main2Activity.this.dialog = ProgressDialog.show(Main2Activity.this,  "Please Wait...", "",true);
                Main2Activity.this.dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Main2Activity.this.dialog.dismiss();

                            Intent intent= new Intent();

                            intent.setClass(Main2Activity.this,StatusActivity.class);
                        //startActivity(intent);
                            AdsManager.showLoadAppLovinAds(Main2Activity.this,intent);

                    }
                }, 1800);



            }
        });

    }
    public void onBackPressed(){
        this.builder = new AlertDialog.Builder(this, 3);
        this.builder.setTitle(getString(R.string.app_name));
        this.builder.setMessage("Please rate us well. Thank you");
        this.builder.setNegativeButton("RATE APP", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                try{

                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse("market://details?id=" + Main2Activity.this.getPackageName()));
                    AdsManager.showLoadAppLovinAds(Main2Activity.this,intent);

                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + Main2Activity.this.getPackageName())));
                }

                Toast.makeText(Main2Activity.this, "Thank you for your Rating", Toast.LENGTH_SHORT).show();
            }
        });
        this.builder.setPositiveButton("QUIT APP", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               {
                   Intent a = new Intent(Intent.ACTION_MAIN);
                   a.addCategory(Intent.CATEGORY_HOME);
                   a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(a);
                }
            }
        });
        this.builder.create().show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main2_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Main2Activity.this.startActivity(new Intent( Main2Activity.this, Main2Activity.class));
            // Handle the camera action
        } else if (id == R.id.nav_CRB_Info) {



                this.dialog = ProgressDialog.show(this, BuildConfig.FLAVOR, "just a moment...", true);
                this.dialog.show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Main2Activity.this.dialog.dismiss();
                        Intent intent = new Intent( Main2Activity.this, CRB_InfoActivity.class);
                        AdsManager.showLoadAppLovinAds(Main2Activity.this,intent);

                    }
                }, 2000);



        } else if (id == R.id.nav_CRB_providers) {
            Main2Activity.this.startActivity(new Intent( Main2Activity.this, ProvidersActivity.class));


        }
        else if (id == R.id.nav_Enquiries_Form) {

                Intent intent = new Intent( Main2Activity.this, Eq_FormActivity.class);
            AdsManager.showLoadAppLovinAds(Main2Activity.this,intent);


        }
        else if (id == R.id.nav_About_Us) {

                Intent intent = new Intent( Main2Activity.this, MoreActivity.class);
            AdsManager.showLoadAppLovinAds(Main2Activity.this,intent);



        }
        else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Download this " + getString(R.string.app_name) + " loanguide and enjoy https://play.google.com/store/apps/details?id="+getPackageName());
            AdsManager.showLoadAppLovinAds(this,intent);

        } else if (id == R.id.nav_send) {

            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
            }
            catch (ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
            }

            Toast.makeText(this, "Thank you for your Rating", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class TapdaqInitListener extends TMInitListener {

        public void didInitialise() {
            super.didInitialise();
            //Tapdaq.getInstance().loadVideo(<CurrentActivity>,  "my_video_tag", new VideoListener());
            Tapdaq.getInstance().loadVideo(Main2Activity.this,  "default",new InterstitialListener());
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
            Tapdaq.getInstance().loadVideo(Main2Activity.this, "default", new InterstitialListener());
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AdsManager.dismissInters(this);
    }

}
