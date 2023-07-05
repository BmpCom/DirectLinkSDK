package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.ads.Ad;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.sdk.direct.link.open.on.fail.sdk.R;

public class RewardAds {

    public RewardedAd ADrewardedad;

    public void Premium_Dialog(Activity activity, UserEarnRewardListener userEarnRewardListener) {
        Dialog dialog = new Dialog(activity, R.style.transparent_dialog);
        dialog.setContentView(R.layout.layout_reward_dialog);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        dialog.findViewById(R.id.iv_close).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.cv_no).setOnClickListener(v -> dialog.dismiss());
        dialog.findViewById(R.id.cv_yes).setOnClickListener(v -> {
            (dialog.findViewById(R.id.pb_loading)).setVisibility(View.VISIBLE);
            Show_Reward_Ads(activity, dialog, userEarnRewardListener);
        });
        dialog.show();

    }

    private void Show_Reward_Ads(Activity source_class, Dialog dialog, UserEarnRewardListener userEarnRewardListener) {
        if (new Apps_Preference(source_class).get_Ad_Status().equalsIgnoreCase("on")) {
            if (new Apps_Preference(source_class).get_Ad_Flag().equalsIgnoreCase("Normal")) {
                ShowAdReward_Admob_Fb(source_class, dialog, userEarnRewardListener);
            } else if (new Apps_Preference(source_class).get_Ad_Flag().equalsIgnoreCase("fb")) {
                ShowAdReward_Fb_Admob(source_class, dialog, userEarnRewardListener);
            } else if (new Apps_Preference(source_class).get_Ad_Flag().equalsIgnoreCase("multiple")) {
                if (Con_stant.Alt_Cnt_Reward == 2) {
                    Con_stant.Alt_Cnt_Reward = 1;
                    ShowAdReward_Admob_Fb(source_class, dialog, userEarnRewardListener);
                } else {
                    Con_stant.Alt_Cnt_Reward++;
                    ShowAdReward_Fb_Admob(source_class, dialog, userEarnRewardListener);
                }
            } else {
                if (Con_stant.Alt_Cnt_Reward == 2) {
                    Con_stant.Alt_Cnt_Reward = 1;
                    ShowAdReward_Admob_Fb(source_class, dialog, userEarnRewardListener);
                } else {
                    Con_stant.Alt_Cnt_Reward++;
                    ShowAdReward_Fb_Admob(source_class, dialog, userEarnRewardListener);
                }
            }
        }
    }

    public void ShowAdReward_Admob_Fb(Activity source_class, Dialog dialog, UserEarnRewardListener userEarnRewardListener) {
        Apps_Preference preference = new Apps_Preference(source_class);
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(source_class, preference.get_Admob_Rewarded_Id(), adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.d("TAG", loadAdError.getMessage());

                final RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(source_class, preference.getFbrewardid());
                RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {

                    @Override
                    public void onError(Ad ad, com.facebook.ads.AdError adError) {
                        dialog.dismiss();
                        RewardAd_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, userEarnRewardListener);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        rewardedVideoAd.show();
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                    }

                    @Override
                    public void onRewardedVideoCompleted() {
                        Toast.makeText(source_class, "Ad completed, now give reward to user", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRewardedVideoClosed() {
                    }
                };
                rewardedVideoAd.loadAd(
                        rewardedVideoAd.buildLoadAdConfig()
                                .withAdListener(rewardedVideoAdListener)
                                .build());
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                ADrewardedad = rewardedAd;
                ADrewardedad.show(source_class, rewardItem -> {
                    //Your Code Goes Here
                    userEarnRewardListener.onEarnReward();
                });
                ADrewardedad.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {
                        dialog.dismiss();

                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        Log.d("TAG", "Ad failed to show.");
                        ADrewardedad = null;
                        dialog.dismiss();
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        Log.d("TAG", "Ad was dismissed.");
                        ADrewardedad = null;

                    }
                });
            }
        });
    }

    public void ShowAdReward_Fb_Admob(Activity source_class, Dialog dialog, UserEarnRewardListener userEarnRewardListener) {
        Apps_Preference preference = new Apps_Preference(source_class);
        AdRequest adRequest = new AdRequest.Builder().build();
        final RewardedVideoAd rewardedVideoAd = new RewardedVideoAd(source_class, preference.getFbrewardid());
        RewardedVideoAdListener rewardedVideoAdListener = new RewardedVideoAdListener() {

            @Override
            public void onError(Ad ad, com.facebook.ads.AdError adError) {
                RewardedAd.load(source_class, preference.get_Admob_Rewarded_Id(), adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.d("TAG", loadAdError.getMessage());
                        dialog.dismiss();
                        RewardAd_Qureka_Predchamp.Show_Qureka_Predchamp_Ads(source_class, userEarnRewardListener);
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        ADrewardedad = rewardedAd;
                        ADrewardedad.show(source_class, rewardItem -> {
                            //Your Code Goes Here
                            userEarnRewardListener.onEarnReward();
                        });
                        ADrewardedad.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdShowedFullScreenContent() {
                                dialog.dismiss();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                Log.d("TAG", "Ad failed to show.");
                                ADrewardedad = null;
                                dialog.dismiss();
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d("TAG", "Ad was dismissed.");
                                ADrewardedad = null;

                            }
                        });
                    }
                });
            }

            @Override
            public void onAdLoaded(Ad ad) {
                rewardedVideoAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }

            @Override
            public void onRewardedVideoCompleted() {
                Toast.makeText(source_class, "Ad completed, now give reward to user", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoClosed() {
            }
        };
        rewardedVideoAd.loadAd(
                rewardedVideoAd.buildLoadAdConfig()
                        .withAdListener(rewardedVideoAdListener)
                        .build());

    }

    public interface UserEarnRewardListener {
        void onEarnReward();
    }
}
