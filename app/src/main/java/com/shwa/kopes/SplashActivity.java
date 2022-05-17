package com.shwa.kopes;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class SplashActivity extends AppCompatActivity {

    InternetConnection cd;

    // 1. Create a static nested class that extends Runnable to start the main Activity
    private class StartMainActivityRunnable implements Runnable {
        // 2. Make sure we keep the source Activity as a WeakReference (more on that later)
        private WeakReference<Activity> mActivity;

        private StartMainActivityRunnable(Activity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            // 3. Check that the reference is valid and execute the code
            if (cd.isConnected()){

                if (mActivity.get() != null) {
                    Activity activity = mActivity.get();
                    Intent mainIntent = new Intent(activity, WelcomeActivity.class);
                    activity.startActivity(mainIntent);
                    activity.finish();
                }
            }
            else {
                AlertDialog alertDialog = new AlertDialog.Builder(SplashActivity.this).create();
                alertDialog.setTitle("ERROR..!!");
                alertDialog.setMessage("Ensure You are Connected To The Internet");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Connect",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                share();

                            }
                        });
                alertDialog.show();
            }
        }
    }

    private void share() {
        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 2400;

    // 4. Declare the Handler as a member variable
    private Handler mHandler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        cd= new InternetConnection(this);
        mHandler.postDelayed(new StartMainActivityRunnable(this), SPLASH_DISPLAY_LENGTH);


    }

    // 6. Override onDestroy()
    @Override
    public void onDestroy() {

        // 7. Remove any delayed Runnable(s) and prevent them from executing.
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);

        // 8. Eagerly clear mHandler allocated memory
        mHandler = null;

    }
}