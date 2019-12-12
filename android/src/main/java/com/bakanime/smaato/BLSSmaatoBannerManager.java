package com.bakanime.smaato;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.smaato.sdk.banner.ad.BannerAdSize;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class BLSSmaatoBannerManager extends SimpleViewManager<BLSSmaatoBannerView> {

    @Nonnull
    @Override
    public String getName() {
        return "TKBannerView";
    }

    @Nonnull
    @Override
    protected BLSSmaatoBannerView createViewInstance(ThemedReactContext reactContext) {
        return new BLSSmaatoBannerView(reactContext);
    }

    @ReactProp(name = "placementId")
    public void setPlacementId(BLSSmaatoBannerView view, String placementId) {
        view.setPlacementId(placementId);
    }

    @ReactProp(name = "size")
    public void setSize(BLSSmaatoBannerView view, String size) {
        BannerAdSize adSize = null;
        switch (size) {
            case "XX_LARGE_320x50":
                adSize = BannerAdSize.XX_LARGE_320x50;
//                adSize = AdSize.BANNER_HEIGHT_90;
                break;
            case "MEDIUM_RECTANGLE_300x250":
                adSize = BannerAdSize.MEDIUM_RECTANGLE_300x250;
                break;
            case "LEADERBOARD_728x90":
                adSize = BannerAdSize.LEADERBOARD_728x90;
                break;
            case "SKYSCRAPER_120x600":
            default:
                adSize = BannerAdSize.SKYSCRAPER_120x600;
                break;
        }
        view.setSize(adSize);
    }

    @Nullable
    @Override
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                "onAdLoad",
                MapBuilder.of("registrationName", "onAdLoad"),
                "onAdError",
                MapBuilder.of("registrationName", "onAdError"),
                "onLoggingImpression",
                MapBuilder.of("registrationName", "onLoggingImpression"),
                "onExpired",
                MapBuilder.of("registrationName", "onExpired"),
                "onAdPress",
                MapBuilder.of("registrationName","onAdPress")
        );
    }
}
