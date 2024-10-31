package com.sumit1334.customtextview.Main;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public abstract class HTextView extends TextView {
    public HTextView(Context context) {
        this(context, (AttributeSet)null);
    }

    public HTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setAnimationListener(AnimationListener var1);

    public abstract void setProgress(float var1);

    public abstract void animateText(CharSequence var1);
}
