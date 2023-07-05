package com.sdk.dierct.link.open.on.fail.sdk;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.browser.customtabs.CustomTabsIntent;

import com.sdk.direct.link.open.on.fail.sdk.R;

public class Con_stant {
    public static int Alt_Cnt_Inter = 1;
    public static int Alt_Back_Cnt_Inter = 1;
    public static int Alt_Cnt_Reward_Inter = 1;
    public static int Alt_Cnt_Reward = 1;
    public static int Alt_Cnt_OpenAd = 1;
    public static int Alt_Cnt_Native = 1;
    public static int Alt_Cnt_Native_Banner = 1;
    public static int Alt_Cnt_Banner = 1;
    public static boolean IS_TIME_INTERVAL = true;
    public static int Front_Counter = 0;
    public static int FullAdCounter = 0;
    public static int NAtiveAdCounter = 0;
    public static int NAtiveBannerAdCounter = 0;
    public static int OpenAdCounter = 0;
    public static int BannerAdCounter = 0;
    public static int Back_Counter = 0;
    public static int NativeCountIncr = 0;

    public static int[] qureka_icon = {R.drawable.qureka_icon1, R.drawable.qureka_icon2, R.drawable.qureka_icon3, R.drawable.qureka_icon4, R.drawable.qureka_icon5};
    public static int[] predchamp_icon = {R.drawable.predchamp_icon1, R.drawable.predchamp_icon2, R.drawable.predchamp_icon3, R.drawable.predchamp_icon4, R.drawable.predchamp_icon5};

    public static int[] qureka_native = {R.drawable.qureka_ban1, R.drawable.qureka_ban2, R.drawable.qureka_ban3, R.drawable.qureka_ban4, R.drawable.qureka_ban5};
    public static int[] predchamp_native = {R.drawable.predchamp_ban1, R.drawable.predchamp_ban2, R.drawable.predchamp_ban3, R.drawable.predchamp_ban4, R.drawable.predchamp_ban5};

    public static int[] qureka_banner = {R.drawable.qureka_banner1, R.drawable.qureka_banner2, R.drawable.qureka_banner3, R.drawable.qureka_banner4, R.drawable.qureka_banner5};
    public static int[] predchamp_banner = {R.drawable.predchamp_banner1, R.drawable.predchamp_banner2, R.drawable.predchamp_banner3, R.drawable.predchamp_banner4, R.drawable.predchamp_banner5};

    public static String[] qureka_header = {"Play & Win Coins Daily.", "Pool prize is 50,000 coins", "Aaj Math Quiz khela kya?", "Time to Win Now!", "Prize Pool: 50,000 Coins| No install required"};
    public static String[] qureka_description = {"Come play Cricket contests running for 50,000 Coins daily.", "Sharpen your Cricket knowledge & win now", "Play and win coins now", "Play Bollywood Quizzes  & win coins daily", "Play IPL contest! Khelo aur jeeto ."};

    public static String[] predchamp_header = {"Play Quiz & Win Now", "SSC Exam Quiz for 50,000 Coins is Live", "Jeeto 10,000 Coins Abhi!", "Mega quiz for 5,00,000 coins open", "Tech quiz for 50,000 coins open"};
    public static String[] predchamp_description = {"Play SSC Exam Quiz & Win Upto 50,000 Coins", "Play Quiz & Win Now", "Play GK, Math & other quizzes & Win Now", "Your chance of winning is high here! Play Now", "Test your tech skills & win now"};
    public static String Adval = "0";

    public static void Share_App(Context context, String appname, String shortlink) {
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, appname);
            String shareMessage = "\nLet me recommend you this application\n\n";
            shareMessage = shareMessage + shortlink + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            context.startActivity(Intent.createChooser(shareIntent, "choose one"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Rate_App(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Unable to Find Market App !", Toast.LENGTH_LONG).show();
        }
    }

    public static void More_App(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=" + new Apps_Preference(context).get_Account()));
        context.startActivity(intent);
    }

    public static void Privacy_App(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(new Apps_Preference(context).get_Privacy_Policy()));
        context.startActivity(intent);
    }

    public static void Open_Qureka(Context context) {
        String URL = new Apps_Preference(context).get_Qureka_Link();
        try {
            CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
            CustomTabsIntent customTabsIntent = builder.build();
            customTabsIntent.launchUrl(context, Uri.parse(URL));
        } catch (Exception e) {

        }
    }

    public static void Open_Url_View(Context context) {
        if (new Apps_Preference(context).getDirect_link().equalsIgnoreCase("on")) {
            String URL = new Apps_Preference(context).getUrl_for_ads();
            try {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(URL));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
