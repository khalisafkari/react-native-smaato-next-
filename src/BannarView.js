import React from 'react'
import {
    requireNativeComponent
} from 'react-native'


const TKBannerView = requireNativeComponent("TKBannerView",BannerView);

/*
    size = "XX_LARGE_320x50" | "MEDIUM_RECTANGLE_300x250" | "LEADERBOARD_728x90" | "SKYSCRAPER_120x600" 
*/

const BannerView = (props) => {

    const { style, placementId, size} = props;

    return(
        <TKBannerView
            style={style}
            placementId={placementId}
            size={size}
            {...props}
        />
    )
}

export default BannerView;