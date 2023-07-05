package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;

public class Interstitial_FullAd_Facebook {

    public static void ShowAd_Facebook(Activity source_class, Interstitial_Full_Ads.AdCloseListener adCloseListener) {
        Apps_Preference preference = new Apps_Preference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Con_stant.Front_Counter++;
            final Custom_Prog_Dialog customProgressDialog = new Custom_Prog_Dialog(source_class, "Showing Ad...");
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
            com.facebook.ads.InterstitialAd fb_interstitial = new com.facebook.ads.InterstitialAd(source_class, preference.get_Facebook_Interstitial());
            fb_interstitial.loadAd(
                    fb_interstitial.buildLoadAdConfig()
                            .withAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    Apps_Preference.isFullScreenShow = true;
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    Apps_Preference.isFullScreenShow = false;
                                    if (customProgressDialog.isShowing()) {
                                        customProgressDialog.dismiss();
                                    }
                                    if (adCloseListener != null) {
                                        adCloseListener.onAdClosed();
                                    }
                                    Con_stant.IS_TIME_INTERVAL = false;
                                    new Handler().postDelayed(() -> Con_stant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {
                                    Apps_Preference.isFullScreenShow = false;
                                    Log.e("TAG", "onError: " + adError.getErrorCode());
                                    if (customProgressDialog.isShowing()) {
                                        customProgressDialog.dismiss();
                                    }
                                    if (adCloseListener != null) {
                                        adCloseListener.onAdClosed();
                                    }
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    if (!fb_interstitial.isAdLoaded()) {
                                        return;
                                    }
                                    if (fb_interstitial.isAdInvalidated()) {
                                        return;
                                    }
                                    fb_interstitial.show();
                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            })
                            .build());
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }
}
