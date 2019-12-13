package com.bakanime.smaato;

import com.facebook.react.bridge.*;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.smaato.sdk.interstitial.*;

import javax.annotation.Nullable;

public class BLSSmaatoIntertititalManager extends ReactContextBaseJavaModule implements LifecycleEventListener, EventListener {

    private ReactApplicationContext mContext;

    public BLSSmaatoIntertititalManager(ReactApplicationContext reactContext) {
        super(reactContext);
        mContext = reactContext;
    }


    @Override
    public String getName() {
        return "TohkaIntertitital";
    }

    @ReactMethod
    public void showAd(String _id){
        Interstitial.loadAd(_id,this);
    }

    private void sendEvent(String eventName, @Nullable WritableMap params){
        mContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName,params);
    }

    @Override
    public void onAdLoaded(InterstitialAd interstitialAd) {
        interstitialAd.showAd(getCurrentActivity());
        sendEvent("onSmaatoIntertitial",null);
    }

    @Override
    public void onAdFailedToLoad(InterstitialRequestError interstitialRequestError) {
        WritableMap parmas = Arguments.createMap();
        parmas.putString("onError", String.valueOf(interstitialRequestError.getInterstitialError()));
        parmas.putString("onErrorSpaceId",interstitialRequestError.getAdSpaceId());
        parmas.putString("onErrorPublisherId",interstitialRequestError.getPublisherId());
        sendEvent("onSmaatoIntertitial",parmas);
        mContext = null;
    }

    @Override
    public void onAdError(InterstitialAd interstitialAd, InterstitialError interstitialError) {
        WritableMap parmas = Arguments.createMap();
        parmas.putString("onError", interstitialError.toString());
        sendEvent("onSmaatoIntertitial",parmas);
        mContext = null;
    }

    @Override
    public void onAdOpened(InterstitialAd interstitialAd) {
        sendEvent("onSmaatoIntertitial",null);
    }

    @Override
    public void onAdClosed(InterstitialAd interstitialAd) {
        sendEvent("onSmaatoIntertitial",null);
    }

    @Override
    public void onAdClicked(InterstitialAd interstitialAd) {
        sendEvent("onSmaatoIntertitial",null);
    }

    @Override
    public void onAdImpression(InterstitialAd interstitialAd) {
        sendEvent("onSmaatoIntertitial",null);
    }

    @Override
    public void onAdTTLExpired(InterstitialAd interstitialAd) {
        sendEvent("onSmaatoIntertitial",null);
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
}
