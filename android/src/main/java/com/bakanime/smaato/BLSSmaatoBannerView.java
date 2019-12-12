package com.bakanime.smaato;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.widget.LinearLayout;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.smaato.sdk.banner.ad.BannerAdSize;
import com.smaato.sdk.banner.widget.BannerError;
import com.smaato.sdk.banner.widget.BannerView;

import javax.annotation.Nonnull;

public class BLSSmaatoBannerView extends LinearLayout implements BannerView.EventListener, LifecycleEventListener {


    private RCTEventEmitter mEventEmitter;
    private ThemedReactContext mUIManager;
    private String mPlacementId;
    private BannerAdSize mySize;
    private BannerView myAdView;

    private final Runnable mLayoutRunnable = new Runnable() {
        @Override
        public void run() {
            measure(MeasureSpec.makeMeasureSpec(getWidth(),MeasureSpec.EXACTLY),MeasureSpec.makeMeasureSpec(getHeight(),MeasureSpec.EXACTLY));
            layout(getLeft(),getTop(),getRight(),getBottom());
        }
    };


    public BLSSmaatoBannerView(ThemedReactContext context) {
        super(context);
        mUIManager = context;
        mUIManager.addLifecycleEventListener(this);
        mEventEmitter = context.getJSModule(RCTEventEmitter.class);
    }

    public void setPlacementId(String placementId) {
        mPlacementId = placementId;
        createAdViewIfCan();
    }

    public void setSize(BannerAdSize adSize){
        mySize = adSize;
        createAdViewIfCan();
    }

    private void createAdViewIfCan(){
        if (myAdView == null && mPlacementId != null && mySize != null){
            myAdView = new BannerView(this.getContext());
            myAdView.setEventListener(this);
            removeAllViews();
            addView(myAdView);
            myAdView.loadAd(mPlacementId,mySize);
        }
    }


    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {
        if (myAdView != null){
            myAdView.destroy();
        }
        mUIManager.removeLifecycleEventListener(this);
        mUIManager = null;

    }

    @Override
    public void onAdLoaded(@Nonnull BannerView bannerView) {
        mEventEmitter.receiveEvent(getId(), "onAdLoad", null);
    }

    @Override
    public void onAdFailedToLoad(@Nonnull BannerView bannerView, BannerError bannerError) {
        Bundle event = new Bundle();
        event.putInt("errorCode", Integer.parseInt(bannerError.toString()));
        event.putString("errorMessage",bannerError.toString());
        mEventEmitter.receiveEvent(getId(),"onAdError",null);
    }

    @Override
    public void onAdImpression(@Nonnull BannerView bannerView) {
        mEventEmitter.receiveEvent(getId(), "onLoggingImpression", null);
    }

    @Override
    public void onAdClicked(@Nonnull BannerView bannerView) {
        mEventEmitter.receiveEvent(getId(), "onAdPress", null);
    }

    @Override
    public void onAdTTLExpired(@NonNull BannerView bannerView) {
        mEventEmitter.receiveEvent(getId(), "onExpired", null);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        post(mLayoutRunnable);
    }
}
