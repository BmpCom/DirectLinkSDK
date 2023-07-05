package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Locale;

public class Check_Screen_Activity {
    static InstallReferrerClient referrerClient;
    public static String referrerUrl = "NA";

    public static boolean checkReferrer(String url, String medium) {
        String[] splitParts = medium.split(",");
        if (TextUtils.isEmpty(url)) {
            return true;
        }
        for (String abc : splitParts) {
            if (url.toLowerCase(Locale.getDefault()).contains(abc.toLowerCase(Locale.getDefault())))
                return true;
        }
        return false;
    }

    public static boolean checkScreenFlag(Activity activity) {
        boolean isOrganic = checkIsOrganic(activity);
        Apps_Preference preference = new Apps_Preference(activity);
        boolean isScreenOn = preference.getScreen().equals("on");
        return isScreenOn && !isOrganic;
    }

    public static void startSDKActivity(Activity activity, Intent intent) {
        Apps_Preference preference = new Apps_Preference(activity);
        boolean check = checkReferrer(referrerUrl, preference.getMedium());
        if (check) {
            preference.setCheckinstallbool(false);
            startAdLoading(activity, preference, intent);
        } else {
            preference.setCheckinstallbool(true);
            startAdLoading(activity, preference, intent);
        }
    }

    public static void CallOpenAd(Apps_Preference preference, Activity activity, Intent intent) {
        String string = preference.get_Splash_OpenApp_Id();
        if (Apps_Preference.isFullScreenShow) {
            return;
        }
        try {
            AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
                public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                    super.onAdLoaded(appOpenAd);
                    FullScreenContentCallback r0 = new FullScreenContentCallback() {
                        @Override
                        public void onAdShowedFullScreenContent() {
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            activity.startActivity(intent);
                            activity.finish();
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            activity.startActivity(intent);
                            activity.finish();
                        }
                    };
                    appOpenAd.show(activity);
                    appOpenAd.setFullScreenContentCallback(r0);
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    activity.startActivity(intent);
                    activity.finish();
                }
            };
            AppOpenAd.load(activity, string, new AdRequest.Builder().build(), 1, loadCallback);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void startAdLoading(Activity activity, Apps_Preference preference, Intent intent) {
        MyApplication_.loadAds(initializationStatus -> {
            if (preference.get_splash_flag().equalsIgnoreCase("open")) {
                if (preference.getOpenflag().equalsIgnoreCase("on")) {
                    CallOpenAd(preference, activity, intent);
                } else {
                    activity.startActivity(intent);
                }
            } else {
                if (preference.getFullflag().equalsIgnoreCase("on")) {
                    new Handler().postDelayed(() -> new Interstitial_FullAd_Splash().Show_Ads(activity, intent, true), 1000);
                } else {
                    new Handler().postDelayed(() -> activity.startActivity(intent), 1000);
                }
            }
        });
    }

    public static void Native_Banner_Count(Activity activity, FrameLayout viewGroup) {
        Apps_Preference preference = new Apps_Preference(activity);
        int nativecount = Integer.parseInt(preference.getNativecount());
        if (Con_stant.NativeCountIncr == nativecount) {
            Con_stant.NativeCountIncr = 0;
        }
        if (Con_stant.NativeCountIncr % nativecount == 0) {
            Con_stant.NativeCountIncr++;
            Native_AdPreload1.getInstance(activity).Native_Banner_Ads(activity, viewGroup);
        } else {
            Con_stant.NativeCountIncr++;
        }
    }

    public static void Native_Large_Count(Activity activity, FrameLayout viewGroup, boolean isList) {
        Apps_Preference preference = new Apps_Preference(activity);
        int nativecount = Integer.parseInt(preference.getNativecount());
        if (Con_stant.NativeCountIncr == nativecount) {
            Con_stant.NativeCountIncr = 0;
        }
        if (Con_stant.NativeCountIncr % nativecount == 0) {
            Con_stant.NativeCountIncr++;
            Native_AdPreload1.getInstance(activity).addNativeAd(viewGroup, isList);
        } else {
            Con_stant.NativeCountIncr++;
        }
    }

    public static boolean checkIsOrganic(Activity activity) {
        Apps_Preference preference = new Apps_Preference(activity);
        String[] splitParts = preference.getMedium().split(",");
        if (TextUtils.isEmpty(preference.getReferrerUrl())) {
            return true;
        }
        for (String abc : splitParts) {
            if (preference.getReferrerUrl().toLowerCase(Locale.getDefault()).contains(abc.toLowerCase(Locale.getDefault())))
                return true;
        }
        if (preference.getGclid().equalsIgnoreCase("on")) {
            if (!preference.getReferrerUrl().toLowerCase(Locale.getDefault()).contains(preference.getGclidValue())) {
                return true;
            } else return false;
        }
        return false;
    }

    //check install
    public static void checkinstallreferre(Activity activity, ReferrerListener referrerListener) {
        Apps_Preference preference = new Apps_Preference(activity);
        referrerClient = InstallReferrerClient.newBuilder(activity).build();
        referrerClient.startConnection(new InstallReferrerStateListener() {
            @Override
            public void onInstallReferrerSetupFinished(int responseCode) {
                switch (responseCode) {
                    case InstallReferrerClient.InstallReferrerResponse.OK:
                        try {
                            ReferrerDetails response = referrerClient.getInstallReferrer();
                            referrerUrl = response.getInstallReferrer();
                            preference.setReferrerUrl(referrerUrl);
                            if (preference.getShowinstall().equalsIgnoreCase("on")) {
                                Toast.makeText(activity, "referrer :" + referrerUrl, Toast.LENGTH_SHORT).show();
                            }
                            referrerListener.referrerDone();
                        } catch (RemoteException e) {
                            referrerListener.referrerCancel();
                            Log.e("insref", "" + e.getMessage());
                        }
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.FEATURE_NOT_SUPPORTED");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.SERVICE_UNAVAILABLE");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.SERVICE_DISCONNECTED");
                        break;
                    case InstallReferrerClient.InstallReferrerResponse.DEVELOPER_ERROR:
                        referrerListener.referrerCancel();
                        Log.w("insref", "InstallReferrer Response.DEVELOPER_ERROR");
                        break;
                }
            }

            @Override
            public void onInstallReferrerServiceDisconnected() {
                Log.w("insref", "InstallReferrer onInstallReferrerServiceDisconnected()");
            }
        });
    }
}
