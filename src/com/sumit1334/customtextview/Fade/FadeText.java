package com.sumit1334.customtextview.Fade;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import com.sumit1334.customtextview.Main.DefaultAnimatorListener;
import com.sumit1334.customtextview.Main.HText;
import com.sumit1334.customtextview.Main.HTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FadeText extends HText {
    private Random random;
    private int animationDuration;
    private List<Integer> alphaList;
    private int DEFAULT_DURATION = 2000;

    public FadeText() {
    }

    public void init(HTextView hTextView, AttributeSet attrs, int defStyle) {
        super.init(hTextView, attrs, defStyle);
        this.animationDuration = 2000;
    }

    public void setAnimationDuration(int animationDuration) {
        this.animationDuration = animationDuration;
    }

    protected void initVariables() {
        this.random = new Random();
        if (this.alphaList == null) {
            this.alphaList = new ArrayList();
        }

        this.alphaList.clear();

        for(int i = 0; i < this.mHTextView.getText().length(); ++i) {
            int randomNumber = this.random.nextInt(2);
            if ((i + 1) % (randomNumber + 2) == 0) {
                if ((i + 1) % (randomNumber + 4) == 0) {
                    this.alphaList.add(55);
                } else {
                    this.alphaList.add(255);
                }
            } else if ((i + 1) % (randomNumber + 4) == 0) {
                this.alphaList.add(55);
            } else {
                this.alphaList.add(0);
            }
        }

    }

    protected void animateStart(CharSequence text) {
        this.initVariables();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(new float[]{0.0F, 1.0F}).setDuration((long)this.animationDuration);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addListener(new DefaultAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                if (FadeText.this.animationListener != null) {
                    FadeText.this.animationListener.onAnimationEnd(FadeText.this.mHTextView);
                }

            }
        });
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                FadeText.this.progress = (Float)animation.getAnimatedValue();
                FadeText.this.mHTextView.invalidate();
            }
        });
        valueAnimator.start();
    }

    protected void animatePrepare(CharSequence text) {
    }

    protected void drawFrame(Canvas canvas) {
        Layout layout = this.mHTextView.getLayout();
        int gapIndex = 0;

        for(int i = 0; i < layout.getLineCount(); ++i) {
            int lineStart = layout.getLineStart(i);
            int lineEnd = layout.getLineEnd(i);
            float lineLeft = layout.getLineLeft(i);
            float lineBaseline = (float)layout.getLineBaseline(i);
            String lineText = this.mText.subSequence(lineStart, lineEnd).toString();

            for(int c = 0; c < lineText.length(); ++c) {
                int alpha = (Integer)this.alphaList.get(gapIndex);
                this.mPaint.setAlpha((int)((float)(255 - alpha) * this.progress + (float)alpha));
                canvas.drawText(String.valueOf(lineText.charAt(c)), lineLeft, lineBaseline, this.mPaint);
                lineLeft += (Float)this.gapList.get(gapIndex++);
            }
        }

    }
}

