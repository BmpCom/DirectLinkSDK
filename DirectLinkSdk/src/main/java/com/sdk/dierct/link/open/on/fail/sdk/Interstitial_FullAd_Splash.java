package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Interstitial_FullAd_Splash {
    private InterstitialAd mInterstitialAd_admob;
    private AdManagerInterstitialAd adManagerInterstitialAd;

    public void Show_Ads(Activity source_class, Intent intent, final boolean flag) {
        Apps_Preference appPreference = new Apps_Preference(source_class);
        if (appPreference.get_Ad_Flag().equals("admob")) {
            Show_AdsAdmob(source_class, intent, flag);
        } else
            Show_AdsAdx(source_class, intent, flag);
    }

    public void Show_AdsAdmob(Activity source_class, Intent intent, final boolean flag) {
        Apps_Preference preference = new Apps_Preference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on") && preference.isConnected()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(source_class, preference.get_Splash_Interstitial_Id(), adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAd_admob = interstitialAd;
                    mInterstitialAd_admob.show(source_class);
                    mInterstitialAd_admob.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            mInterstitialAd_admob = null;
                            Apps_Preference.isFullScreenShow = false;
                            if (flag) {
                                source_class.startActivity(intent);
                                source_class.finish();
                            } else {
                                source_class.startActivity(intent);
                            }
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            mInterstitialAd_admob = null;
                            Apps_Preference.isFullScreenShow = true;
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            Apps_Preference.isFullScreenShow = false;

                            if (flag) {
                                source_class.startActivity(intent);
                                source_class.finish();
                            } else {
                                source_class.startActivity(intent);
                            }
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
                    Apps_Preference.isFullScreenShow = false;

                    if (flag) {
                        source_class.startActivity(intent);
                        source_class.finish();
                    } else {
                        source_class.startActivity(intent);
                    }
                }
            });
        } else {
            if (flag) {
                source_class.startActivity(intent);
                source_class.finish();
            } else {
                source_class.startActivity(intent);
            }
        }
    }

    public void Show_AdsAdx(Activity source_class, Intent intent, final boolean flag) {
        Apps_Preference preference = new Apps_Preference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on") && preference.isConnected()) {
            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
            AdManagerInterstitialAd.load(source_class, preference.get_Splash_Interstitial_Id(), adRequest, new AdManagerInterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                    adManagerInterstitialAd = interstitialAd;
                    adManagerInterstitialAd.show(source_class);
                    adManagerInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            adManagerInterstitialAd = null;
                            Apps_Preference.isFullScreenShow = false;
                            if (flag) {
                                source_class.startActivity(intent);
                                source_class.finish();
                            } else {
                                source_class.startActivity(intent);
                            }
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                            adManagerInterstitialAd = null;
                            Apps_Preference.isFullScreenShow = true;
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            Apps_Preference.isFullScreenShow = false;
                            if (flag) {
                                source_class.startActivity(intent);
                                source_class.finish();
                            } else {
                                source_class.startActivity(intent);
                            }
                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                        }
                    });
                }


                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    adManagerInterstitialAd = null;
                    Apps_Preference.isFullScreenShow = false;
                    if (flag) {
                        source_class.startActivity(intent);
                        source_class.finish();
                    } else {
                        source_class.startActivity(intent);
                    }
                }
            });
        } else {
            if (flag) {
                source_class.startActivity(intent);
                source_class.finish();
            } else {
                source_class.startActivity(intent);
            }
        }
    }

}
