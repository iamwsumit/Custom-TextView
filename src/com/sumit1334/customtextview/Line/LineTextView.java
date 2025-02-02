package com.sumit1334.customtextview.Line;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.sumit1334.customtextview.Main.AnimationListener;
import com.sumit1334.customtextview.Main.HTextView;

public class LineTextView extends HTextView {

    private LineText lineText;

    public LineTextView(Context context) {
        this(context, null);
    }

    public LineTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    @Override
    public void setAnimationListener(AnimationListener listener) {
        lineText.setAnimationListener(listener);
    }

    public void setLineColor(int color) {
        lineText.setLineColor(color);
    }

    public float getLineWidth() {
        return lineText.getLineWidth();
    }

    public void setLineWidth(float lineWidth) {
        lineText.setLineWidth(lineWidth);
    }

    public float getAnimationDuration() {
        return lineText.getAnimationDuration();
    }

    public void setAnimationDuration(float animationDuration) {
        lineText.setAnimationDuration(animationDuration);
    }

    @Override
    public void setProgress(float progress) {
        lineText.setProgress(progress);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        lineText = new LineText();
        lineText.init(this, attrs, defStyleAttr);
    }

    @Override
    public void animateText(CharSequence text) {
        lineText.animateText(text);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        lineText.onDraw(canvas);
    }

}

