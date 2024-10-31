package com.sumit1334.customtextview.Main;


import android.graphics.Canvas;
import android.util.AttributeSet;

interface IHText {
    void init(HTextView var1, AttributeSet var2, int var3);

    void animateText(CharSequence var1);

    void onDraw(Canvas var1);

    void setAnimationListener(AnimationListener var1);
}

