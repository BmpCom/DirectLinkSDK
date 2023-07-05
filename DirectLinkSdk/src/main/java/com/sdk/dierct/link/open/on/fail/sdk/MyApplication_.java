package com.sdk.dierct.link.open.on.fail.sdk;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.facebook.ads.AudienceNetworkAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplication_ extends MultiDexApplication {
    private static MyApplication_ instance;

    public static void loadAds(OnInitializationCompleteListener listener) {
        MobileAds.initialize(
                instance,
                initializationStatus -> {
                    listener.onInitializationComplete(initializationStatus);
                    if (new Apps_Preference(instance).getOpenflag().equalsIgnoreCase("on")) {
                        new App_OpenManager_(instance);
                    }
                    if (new Apps_Preference(instance).get_Ad_Status().equalsIgnoreCase("on")) {
                        if (new Apps_Preference(instance).getNativeflag().equalsIgnoreCase("on")) {
                            new Native_AdLoad(instance);
                        }
                    }
                });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AudienceNetworkAds.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
