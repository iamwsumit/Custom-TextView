package com.sumit1334.customtextview.Rainbow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import com.sumit1334.customtextview.Main.AnimationListener;
import com.sumit1334.customtextview.Main.HTextView;

public class RainbowTextView extends HTextView {
    private Matrix mMatrix;
    private float mTranslate;
    private float colorSpeed;
    private float colorSpace;
    private int[] colors;
    private LinearGradient mLinearGradient;

    public RainbowTextView(Context context) {
        this(context, (AttributeSet)null);
    }

    public RainbowTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RainbowTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.colors = new int[]{-54494, -32990, -1179870, -14483678, -14486273, -14534145, -11271945};
        this.init(attrs, defStyleAttr);
    }

    public void setAnimationListener(AnimationListener listener) {
        throw new UnsupportedOperationException("Invalid operation for rainbow");
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        this.colorSpace = 150f;
        this.colorSpeed = 4f;
        this.mMatrix = new Matrix();
        this.initPaint();
    }

    public float getColorSpace() {
        return this.colorSpace;
    }

    public void setColorSpace(float colorSpace) {
        this.colorSpace = colorSpace;
    }

    public float getColorSpeed() {
        return this.colorSpeed;
    }

    public void setColorSpeed(float colorSpeed) {
        this.colorSpeed = colorSpeed;
    }

    public void setColors(int... colors) {
        this.colors = colors;
        this.initPaint();
    }

    private void initPaint() {
        this.mLinearGradient = new LinearGradient(0.0F, 0.0F, this.colorSpace, 0.0F, this.colors, (float[])null, TileMode.MIRROR);
        this.getPaint().setShader(this.mLinearGradient);
    }

    public void setProgress(float progress) {
    }

    public void animateText(CharSequence text) {
        this.setText(text);
    }

    protected void onDraw(Canvas canvas) {
        if (this.mMatrix == null) {
            this.mMatrix = new Matrix();
        }

        this.mTranslate += this.colorSpeed;
        this.mMatrix.setTranslate(this.mTranslate, 0.0F);
        this.mLinearGradient.setLocalMatrix(this.mMatrix);
        super.onDraw(canvas);
        this.postInvalidateDelayed(100L);
    }
}

