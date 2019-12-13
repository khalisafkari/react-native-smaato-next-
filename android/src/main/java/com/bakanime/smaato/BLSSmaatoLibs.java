package com.bakanime.smaato;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.smaato.sdk.core.LatLng;
import com.smaato.sdk.core.SmaatoSdk;

import java.util.Date;

public class BLSSmaatoLibs extends ReactContextBaseJavaModule implements LifecycleEventListener {

    ReactApplicationContext mReactContext;
    private LatLng latLng;

    public BLSSmaatoLibs(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "TohkaLib";
    }

    @ReactMethod
    public void setKeywords(String  keyword){
        SmaatoSdk.setKeywords(keyword);
    }

    @ReactMethod
    public void setAge(int age){
        SmaatoSdk.setAge(age);
    }

    @ReactMethod
    public void setLatLng(double latitude,double longitude,float accuracy,boolean timestamp){
        LatLng location = null;
        if (timestamp){
            final long time = new Date().getTime();
            location = new LatLng(latitude,longitude,accuracy,time);
        }else{
            location = new LatLng(latitude,longitude,accuracy,0);
        }
        SmaatoSdk.setLatLng(location);
    }

    @ReactMethod
    public void setRegion(String region){
        SmaatoSdk.setRegion(region);
    }

    @ReactMethod
    public void setLanguage(String leLanguage){
        SmaatoSdk.setLanguage(leLanguage);
    }

    @ReactMethod
    public void setCoppa(boolean coppa){
        SmaatoSdk.setCoppa(coppa);
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
