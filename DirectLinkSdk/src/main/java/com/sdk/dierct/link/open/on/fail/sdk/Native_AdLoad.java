package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdBase;
import com.facebook.ads.NativeAdListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.nativead.NativeAd;

import java.util.ArrayList;


public class Native_AdLoad {
    private static final String TAG = "fbAd";
    public static Context context;

    public static ArrayList<NativeAd> mNativeAdsGHome;
    private static com.facebook.ads.NativeAd fbNativeAd;
    public static ArrayList<String> mNativeAdsId;
    int native_ads_count = 0;
    static int adCounter = -1;

    public static Object getNextNativeAd(Activity activity) {
        Apps_Preference preference = new Apps_Preference(activity);
        if (preference.get_AdstyleNative().equalsIgnoreCase("Normal")) {
            if (mNativeAdsGHome != null && mNativeAdsGHome.size() > 0) {
                return mNativeAdsGHome.get(getCounter());
            } else if (fbNativeAd != null) {
                return fbNativeAd;
            } else
                return null;
        } else if (preference.get_AdstyleNative().equalsIgnoreCase("fb")) {
            if (fbNativeAd != null) {
                return fbNativeAd;
            } else if (mNativeAdsGHome != null && mNativeAdsGHome.size() > 0) {
                return mNativeAdsGHome.get(getCounter());
            } else
                return null;
        } else return null;


    }

    public static int getCounter() {
        updateCounter();
        return adCounter;
    }

    public static void updateCounter() {
        adCounter++;
        if (adCounter >= mNativeAdsGHome.size()) {
            adCounter = 0;
        }
    }

    public Native_AdLoad(Context activity) {
        context = activity;
        mNativeAdsId = new ArrayList<>();
        Apps_Preference preference = new Apps_Preference(activity);
        if (!preference.get_Admob_Native_Id1().isEmpty()) {
            mNativeAdsId.add(preference.get_Admob_Native_Id1());
        }
        if (!preference.get_Admob_Native_Id2().isEmpty()) {
            mNativeAdsId.add(preference.get_Admob_Native_Id2());
        }
        if (!preference.get_Admob_Native_Id3().isEmpty()) {
            mNativeAdsId.add(preference.get_Admob_Native_Id3());
        }

        native_ads_count = mNativeAdsId.size();
        loadAds(activity);
    }

    public void loadAds(final Context activity) {
        Apps_Preference preference = new Apps_Preference(activity);
        if (preference.get_AdstyleNative().equalsIgnoreCase("Normal")) {
            loadGNativeIntermediate(activity, 0, true);
        } else if (preference.get_AdstyleNative().equalsIgnoreCase("fb")) {
            loadFbAd(activity, true);
        }
    }

    public void loadFbAd(Context activity, boolean fromMainFunction) {
        Apps_Preference preference = new Apps_Preference(activity);
        Log.d(TAG, preference.get_Facebook_Native());
        final com.facebook.ads.NativeAd nativeAd = new com.facebook.ads.NativeAd(activity, preference.get_Facebook_Native());

        NativeAdListener nativeAdListener = new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                if (fromMainFunction)
                    loadGNativeIntermediate(activity, 0, false);
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                fbNativeAd = nativeAd;
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!");
            }
        };

        // Request an ad
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .withMediaCacheFlag(NativeAdBase.MediaCacheFlag.ALL)
                        .build());
    }

    public void loadGNativeIntermediate(Context activity, int adCount, boolean fromMainFunction) {
        Apps_Preference preference = new Apps_Preference(activity);
        if (adCount == 0) {
            mNativeAdsGHome = new ArrayList<>();
        }
        AdLoader.Builder builder;

        String adUnitId = mNativeAdsId.get(adCount);
        Log.e("Ads ", "NativeAd adUnitId:  " + adUnitId);
        Log.e("NativeAd", "adUnitId:" + adUnitId);
        if (adUnitId == null) {
            return;
        }
        if (TextUtils.isEmpty(adUnitId)) {
            return;
        }
        builder = new AdLoader.Builder(context, adUnitId);

        builder.forNativeAd(nativeAd -> {
            mNativeAdsGHome.add(nativeAd);
            int nextConunt = adCount + 1;
            if (nextConunt < native_ads_count) {
                Log.e("Ads ", "NativeAd nextConunt: " + nextConunt);
                loadGNativeIntermediate(activity, nextConunt, fromMainFunction);
            }

            if (nextConunt == native_ads_count) {
                Log.e("Ads ", "NativeAd " + nextConunt + ":Last");
                Log.e("NativeAds: ", "last == ");

            }
        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(true)
                .build();

        com.google.android.gms.ads.nativead.NativeAdOptions adOptions = new com.google.android.gms.ads.nativead.NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);
        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError adError) {
                Log.e("Ads ", "NativeAd onAdFailedToLoad: " + adError.getMessage());
                if (mNativeAdsGHome.size() == 0 && fromMainFunction) {
                    loadFbAd(activity, false);
                }
            }
        }).build();

        if (preference.get_Ad_Flag().equals("admob")) {
            AdRequest.Builder builerRe = new AdRequest.Builder();
            adLoader.loadAd(builerRe.build());
        } else {
            AdManagerAdRequest.Builder builerRe = new AdManagerAdRequest.Builder();
            adLoader.loadAd(builerRe.build());
        }
    }

}
