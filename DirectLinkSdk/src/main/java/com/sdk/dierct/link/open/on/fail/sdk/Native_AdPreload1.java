package com.sdk.dierct.link.open.on.fail.sdk;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeAdViewAttributes;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.nativead.NativeAd;
import com.sdk.direct.link.open.on.fail.sdk.R;

import java.util.Random;


public class Native_AdPreload1 {
    public static Activity context;
    public static Apps_Preference preference;
    public static Native_AdPreload1 mInstance;

    public Native_AdPreload1(Activity activity) {
        context = activity;
        preference = new Apps_Preference(activity);

    }

    public static Native_AdPreload1 getInstance(Activity mContext) {
        context = mContext;
        preference = new Apps_Preference(mContext);
        if (mInstance == null) {
            mInstance = new Native_AdPreload1(mContext);
        }
        return mInstance;
    }

    private static AdSize getAdSize(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

    public void addNativeAd(FrameLayout viewGroup, boolean isList) {
        String type = isList ? new Apps_Preference(context).getNativeTypeList() : new Apps_Preference(context).getNativeTypeOther();
        switch (type) {
            case "banner":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Banner_Ads(context, viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
            case "small":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Small_Ads(context, viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
            case "medium":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Medium_Size(context, viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
            case "large":
                if (preference.getNativeflag().equalsIgnoreCase("on")) {
                    Native_Large_Size(context, viewGroup);
                } else {
                    viewGroup.setVisibility(View.GONE);
                }
                break;
        }
    }

    private void inflateAd(Context context, com.facebook.ads.NativeAd nativeAd, FrameLayout viewGroup, int height) {

        Apps_Preference preference = new Apps_Preference(context);
        int textColor = Color.parseColor("#" + preference.getTextColor());
        int backColor = Color.parseColor("#" + preference.getBackColor());
        int btnColor = Color.parseColor("#" + preference.getAdbtcolor());

        NativeAdViewAttributes viewAttributes = new NativeAdViewAttributes(context)
                .setBackgroundColor(backColor)
                .setTitleTextColor(textColor)
                .setDescriptionTextColor(textColor)
                .setButtonColor(btnColor)
                .setButtonTextColor(Color.WHITE);

        View adView = NativeAdView.render(context, nativeAd, viewAttributes);
        viewGroup.removeAllViews();
        viewGroup.addView(adView, new ViewGroup.LayoutParams(MATCH_PARENT, height));
    }


    public void Native_Banner_Ads(Activity activity, final FrameLayout viewGroup) {
        new NativeAd_Static(context).Native_Banner_Ads(viewGroup);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Object nativeAd = Native_AdLoad.getNextNativeAd(activity);
            if (nativeAd != null) {
                if (nativeAd instanceof NativeAd) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                    final Template_View templateView = inflate.findViewById(R.id.my_template_small);
                    inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                    templateView.setVisibility(View.GONE);
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd((NativeAd) nativeAd);
                    viewGroup.setVisibility(View.VISIBLE);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                } else if (nativeAd instanceof com.facebook.ads.NativeAd) {
                    inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 300);
                }
            } else {
                Qureka_Predchamp_Native_Banner(viewGroup);
            }
        }
    }

    public void Native_Small_Ads(Activity activity, final FrameLayout viewGroup) {
        new NativeAd_Static(context).Adaptive_Banner(viewGroup);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Object nativeAd = Native_AdLoad.getNextNativeAd(activity);
            if (nativeAd != null) {
                if (nativeAd instanceof NativeAd) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                    final Template_View templateView = inflate.findViewById(R.id.my_template_small);
                    inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                    templateView.setVisibility(View.GONE);
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd((NativeAd) nativeAd);
                    viewGroup.setVisibility(View.VISIBLE);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                } else if (nativeAd instanceof com.facebook.ads.NativeAd) {
                    inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 300);
                }
            } else {
                Qureka_Predchamp_Native_Banner(viewGroup);
            }
        }
    }

    public void Native_Medium_Size(Activity activity, final FrameLayout viewGroup) {
        new NativeAd_Static(context).Native_Banner_Ads(viewGroup);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Object nativeAd = Native_AdLoad.getNextNativeAd(activity);
            if (nativeAd != null) {
                if (nativeAd instanceof NativeAd) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                    final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                    inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                    templateView.setVisibility(View.GONE);
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd((NativeAd) nativeAd);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                } else if (nativeAd instanceof com.facebook.ads.NativeAd) {
                    inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 400);
                }
            } else {
                Qureka_Predchamp_Native(viewGroup);
            }
        }
    }

    public void Native_Large_Size(Activity activity, final FrameLayout viewGroup) {
        new NativeAd_Static(context).Native_Ads(viewGroup);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            Object nativeAd = Native_AdLoad.getNextNativeAd(activity);
            if (nativeAd != null) {
                if (nativeAd instanceof NativeAd) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                    final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                    inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                    templateView.setVisibility(View.GONE);
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd((NativeAd) nativeAd);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                } else if (nativeAd instanceof com.facebook.ads.NativeAd) {
                    inflateAd(context, (com.facebook.ads.NativeAd) nativeAd, viewGroup, 500);
                }
            } else {
                Qureka_Predchamp_Native(viewGroup);
            }
        }
    }

    private void Qureka_Predchamp_Native(final ViewGroup BannerContainer) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Con_stant.qureka_native[i1]);
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Con_stant.qureka_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Con_stant.qureka_description[i1]);
                Glide.with(context).load(Con_stant.qureka_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                Glide.with(context).load(Con_stant.predchamp_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Con_stant.predchamp_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Con_stant.predchamp_description[i1]);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Con_stant.predchamp_native[i1]);
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else {
                BannerContainer.setVisibility(View.GONE);
            }
        }
    }

    private void Qureka_Predchamp_Native_Banner(final ViewGroup BannerContainer) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {

            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_banner_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Con_stant.qureka_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Con_stant.qureka_description[i1]);
                Glide.with(context).load(Con_stant.qureka_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(context));
                BannerContainer.setVisibility(View.VISIBLE);
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_native_banner_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((TextView) view.findViewById(R.id.tv_appname)).setText(Con_stant.predchamp_header[i1]);
                ((TextView) view.findViewById(R.id.tv_desc)).setText(Con_stant.predchamp_description[i1]);
                Glide.with(context).load(Con_stant.predchamp_icon[i1]).into((ImageView) view.findViewById(R.id.img_logo));
                view.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(context));
                BannerContainer.setVisibility(View.VISIBLE);

                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else {
                BannerContainer.setVisibility(View.GONE);
            }
        }
    }

    private void Qureka_Predchamp_Adaptive(final ViewGroup BannerContainer) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_adaptive_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Con_stant.qureka_banner[i1]);
                view.setOnClickListener(v -> Con_stant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_qureka_adaptive_ads, null, false);
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                ((ImageView) view.findViewById(R.id.img_banner)).setImageResource(Con_stant.predchamp_banner[i1]);
                view.setOnClickListener(v -> Con_stant.Open_Qureka(context));
                BannerContainer.removeAllViews();
                BannerContainer.addView(view);
            } else {
                BannerContainer.setVisibility(View.GONE);
            }
        }
    }
}
