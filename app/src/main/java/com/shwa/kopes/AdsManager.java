package com.shwa.kopes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.LinearLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;

public class AdsManager {

    static MaxAdView adView;
    private static MaxInterstitialAd interstitialAdAPL;

    @SuppressLint("ResourceAsColor")

    public static void showBanner(final Activity activity) {
        if (activity.getResources().getString(R.string.bannerID_applovin) != null) {
            final LinearLayout adContainer = activity.findViewById(R.id.ads);

            adView = new MaxAdView(activity.getResources().getString(R.string.bannerID_applovin), activity);
            adView.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                  //  adContainer.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {

                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                }
            });
            adContainer.addView(adView);
            adView.loadAd();

        }
    }

    public static void showLoadAppLovinAds(final Activity activity, final Intent mClass) {
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Please wait...");
        dialog.show();

        if (activity.getResources().getString(R.string.intersID_applovin) != null) {
            interstitialAdAPL = new MaxInterstitialAd(activity.getResources().getString(R.string.intersID_applovin)
                    , activity);
            interstitialAdAPL.loadAd();
        }

        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                if (interstitialAdAPL.isReady()) {
                    interstitialAdAPL.showAd();
                    interstitialAdAPL.setListener(new MaxAdViewAdListener() {
                        @Override
                        public void onAdExpanded(MaxAd ad) {

                        }

                        @Override
                        public void onAdCollapsed(MaxAd ad) {


                        }


                        @Override
                        public void onAdLoaded(MaxAd ad) {

                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {
                            activity.startActivity(mClass);

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {

                            activity.startActivity(mClass);


                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                         //   activity.startActivity(mClass);

                        }
                    });
                }
                else {
                    activity.startActivity(mClass);
                }


            }
        }.start();


    }


    public static void loadInters(final Activity activity) {


        if (activity.getResources().getString(R.string.intersID_applovin) != null) {
            interstitialAdAPL = new MaxInterstitialAd(activity.getResources().getString(R.string.intersID_applovin)
                    , activity);
            interstitialAdAPL.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {


                }


                @Override
                public void onAdLoaded(MaxAd ad) {



                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {


                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {


                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {


                }
            });

            // Load the first ad
            interstitialAdAPL.loadAd();
        }


    }


    public static void dismissInters(final Activity activity) {

        if(interstitialAdAPL != null){
            interstitialAdAPL.destroy();
        }
    }

    public static void dismissBanner(final Activity activity) {

       if(adView != null){
           adView.destroy();
       }

    }


    public static void showInters(final Activity activity) {

        if (interstitialAdAPL.isReady()) {
            interstitialAdAPL.showAd();
        }

    }


}

