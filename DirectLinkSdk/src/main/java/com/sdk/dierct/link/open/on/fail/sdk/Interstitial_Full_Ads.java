package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;

public class Interstitial_Full_Ads {

    public void Show_Ads(Activity source_class, AdCloseListener adCloseListener) {
        Apps_Preference preference = new Apps_Preference(source_class);
        if (preference.getFullflag().equalsIgnoreCase("on")) {
            if (!preference.get_Click_Flag().equalsIgnoreCase("on")) {
                if (Con_stant.IS_TIME_INTERVAL) {
                    Con_stant.Front_Counter++;
                    callad(preference, source_class, adCloseListener, "noorganic");
                } else {
                    if (adCloseListener != null) {
                        adCloseListener.onAdClosed();
                    }
                }
            } else {
                if (Check_Screen_Activity.checkIsOrganic(source_class)) {
                    if (Con_stant.Front_Counter % Integer.parseInt(preference.getOrganic_Click_Count()) == 0) {
                        Con_stant.Front_Counter++;
                        callad(preference, source_class, adCloseListener, "organic");
                    } else {
                        Con_stant.Front_Counter++;
                        if (adCloseListener != null) {
                            adCloseListener.onAdClosed();
                        }
                    }
                } else {
                    if (Con_stant.Front_Counter % Integer.parseInt(preference.get_Click_Count()) == 0) {
                        Con_stant.Front_Counter++;
                        callad(preference, source_class, adCloseListener, "noorganic");
                    } else {
                        Con_stant.Front_Counter++;
                        if (adCloseListener != null) {
                            adCloseListener.onAdClosed();
                        }
                    }
                }
            }
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

    public void callad(Apps_Preference preference, Activity source_class, AdCloseListener adCloseListener, String oraganictype) {
        if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
            Interstitial_FullAd_Admob_Fb.ShowAd_Full(source_class, adCloseListener, oraganictype);
        } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
            if (Con_stant.Alt_Cnt_Inter == 2) {
                Con_stant.Alt_Cnt_Inter = 1;
                Interstitial_FullAd_Admob_Fb.ShowAd_Full(source_class, adCloseListener, oraganictype);
            } else {
                Con_stant.Alt_Cnt_Inter++;
                Interstitial_FullAd_Fb_Admob.ShowAd_FullFb(source_class, adCloseListener, oraganictype);
            }
        } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
            Interstitial_FullAd_Fb_Admob.ShowAd_FullFb(source_class, adCloseListener, oraganictype);
        } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
            Interstitial_FullAd_Admob_Fb_Qureka_MultipleAds.ShowAd_Full(source_class, adCloseListener, oraganictype);
        }
    }

    public interface AdCloseListener {
        void onAdClosed();
    }
}
