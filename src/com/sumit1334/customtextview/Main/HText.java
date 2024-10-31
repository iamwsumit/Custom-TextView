package com.sumit1334.customtextview.Main;

import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

import java.util.ArrayList;
import java.util.List;

public abstract class HText implements IHText {
    protected int mHeight;
    protected int mWidth;
    protected CharSequence mText;
    protected CharSequence mOldText;
    protected TextPaint mPaint;
    protected TextPaint mOldPaint;
    protected HTextView mHTextView;
    protected List<Float> gapList = new ArrayList();
    protected List<Float> oldGapList = new ArrayList();
    protected float progress;
    protected float mTextSize;
    protected float oldStartX = 0.0F;
    protected AnimationListener animationListener;

    public HText() {
    }

    public void setProgress(float progress) {
        this.progress = progress;
        this.mHTextView.invalidate();
    }

    public void init(HTextView hTextView, AttributeSet attrs, int defStyle) {
        this.mHTextView = hTextView;
        this.mOldText = "";
        this.mText = hTextView.getText();
        this.progress = 1.0F;
        this.mPaint = new TextPaint(1);
        this.mOldPaint = new TextPaint(this.mPaint);
        this.mHTextView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (VERSION.SDK_INT >= 16) {
                    HText.this.mHTextView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    HText.this.mHTextView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }

                HText.this.mTextSize = HText.this.mHTextView.getTextSize();
                HText.this.mWidth = HText.this.mHTextView.getWidth();
                HText.this.mHeight = HText.this.mHTextView.getHeight();
                HText.this.oldStartX = 0.0F;

//                try {
////                    int layoutDirection = ViewCompat.getLayoutDirection(HText.this.mHTextView);
////                    HText.this.oldStartX = layoutDirection == 0 ? HText.this.mHTextView.getLayout().getLineLeft(0) : HText.this.mHTextView.getLayout().getLineRight(0);
//                } catch (Exception var2) {
//                    var2.printStackTrace();
//                }

                HText.this.initVariables();
            }
        });
        this.prepareAnimate();
    }

    private void prepareAnimate() {
        this.mTextSize = this.mHTextView.getTextSize();
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setColor(this.mHTextView.getCurrentTextColor());
        this.mPaint.setTypeface(this.mHTextView.getTypeface());
        this.gapList.clear();

        int i;
        for(i = 0; i < this.mText.length(); ++i) {
            this.gapList.add(this.mPaint.measureText(String.valueOf(this.mText.charAt(i))));
        }

        this.mOldPaint.setTextSize(this.mTextSize);
        this.mOldPaint.setColor(this.mHTextView.getCurrentTextColor());
        this.mOldPaint.setTypeface(this.mHTextView.getTypeface());
        this.oldGapList.clear();

        for(i = 0; i < this.mOldText.length(); ++i) {
            this.oldGapList.add(this.mOldPaint.measureText(String.valueOf(this.mOldText.charAt(i))));
        }

    }

    public void animateText(CharSequence text) {
        this.mHTextView.setText(text);
        this.mOldText = this.mText;
        this.mText = text;
        this.prepareAnimate();
        this.animatePrepare(text);
        this.animateStart(text);
    }

    public void setAnimationListener(AnimationListener listener) {
        this.animationListener = listener;
    }

    public void onDraw(Canvas canvas) {
        this.drawFrame(canvas);
    }

    protected abstract void initVariables();

    protected abstract void animateStart(CharSequence var1);

    protected abstract void animatePrepare(CharSequence var1);

    protected abstract void drawFrame(Canvas var1);
}

