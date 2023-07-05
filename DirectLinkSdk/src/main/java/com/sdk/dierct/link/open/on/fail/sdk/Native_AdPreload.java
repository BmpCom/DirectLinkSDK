package com.sdk.dierct.link.open.on.fail.sdk;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdView;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.sdk.direct.link.open.on.fail.sdk.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Native_AdPreload {
    public static Context context;
    public static Apps_Preference preference;
    public static Native_AdPreload mInstance;
    com.google.android.gms.ads.nativead.NativeAd nativeAd1;
    com.google.android.gms.ads.nativead.NativeAd nativeAd2;
    com.google.android.gms.ads.nativead.NativeAd nativeAd3;
    com.google.android.gms.ads.nativead.NativeAd nativeAd4;
    NativeAd FbNativeAd1;
    NativeAd FbNativeAd2;
    NativeAd FbNativeAd3;
    NativeAd FbNativeAd4;
    com.google.android.gms.ads.nativead.NativeAd nativeBannerAd1;
    com.google.android.gms.ads.nativead.NativeAd nativeBannerAd2;
    com.google.android.gms.ads.nativead.NativeAd nativeBannerAd3;
    NativeBannerAd FBNativeBannerAd1;
    NativeBannerAd FBNativeBannerAd2;
    NativeBannerAd FBNativeBannerAd3;


    public Native_AdPreload(Context activity) {
        context = activity;
        preference = new Apps_Preference(activity);

    }

    public static Native_AdPreload getInstance(Context mContext) {
        context = mContext;
        preference = new Apps_Preference(mContext);
        if (mInstance == null) {
            mInstance = new Native_AdPreload(mContext);
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

    public void Native_Ad(final ViewGroup viewGroup) {
        new NativeAd_Static(context).Native_Ads(viewGroup);
        if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
            Admob_Native_Ads(viewGroup);
        } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
            if (Con_stant.Alt_Cnt_Native == 2) {
                Admob_Native_Ads(viewGroup);
                Con_stant.Alt_Cnt_Native = 1;
            } else {
                Facebook_Native_Ads(viewGroup);
                Con_stant.Alt_Cnt_Native++;
            }
        } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
            Admob_FB_Multiple_Native_Ads(viewGroup);
        } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
            Facebook_Native_Ads(viewGroup);
        }
    }


    public void Native_Banner_Ads(final ViewGroup viewGroup) {
        new NativeAd_Static(context).Native_Banner_Ads(viewGroup);
        Log.d("TAG", "Native_Banner_Ads: " + preference.get_Adstyle());
        if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
            Admob_Native_Banner_Ads(viewGroup);
        } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
            if (Con_stant.Alt_Cnt_Native_Banner == 2) {
                Admob_Native_Banner_Ads(viewGroup);
                Con_stant.Alt_Cnt_Native_Banner = 1;
            } else {
                Facebook_Native_Banner_Ads(viewGroup);
                Con_stant.Alt_Cnt_Native_Banner++;
            }
        } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
            Admob_FB_Multiple_Native_Banner_Ads(viewGroup);
        } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
            Facebook_Native_Banner_Ads(viewGroup);
        }
    }

    public void Adaptive_Banner(final ViewGroup viewGroup) {
        new NativeAd_Static(context).Adaptive_Banner(viewGroup);
        if (preference.get_Adstyle().equalsIgnoreCase("Normal")) {
            Admob_Adaptive_Banner(viewGroup);
        } else if (preference.get_Adstyle().equalsIgnoreCase("ALT")) {
            if (Con_stant.Alt_Cnt_Banner == 2) {
                Admob_Adaptive_Banner(viewGroup);
                Con_stant.Alt_Cnt_Banner = 1;
            } else {
                Facebook_Adaptive_Banner(viewGroup);
                Con_stant.Alt_Cnt_Banner++;
            }
        } else if (preference.get_Adstyle().equalsIgnoreCase("multiple")) {
            Admob_FB_Multiple_Adaptive_Banner(viewGroup);
        } else if (preference.get_Adstyle().equalsIgnoreCase("fb")) {
            Facebook_Adaptive_Banner(viewGroup);
        }
    }

    private void Admob_Native_Ads(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
                builder.forNativeAd(nativeAdLoad -> {
                    nativeAd1 = nativeAdLoad;
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd(nativeAdLoad);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                });

                builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
                        super.onAdFailedToLoad(loadAdError);
                        templateView.setVisibility(View.GONE);
                        Qureka_Predchamp_Native(viewGroup);
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
                    }
                }).build().loadAd(new AdRequest.Builder().build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Admob_Native_Ads1(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
                builder.forNativeAd(nativeAd -> {
                    nativeAd2 = nativeAd;

                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd(nativeAd);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                });

                builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
                        super.onAdFailedToLoad(loadAdError);
                        templateView.setVisibility(View.GONE);
                        Qureka_Predchamp_Native(viewGroup);
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
                    }
                }).build().loadAd(new AdRequest.Builder().build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Admob_FB_Multiple_Native_Ads(final ViewGroup viewGroup) {
        final List<String> adUnitIds = Arrays.asList(new Apps_Preference(context).get_Admob_Native_Id1(),
                new Apps_Preference(context).get_Admob_Native_Id2(),
                new Apps_Preference(context).get_Admob_Native_Id3());
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                if (Con_stant.NAtiveAdCounter > 2) {
                    Con_stant.NAtiveAdCounter = 0;
                }
                templateView.setVisibility(View.GONE);
                AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Con_stant.NAtiveAdCounter));
                Log.e("NAtiveAdCounter out", String.valueOf(Con_stant.NAtiveAdCounter));

                builder.forNativeAd(nativeAd -> {
                    nativeAd3 = nativeAd;
                    Con_stant.NAtiveAdCounter++;
                    Log.e("NAtiveAdCounter", String.valueOf(Con_stant.NAtiveAdCounter));
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd(nativeAd);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                });

                builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
                        super.onAdFailedToLoad(loadAdError);
                        templateView.setVisibility(View.GONE);
                        final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
                                templateView.setVisibility(View.GONE);
                                Qureka_Predchamp_Native(viewGroup);
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                FbNativeAd1 = nativeAd;
                                View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
                                viewGroup.removeAllViews();
                                viewGroup.addView(adView);
                                viewGroup.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                            }
                        };
                        nativeAd.loadAd(
                                nativeAd.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
                    }
                }).build().loadAd(new AdRequest.Builder().build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Admob_FB_Multiple_Native_Ads1(final ViewGroup viewGroup) {
        final List<String> adUnitIds = Arrays.asList(new Apps_Preference(context).get_Admob_Native_Id1(),
                new Apps_Preference(context).get_Admob_Native_Id2(),
                new Apps_Preference(context).get_Admob_Native_Id3());
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                if (Con_stant.NAtiveAdCounter > 2) {
                    Con_stant.NAtiveAdCounter = 0;
                }
                templateView.setVisibility(View.GONE);
                AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Con_stant.NAtiveAdCounter));
                Log.e("NAtiveAdCounter out", String.valueOf(Con_stant.NAtiveAdCounter));

                builder.forNativeAd(nativeAd -> {
                    nativeAd4 = nativeAd;
                    Con_stant.NAtiveAdCounter++;
                    Log.e("NAtiveAdCounter", String.valueOf(Con_stant.NAtiveAdCounter));
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd(nativeAd);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                });

                builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("TAG", "Admob Fail -> onAdFailedToLoad: Native" + loadAdError.getMessage());
                        super.onAdFailedToLoad(loadAdError);
                        templateView.setVisibility(View.GONE);
                        final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
                        NativeAdListener nativeAdListener = new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
                                templateView.setVisibility(View.GONE);
                                Qureka_Predchamp_Native(viewGroup);
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                FbNativeAd2 = nativeAd;
                                View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
                                viewGroup.removeAllViews();
                                viewGroup.addView(adView);
                                viewGroup.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                            }
                        };
                        nativeAd.loadAd(
                                nativeAd.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.e("TAG", "Admob Load -> onNativeAdLoaded: Native");
                    }
                }).build().loadAd(new AdRequest.Builder().build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Facebook_Native_Ads(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
                        templateView.setVisibility(View.GONE);
                        Qureka_Predchamp_Native(viewGroup);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        FbNativeAd3 = nativeAd;
                        View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
                        viewGroup.removeAllViews();
                        viewGroup.addView(adView);
                        viewGroup.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                nativeAd.loadAd(
                        nativeAd.buildLoadAdConfig()
                                .withAdListener(nativeAdListener)
                                .build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Facebook_Native_Ads1(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final Template_View templateView = inflate.findViewById(R.id.my_template_large);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                final NativeAd nativeAd = new NativeAd(context, preference.get_Facebook_Native());
                NativeAdListener nativeAdListener = new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        Log.e("TAG", " Facebook Native_Ads  onAdFailedToLoad  " + adError.getErrorCode());
                        templateView.setVisibility(View.GONE);
                        Qureka_Predchamp_Native(viewGroup);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        FbNativeAd4 = nativeAd;
                        View adView = NativeAdView.render(context, nativeAd, NativeAdView.Type.HEIGHT_300);
                        viewGroup.removeAllViews();
                        viewGroup.addView(adView);
                        viewGroup.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }
                };
                nativeAd.loadAd(
                        nativeAd.buildLoadAdConfig()
                                .withAdListener(nativeAdListener)
                                .build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Admob_Adaptive_Banner(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                final AdView adView = new AdView(context);
                adView.setAdSize(getAdSize(context));
                adView.setAdUnitId(preference.get_Admob_Banner_Id1());
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("TAG", "Admob  Fail -> onAdFailedToLoad: Banner" + loadAdError.getMessage());
                        super.onAdFailedToLoad(loadAdError);
                        Qureka_Predchamp_Adaptive(viewGroup);
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.e("TAG", "Admob Load -> onAdLoaded: Banner");

                        try {
                            viewGroup.removeAllViews();
                            viewGroup.addView(adView);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Admob_FB_Multiple_Adaptive_Banner(final ViewGroup viewGroup) {
        final List<String> adUnitIds = Arrays.asList(new Apps_Preference(context).get_Admob_Banner_Id1(),
                new Apps_Preference(context).get_Admob_Banner_Id2(),
                new Apps_Preference(context).get_Admob_Banner_Id3());
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                if (Con_stant.BannerAdCounter > 2) {
                    Con_stant.BannerAdCounter = 0;
                }
                Log.e("BannerAdCounter out", String.valueOf(Con_stant.BannerAdCounter));
                final AdView adView = new AdView(context);
                adView.setAdSize(getAdSize(context));
                adView.setAdUnitId(adUnitIds.get(Con_stant.BannerAdCounter));
                AdRequest adRequest = new AdRequest.Builder().build();
                adView.loadAd(adRequest);
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("TAG", "Admob  Fail -> onAdFailedToLoad: Banner" + loadAdError.getMessage());
                        super.onAdFailedToLoad(loadAdError);
                        try {
                            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, preference.get_Facebook_Banner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                            viewGroup.removeAllViews();
                            viewGroup.addView(adView);
                            adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                                @Override
                                public void onError(Ad ad, AdError adError) {
                                    Log.e("TAG", " Facebook Adaptive_Banner  onAdFailedToLoad  " + adError.getErrorCode());
                                    Qureka_Predchamp_Adaptive(viewGroup);
                                }

                                @Override
                                public void onAdLoaded(Ad ad) {

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            }).build());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.e("TAG", "Admob Load -> onAdLoaded: Banner");
                        Con_stant.BannerAdCounter++;
                        Log.e("BannerAdCounter", String.valueOf(Con_stant.BannerAdCounter));
                        try {
                            viewGroup.removeAllViews();
                            viewGroup.addView(adView);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onAdClicked() {
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdImpression() {
                        super.onAdImpression();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void Facebook_Adaptive_Banner(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, preference.get_Facebook_Banner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                viewGroup.removeAllViews();
                viewGroup.addView(adView);
                adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        Log.e("TAG", " Facebook Adaptive_Banner  onAdFailedToLoad  " + adError.getErrorCode());
                        Qureka_Predchamp_Adaptive(viewGroup);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                }).build());

            } catch (Exception e) {
                e.printStackTrace();
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
                new NativeAd_Static(context).Native_Ads(BannerContainer);
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
                new NativeAd_Static(context).Native_Banner_Ads(BannerContainer);
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
                new NativeAd_Static(context).Adaptive_Banner(BannerContainer);
            }
        }
    }

    private void inflateAdmob_Native_Banner(final ViewGroup viewGroup, com.google.android.gms.ads.nativead.NativeAd nativeAd) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
        final Template_View templateView = inflate.findViewById(R.id.my_template_small);
        inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
        templateView.setVisibility(View.GONE);
        NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
        templateView.setVisibility(View.VISIBLE);
        templateView.setStyles(build);
        templateView.setNativeAd(nativeAd);
        viewGroup.setVisibility(View.VISIBLE);
        viewGroup.removeAllViews();
        viewGroup.addView(inflate);
    }

    private void Admob_Native_Banner_Ads(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (nativeBannerAd1 != null) {

                inflateAdmob_Native_Banner(viewGroup, nativeBannerAd1);
            } else {
                try {
                    AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
                    builder.forNativeAd(nativeAd -> {
                        nativeBannerAd1 = nativeAd;
                        inflateAdmob_Native_Banner(viewGroup, nativeBannerAd1);

                    });

                    builder.withAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            viewGroup.setVisibility(View.GONE);
                            Qureka_Predchamp_Native_Banner(viewGroup);
                        }

                        @Override
                        public void onAdOpened() {
                            super.onAdOpened();
                        }

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                        }
                    }).build().loadAd(new AdRequest.Builder().build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Qureka_Predchamp_Native_Banner(viewGroup);
        }
    }

    private void Admob_Native_Banner_Ads1(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (nativeBannerAd2 != null) {
                inflateAdmob_Native_Banner(viewGroup, nativeBannerAd2);
            } else {
                try {
                    AdLoader.Builder builder = new AdLoader.Builder(context, preference.get_Admob_Native_Id1());
                    builder.forNativeAd(nativeAd -> {
                        nativeBannerAd2 = nativeAd;
                        inflateAdmob_Native_Banner(viewGroup, nativeBannerAd1);

                    });

                    builder.withAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            viewGroup.setVisibility(View.GONE);
                            Qureka_Predchamp_Native_Banner(viewGroup);
                        }

                        @Override
                        public void onAdOpened() {
                            super.onAdOpened();
                        }

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                        }
                    }).build().loadAd(new AdRequest.Builder().build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else {
            Qureka_Predchamp_Native_Banner(viewGroup);
        }
    }

    private void inflateMULTIPLE_Native_Banner(final ViewGroup viewGroup, com.google.android.gms.ads.nativead.NativeAd nativeAd, NativeBannerAd FBNativeAd) {
        if (nativeAd != null) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
            final Template_View templateView = inflate.findViewById(R.id.my_template_small);
            inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
            templateView.setVisibility(View.GONE);
            NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
            templateView.setVisibility(View.VISIBLE);
            templateView.setStyles(build);
            templateView.setNativeAd(nativeAd);
            viewGroup.setVisibility(View.VISIBLE);
            viewGroup.removeAllViews();
            viewGroup.addView(inflate);
        } else if (FBNativeAd != null) {
            try {
                View adView = NativeBannerAdView.render(context, FBNativeAd, NativeBannerAdView.Type.HEIGHT_100);
                viewGroup.setVisibility(View.VISIBLE);
                viewGroup.removeAllViews();
                viewGroup.addView(adView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            viewGroup.setVisibility(View.GONE);
            Qureka_Predchamp_Native_Banner(viewGroup);
        }
    }

    private void Admob_FB_Multiple_Native_Banner_Ads(final ViewGroup viewGroup) {
        final List<String> adUnitIds = Arrays.asList(new Apps_Preference(context).get_Admob_Native_Id1(),
                new Apps_Preference(context).get_Admob_Native_Id2(),
                new Apps_Preference(context).get_Admob_Native_Id3());
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                if (Con_stant.NAtiveBannerAdCounter > 2) {
                    Con_stant.NAtiveBannerAdCounter = 0;
                }
                if (nativeBannerAd3 != null || FBNativeBannerAd3 != null) {
                    inflateMULTIPLE_Native_Banner(viewGroup, nativeBannerAd3, FBNativeBannerAd3);
                } else {
                    AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Con_stant.NAtiveBannerAdCounter));
                    builder.forNativeAd(nativeAd -> {
                        nativeBannerAd3 = nativeAd;
                        Con_stant.NAtiveBannerAdCounter++;
                        inflateMULTIPLE_Native_Banner(viewGroup, nativeBannerAd3, FBNativeBannerAd3);


                    });

                    builder.withAdListener(new AdListener() {
                        @Override
                        public void onAdClosed() {
                            super.onAdClosed();
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            super.onAdFailedToLoad(loadAdError);
                            NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
                            NativeAdListener nativeAdListener = new NativeAdListener() {

                                @Override
                                public void onMediaDownloaded(Ad ad) {
                                    // Native ad finished downloading all assets
                                    Log.e(TAG, "Native ad finished downloading all assets.");
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {
                                    // Native ad failed to load
                                    Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                                    inflateMULTIPLE_Native_Banner(viewGroup, nativeBannerAd3, FBNativeBannerAd3);

                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    FBNativeBannerAd3 = nativeBannerAd;
                                    inflateMULTIPLE_Native_Banner(viewGroup, nativeBannerAd3, FBNativeBannerAd3);

                                    // Native ad is loaded and ready to be displayed
                                    Log.d(TAG, "Native ad is loaded and ready to be displayed!");

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
                            // load the ad
                            nativeBannerAd.loadAd(
                                    nativeBannerAd.buildLoadAdConfig()
                                            .withAdListener(nativeAdListener)
                                            .build());
                        }

                        @Override
                        public void onAdOpened() {
                            super.onAdOpened();
                        }

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                        }
                    }).build().loadAd(new AdRequest.Builder().build());
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Qureka_Predchamp_Native_Banner(viewGroup);
        }
    }

    private void Admob_FB_Multiple_Native_Banner_Ads1(final ViewGroup viewGroup) {
        final List<String> adUnitIds = Arrays.asList(new Apps_Preference(context).get_Admob_Native_Id1(),
                new Apps_Preference(context).get_Admob_Native_Id2(),
                new Apps_Preference(context).get_Admob_Native_Id3());
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            try {
                if (Con_stant.NAtiveBannerAdCounter > 2) {
                    Con_stant.NAtiveBannerAdCounter = 0;
                }
                View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
                final Template_View templateView = inflate.findViewById(R.id.my_template_small);
                inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
                templateView.setVisibility(View.GONE);
                AdLoader.Builder builder = new AdLoader.Builder(context, adUnitIds.get(Con_stant.NAtiveBannerAdCounter));
                Log.e("NAtivBanerAdCounter out", String.valueOf(Con_stant.NAtiveBannerAdCounter));
                builder.forNativeAd(nativeAd -> {
                    NativeTemplate_Style build = new NativeTemplate_Style.Builder().build();
                    Con_stant.NAtiveBannerAdCounter++;
                    Log.e("NAtivBanerAdCounter", String.valueOf(Con_stant.NAtiveBannerAdCounter));
                    templateView.setVisibility(View.VISIBLE);
                    templateView.setStyles(build);
                    templateView.setNativeAd(nativeAd);
                    viewGroup.removeAllViews();
                    viewGroup.addView(inflate);
                });

                builder.withAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
                        NativeAdListener nativeAdListener = new NativeAdListener() {

                            @Override
                            public void onMediaDownloaded(Ad ad) {
                                // Native ad finished downloading all assets
                                Log.e(TAG, "Native ad finished downloading all assets.");
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                // Native ad failed to load
                                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                                templateView.setVisibility(View.GONE);
                                Qureka_Predchamp_Native_Banner(viewGroup);
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                // Native ad is loaded and ready to be displayed
                                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                                try {
                                    View adView = NativeBannerAdView.render(context, nativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);
                                    viewGroup.removeAllViews();
                                    viewGroup.addView(adView);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
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
                        // load the ad
                        nativeBannerAd.loadAd(
                                nativeBannerAd.buildLoadAdConfig()
                                        .withAdListener(nativeAdListener)
                                        .build());
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                    }
                }).build().loadAd(new AdRequest.Builder().build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Qureka_Predchamp_Native_Banner(viewGroup);
        }
    }

    private void inflateFB_Native_Banner(final ViewGroup viewGroup, NativeBannerAd nativeAd) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.am_activity_native_ads_temp, viewGroup, false);
        final Template_View templateView = inflate.findViewById(R.id.my_template_small);
        inflate.findViewById(R.id.my_template_small).setVisibility(View.GONE);
        templateView.setVisibility(View.GONE);

        try {
            View adView = NativeBannerAdView.render(context, nativeAd, NativeBannerAdView.Type.HEIGHT_100);
            viewGroup.setVisibility(View.VISIBLE);

            viewGroup.removeAllViews();
            viewGroup.addView(adView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Facebook_Native_Banner_Ads(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (FBNativeBannerAd1 != null) {

                inflateFB_Native_Banner(viewGroup, FBNativeBannerAd1);
            } else {
                try {

                    NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
                    NativeAdListener nativeAdListener = new NativeAdListener() {

                        @Override
                        public void onMediaDownloaded(Ad ad) {
                            // Native ad finished downloading all assets
                            Log.e(TAG, "Native ad finished downloading all assets.");
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                            // Native ad failed to load
                            Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                            viewGroup.setVisibility(View.GONE);
                            Qureka_Predchamp_Native_Banner(viewGroup);
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Native ad is loaded and ready to be displayed
                            Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                            FBNativeBannerAd1 = nativeBannerAd;
                            inflateFB_Native_Banner(viewGroup, FBNativeBannerAd1);

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
                    // load the ad
                    nativeBannerAd.loadAd(
                            nativeBannerAd.buildLoadAdConfig()
                                    .withAdListener(nativeAdListener)
                                    .build());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void Facebook_Native_Banner_Ads1(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (FBNativeBannerAd2 != null) {

                inflateFB_Native_Banner(viewGroup, FBNativeBannerAd2);
            } else {
                try {
                    NativeBannerAd nativeBannerAd = new NativeBannerAd(context, preference.get_Facebook_Native());
                    NativeAdListener nativeAdListener = new NativeAdListener() {

                        @Override
                        public void onMediaDownloaded(Ad ad) {
                            // Native ad finished downloading all assets
                            Log.e(TAG, "Native ad finished downloading all assets.");
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {
                            // Native ad failed to load
                            Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
                            Qureka_Predchamp_Native_Banner(viewGroup);
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Native ad is loaded and ready to be displayed
                            Log.d(TAG, "Native ad is loaded and ready to be displayed!");
                            FBNativeBannerAd2 = nativeBannerAd;
                            inflateFB_Native_Banner(viewGroup, FBNativeBannerAd1);

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
                    // load the ad
                    nativeBannerAd.loadAd(
                            nativeBannerAd.buildLoadAdConfig()
                                    .withAdListener(nativeAdListener)
                                    .build());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
