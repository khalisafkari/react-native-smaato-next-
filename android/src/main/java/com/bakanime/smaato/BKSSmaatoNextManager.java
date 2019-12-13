package com.bakanime.smaato;

import android.view.View;
import android.support.v7.widget.AppCompatCheckBox;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

public class BKSSmaatoNextManager extends SimpleViewManager<View> {

    public static final String REACT_CLASS = "BKSSmaatoNext";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public View createViewInstance(ThemedReactContext c) {
        // TODO: Implement some actually useful functionality
        AppCompatCheckBox cb = new AppCompatCheckBox(c);
        cb.setChecked(true);
        return cb;
    }
}
