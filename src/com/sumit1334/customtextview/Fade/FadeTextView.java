package com.sumit1334.customtextview.Fade;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.sumit1334.customtextview.Main.AnimationListener;
import com.sumit1334.customtextview.Main.HTextView;

public class FadeTextView extends HTextView {
    private FadeText fadeText;

    public FadeTextView(Context context) {
        this(context, null);
    }

    public FadeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FadeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    public void setAnimationListener(AnimationListener listener) {
        fadeText.setAnimationListener(listener);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        fadeText = new FadeText();
        fadeText.init(this, attrs, defStyleAttr);
    }
    public void setAnimationDuration(int animationDuration) {
        fadeText.setAnimationDuration(animationDuration);
    }

    @Override
    public void setProgress(float progress) {
        fadeText.setProgress(progress);
    }

    @Override
    public void animateText(CharSequence text) {
        fadeText.animateText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        fadeText.onDraw(canvas);
    }
}
