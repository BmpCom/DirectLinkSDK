package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.sdk.direct.link.open.on.fail.sdk.R;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Native_Ad {
    public Context context;
    Apps_Preference preference;

    public Native_Ad(Context context) {
        this.context = context;
        preference = new Apps_Preference(this.context);
    }

    public void Adaptive_Banner(final ViewGroup viewGroup) {
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.getBannerflag().equalsIgnoreCase("on")) {
                new NativeAd_Static(context).Adaptive_Banner(viewGroup);
                if (preference.getAdstyleBanner().equalsIgnoreCase("Normal")) {
                    Admob_Adaptive_Banner(viewGroup);
                } else if (preference.getAdstyleBanner().equalsIgnoreCase("ALT")) {
                    if (Con_stant.Alt_Cnt_Banner == 2) {
                        Admob_Adaptive_Banner(viewGroup);
                        Con_stant.Alt_Cnt_Banner = 1;
                    } else {
                        Facebook_Adaptive_Banner(viewGroup);
                        Con_stant.Alt_Cnt_Banner++;
                    }
                } else if (preference.getAdstyleBanner().equalsIgnoreCase("multiple")) {
                    Admob_FB_Multiple_Adaptive_Banner(viewGroup);
                } else if (preference.getAdstyleBanner().equalsIgnoreCase("fb")) {
                    Facebook_Adaptive_Banner(viewGroup);
                }
            }
        }
    }

    private void Admob_FB_Multiple_Adaptive_Banner(final ViewGroup viewGroup) {
        final List<String> adUnitIds = Arrays.asList(new Apps_Preference(context).get_Admob_Banner_Id1(),
                new Apps_Preference(context).get_Admob_Banner_Id2(),
                new Apps_Preference(context).get_Admob_Banner_Id3());
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

    private void Admob_Adaptive_Banner(final ViewGroup viewGroup) {
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

    private void Facebook_Adaptive_Banner(final ViewGroup viewGroup) {
        try {
            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, preference.get_Facebook_Banner(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
            viewGroup.removeAllViews();
            viewGroup.addView(adView);
            adView.loadAd(adView.buildLoadAdConfig().withAdListener(new com.facebook.ads.AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.e("TAG", " Facebook Adaptive_Banner  onAdFailedToLoad  " + adError.getErrorCode());
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

    private static AdSize getAdSize(Context context) {
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth);
    }

}
