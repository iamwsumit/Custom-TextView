package com.sumit1334.customtextview.Main;

import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class DisplayUtils {
    public DisplayUtils() {
    }

    public static DisplayMetrics getDisplayMetrics() {
        return Resources.getSystem().getDisplayMetrics();
    }

    public static int dp2px(float dp) {
        return Math.round(dp * getDisplayMetrics().density);
    }

    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }
}