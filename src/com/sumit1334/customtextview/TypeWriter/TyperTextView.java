package com.sumit1334.customtextview.TypeWriter;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.AttributeSet;
import com.sumit1334.customtextview.Main.*;
import java.util.Random;

public class TyperTextView extends HTextView {
    public static final int INVALIDATE = 1895;
    private Random random;
    private CharSequence mText;
    private Handler handler;
    private int charIncrease;
    private int typerSpeed;
    private AnimationListener animationListener;

    public TyperTextView(Context context) {
        this(context, (AttributeSet)null);
    }

    public TyperTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TyperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.typerSpeed = 4;
        this.charIncrease = 1;
        this.random = new Random();
        this.mText = this.getText();
        this.handler = new Handler(new Callback() {
            public boolean handleMessage(Message msg) {
                int currentLength = TyperTextView.this.getText().length();
                if (currentLength < TyperTextView.this.mText.length()) {
                    if (currentLength + TyperTextView.this.charIncrease > TyperTextView.this.mText.length()) {
                        TyperTextView.this.charIncrease = TyperTextView.this.mText.length() - currentLength;
                    }

                    TyperTextView.this.append(TyperTextView.this.mText.subSequence(currentLength, currentLength + TyperTextView.this.charIncrease));
                    long randomTime = (long)(TyperTextView.this.typerSpeed + TyperTextView.this.random.nextInt(TyperTextView.this.typerSpeed));
                    Message message = Message.obtain();
                    message.what = 1895;
                    TyperTextView.this.handler.sendMessageDelayed(message, randomTime);
                    return false;
                } else {
                    if (TyperTextView.this.animationListener != null) {
                        TyperTextView.this.animationListener.onAnimationEnd(TyperTextView.this);
                    }

                    return false;
                }
            }
        });
    }

    public void setAnimationListener(AnimationListener listener) {
        this.animationListener = listener;
    }

    public int getTyperSpeed() {
        return this.typerSpeed;
    }

    public void setTyperSpeed(int typerSpeed) {
        this.typerSpeed = typerSpeed;
    }

    public int getCharIncrease() {
        return this.charIncrease;
    }

    public void setCharIncrease(int charIncrease) {
        this.charIncrease = charIncrease;
    }

    public void setProgress(float progress) {
        this.setText(this.mText.subSequence(0, (int)((float)this.mText.length() * progress)));
    }

    public void animateText(CharSequence text) {
        if (text == null) {
            throw new RuntimeException("text must not  be null");
        } else {
            this.mText = text;
            this.setText("");
            Message message = Message.obtain();
            message.what = 1895;
            this.handler.sendMessage(message);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.handler.removeMessages(1895);
    }
}

