package com.sdk.dierct.link.open.on.fail.sdk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.sdk.direct.link.open.on.fail.sdk.R;

public class NativeAd_Static {
    Context context;

    public NativeAd_Static(Context context) {
        this.context = context;
    }

    public void Native_Ads(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_native_ads, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }

    public void Native_Banner_Ads(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_native_banner_ads, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }

    public void Adaptive_Banner(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_static_adaptive_ads, null);
        viewGroup.removeAllViews();
        ShimmerFrameLayout shimmerContainer = view.findViewById(R.id.shimmer_view_container);
        shimmerContainer.startShimmer();
        viewGroup.addView(view);
    }
}
