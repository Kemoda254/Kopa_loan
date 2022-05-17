package com.shwa.kopes;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnection {

    Context context;


    public InternetConnection(Context context) {
        this.context = context;
    }

    public boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();

            if (info != null) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // connected to wifi
                    return true;
                }
            }
        }

        return false;
    }
}
