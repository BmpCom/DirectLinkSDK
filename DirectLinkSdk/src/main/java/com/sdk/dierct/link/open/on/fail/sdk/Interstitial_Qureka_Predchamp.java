package com.sdk.dierct.link.open.on.fail.sdk;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sdk.direct.link.open.on.fail.sdk.R;

import java.util.Random;

public class Interstitial_Qureka_Predchamp {

    public static void Show_Qureka_Predchamp_Ads(Activity source_class, Interstitial_Full_Ads.AdCloseListener adCloseListener) {
        Apps_Preference preference = new Apps_Preference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                Apps_Preference.isFullScreenShow = true;
                final Dialog dialog = new Dialog(source_class, R.style.transparent_dialog);
                dialog.setContentView(R.layout.custome_interstitial);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                Glide.with(source_class).load(Con_stant.qureka_icon[i1]).into(((ImageView) dialog.findViewById(R.id.img_icon)));
                Glide.with(source_class).load(Con_stant.qureka_native[i1]).into(((ImageView) dialog.findViewById(R.id.img_banner)));
                ((TextView) dialog.findViewById(R.id.txt_main_title)).setText("Qureka Lite/Play Game");
                ((TextView) dialog.findViewById(R.id.txt_title)).setText(Con_stant.qureka_header[i1]);
                ((TextView) dialog.findViewById(R.id.txt_description)).setText(Con_stant.qureka_description[i1]);
                dialog.setOnDismissListener(dialog1 -> {
                    Apps_Preference.isFullScreenShow = false;
                    if (adCloseListener != null) {
                        adCloseListener.onAdClosed();
                    }
                });
                dialog.findViewById(R.id.img_close).setOnClickListener(v -> dialog.dismiss());
                dialog.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(source_class));
                dialog.show();
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                Apps_Preference.isFullScreenShow = true;
                final Dialog dialog = new Dialog(source_class, R.style.transparent_dialog);
                dialog.setContentView(R.layout.custome_interstitial);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                Glide.with(source_class).load(Con_stant.predchamp_icon[i1]).into(((ImageView) dialog.findViewById(R.id.img_icon)));
                Glide.with(source_class).load(Con_stant.predchamp_native[i1]).into(((ImageView) dialog.findViewById(R.id.img_banner)));
                ((TextView) dialog.findViewById(R.id.txt_main_title)).setText("Predchamp/Play Game");
                ((TextView) dialog.findViewById(R.id.txt_title)).setText(Con_stant.predchamp_header[i1]);
                ((TextView) dialog.findViewById(R.id.txt_description)).setText(Con_stant.predchamp_description[i1]);
                dialog.setOnDismissListener(dialog12 -> {
                    Apps_Preference.isFullScreenShow = false;
                    if (adCloseListener != null) {
                        adCloseListener.onAdClosed();
                    }
                });
                dialog.findViewById(R.id.img_close).setOnClickListener(v -> dialog.dismiss());
                dialog.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(source_class));
                dialog.show();
            } else {
                if (adCloseListener != null) {
                    adCloseListener.onAdClosed();
                }
            }
        } else {
            if (adCloseListener != null) {
                adCloseListener.onAdClosed();
            }
        }
    }

    public static void Show_Qureka_Predchamp_Ads(Activity source_class) {
        Apps_Preference preference = new Apps_Preference(source_class);
        if (preference.get_Ad_Status().equalsIgnoreCase("on")) {
            if (preference.get_Qureka_Flag().equalsIgnoreCase("qureka")) {
                Apps_Preference.isFullScreenShow = true;
                Dialog dialog = new Dialog(source_class, R.style.transparent_dialog);
                dialog.setContentView(R.layout.custome_interstitial);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                Glide.with(source_class).load(Con_stant.qureka_icon[i1]).into(((ImageView) dialog.findViewById(R.id.img_icon)));
                Glide.with(source_class).load(Con_stant.qureka_native[i1]).into(((ImageView) dialog.findViewById(R.id.img_banner)));
                ((TextView) dialog.findViewById(R.id.txt_main_title)).setText("Qureka Lite/Play Game");
                ((TextView) dialog.findViewById(R.id.txt_title)).setText(Con_stant.qureka_header[i1]);
                ((TextView) dialog.findViewById(R.id.txt_description)).setText(Con_stant.qureka_description[i1]);
                dialog.setOnDismissListener(dialog12 -> {
                    Apps_Preference.isFullScreenShow = false;
                });
                dialog.findViewById(R.id.img_close).setOnClickListener(v -> dialog.dismiss());
                dialog.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(source_class));
                dialog.show();
            } else if (preference.get_Qureka_Flag().equalsIgnoreCase("predchamp")) {
                Apps_Preference.isFullScreenShow = true;
                Dialog dialog = new Dialog(source_class, R.style.transparent_dialog);
                dialog.setContentView(R.layout.custome_interstitial);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                Random r = new Random();
                int i1 = r.nextInt(4 + 1);
                Glide.with(source_class).load(Con_stant.predchamp_icon[i1]).into(((ImageView) dialog.findViewById(R.id.img_icon)));
                Glide.with(source_class).load(Con_stant.predchamp_native[i1]).into(((ImageView) dialog.findViewById(R.id.img_banner)));
                ((TextView) dialog.findViewById(R.id.txt_main_title)).setText("Predchamp/Play Game");
                ((TextView) dialog.findViewById(R.id.txt_title)).setText(Con_stant.predchamp_header[i1]);
                ((TextView) dialog.findViewById(R.id.txt_description)).setText(Con_stant.predchamp_description[i1]);
                dialog.setOnDismissListener(dialog1 -> {
                    Apps_Preference.isFullScreenShow = false;
                });
                dialog.findViewById(R.id.img_close).setOnClickListener(v -> dialog.dismiss());
                dialog.findViewById(R.id.btn_install).setOnClickListener(v -> Con_stant.Open_Qureka(source_class));
                dialog.show();
            }
        }
    }
}
