package com.bakanime.smaato;

import com.facebook.react.bridge.*;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.smaato.sdk.rewarded.*;

import javax.annotation.Nullable;

public class BLSSmaatoReward extends ReactContextBaseJavaModule implements EventListener, LifecycleEventListener {

    ReactApplicationContext mContext;


    public BLSSmaatoReward(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }

    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {

    }

    @Override
    public String getName() {
        return "TohkaReward";
    }

    @ReactMethod
    public void showAd(String _id){
        RewardedInterstitial.loadAd(_id,this);
    }


    @Override
    public void onAdLoaded(RewardedInterstitialAd rewardedInterstitialAd) {
        rewardedInterstitialAd.showAd();
    }

    private void sentEvent(String eventName, @Nullable WritableMap params){
        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName,params);
    }

    @Override
    public void onAdFailedToLoad(RewardedRequestError rewardedRequestError) {
        WritableMap params = Arguments.createMap();
        params.putString("onFailed", String.valueOf(rewardedRequestError.getRewardedError()));
        params.putString("onSpaceId",rewardedRequestError.getAdSpaceId());
        params.putString("onPubId",rewardedRequestError.getPublisherId());
        sentEvent("onSmaatoReward",params);
        mContext.removeLifecycleEventListener(this);
        mContext = null;
    }

    @Override
    public void onAdError(RewardedInterstitialAd rewardedInterstitialAd, RewardedError rewardedError) {
        WritableMap params = Arguments.createMap();
        params.putString("onError",rewardedError.toString());
        sentEvent("onSmaatoReward",params);
        mContext.removeLifecycleEventListener(this);
        mContext = null;
    }

    @Override
    public void onAdClosed(RewardedInterstitialAd rewardedInterstitialAd) {
        sentEvent("onSmaatoReward",null);
    }

    @Override
    public void onAdClicked(RewardedInterstitialAd rewardedInterstitialAd) {
        sentEvent("onSmaatoReward",null);
    }

    @Override
    public void onAdStarted(RewardedInterstitialAd rewardedInterstitialAd) {
        sentEvent("onSmaatoReward",null);
    }

    @Override
    public void onAdReward(RewardedInterstitialAd rewardedInterstitialAd) {
        sentEvent("onSmaatoReward",null);
    }

    @Override
    public void onAdTTLExpired(RewardedInterstitialAd rewardedInterstitialAd) {
        sentEvent("onSmaatoReward",null);
    }
}
