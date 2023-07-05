package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Arrays;
import java.util.List;

public class Interstitial_FullAd_Admob_Fb_Qureka_MultipleAds {
    public static InterstitialAd mInterstitialAd_admob;

    public static void ShowAd_Full(Activity source_class, Interstitial_Full_Ads.AdCloseListener adCloseListener, String oraganictype) {
        final List<String> adUnitIds = Arrays.asList(new Apps_Preference(source_class).get_Admob_Interstitial_Id1(),
                new Apps_Preference(source_class).get_Admob_Interstitial_Id2(),
                new Apps_Preference(source_class).get_Admob_Interstitial_Id3());

        Apps_Preference preference = new Apps_Preference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Con_stant.Front_Counter++;
            if (Con_stant.FullAdCounter > 2) {
                Con_stant.FullAdCounter = 0;
            }
            final Custom_Prog_Dialog customProgressDialog = new Custom_Prog_Dialog(source_class, "Showing Ad...");
            customProgressDialog.setCancelable(false);
            customProgressDialog.show();
            AdRequest adRequest = new AdRequest.Builder().build();
            Log.e("admob id", String.valueOf(Con_stant.FullAdCounter));
            InterstitialAd.load(source_class, adUnitIds.get(Con_stant.FullAdCounter), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAd_admob = interstitialAd;
                    mInterstitialAd_admob.show(source_class);
                    Con_stant.FullAdCounter++;
                    Log.e("inside load ad", String.valueOf(Con_stant.FullAdCounter));
                    mInterstitialAd_admob.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            mInterstitialAd_admob = null;
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
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            mInterstitialAd_admob = null;
                            Apps_Preference.isFullScreenShow = true;
                            if (oraganictype.equalsIgnoreCase("noorganic")) {
                                Con_stant.Open_Url_View(source_class);
                            }
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            if (customProgressDialog.isShowing()) {
                                customProgressDialog.dismiss();
                            }
                            Apps_Preference.isFullScreenShow = false;
                            if (adCloseListener != null) {
                                adCloseListener.onAdClosed();
                            }
                            Con_stant.IS_TIME_INTERVAL = false;
                            new Handler().postDelayed(() -> Con_stant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                        }
                    });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    mInterstitialAd_admob = null;
                    if (customProgressDialog.isShowing()) {
                        customProgressDialog.dismiss();
                    }
                    Apps_Preference.isFullScreenShow = false;

                    com.facebook.ads.InterstitialAd fb_interstitial = new com.facebook.ads.InterstitialAd(source_class, preference.get_Facebook_Interstitial());
                    fb_interstitial.loadAd(
                            fb_interstitial.buildLoadAdConfig()
                                    .withAdListener(new InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialDisplayed(Ad ad) {
                                            Apps_Preference.isFullScreenShow = true;
                                            if (oraganictype.equalsIgnoreCase("noorganic")) {
                                                Con_stant.Open_Url_View(source_class);
                                            }
                                        }

                                        @Override
                                        public void onInterstitialDismissed(Ad ad) {
                                            if (customProgressDialog.isShowing()) {
                                                customProgressDialog.dismiss();
                                            }
                                            Apps_Preference.isFullScreenShow = false;

                                            if (adCloseListener != null) {
                                                adCloseListener.onAdClosed();
                                            }
                                            Con_stant.IS_TIME_INTERVAL = false;
                                            new Handler().postDelayed(() -> Con_stant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
                                        }

                                        @Override
                                        public void onError(Ad ad, AdError adError) {
                                            Log.e("TAG", "onError: " + adError.getErrorCode());
                                            if (customProgressDialog.isShowing()) {
                                                customProgressDialog.dismiss();
                                            }
                                            Apps_Preference.isFullScreenShow = false;
                                            Interstitial_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, adCloseListener);
                                            if (oraganictype.equalsIgnoreCase("noorganic")) {
                                                Con_stant.Open_Url_View(source_class);
                                            }
                                            Con_stant.IS_TIME_INTERVAL = false;
                                            new Handler().postDelayed(() -> Con_stant.IS_TIME_INTERVAL = true, Long.parseLong(String.valueOf(preference.get_Ad_Time_Interval())) * 1000);
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
                                            Con_stant.Front_Counter++;
                                        }

                                        @Override
                                        public void onAdClicked(Ad ad) {

                                        }

                                        @Override
                                        public void onLoggingImpression(Ad ad) {

                                        }
                                    })
                                    .build());
                }
            });
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }
}
