package com.sumit1334.customtextview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.sumit1334.customtextview.Evaprate.EvaporateTextView;
import com.sumit1334.customtextview.Fade.FadeTextView;
import com.sumit1334.customtextview.Fall.FallTextView;
import com.sumit1334.customtextview.Line.LineTextView;
import com.sumit1334.customtextview.Main.AnimationListener;
import com.sumit1334.customtextview.Main.HTextView;
import com.sumit1334.customtextview.Partical.MovingStrategy.*;
import com.sumit1334.customtextview.Partical.Object.ParticleTextViewConfig;
import com.sumit1334.customtextview.Partical.View.ParticleTextView;
import com.sumit1334.customtextview.Rainbow.RainbowTextView;
import com.sumit1334.customtextview.Scale.ScaleTextView;
import com.sumit1334.customtextview.TypeWriter.TyperTextView;


import java.util.HashMap;

public class CustomTextView extends AndroidNonvisibleComponent {

  private Context context;
  private ComponentContainer container;
  private HashMap<String, FallTextView> fallComponent = new HashMap<>();
  private HashMap<String, FadeTextView> fadeComponent = new HashMap<>();
  private HashMap<String, ScaleTextView> scaleComponent = new HashMap<>();
  private HashMap<String, RainbowTextView> rainbowComponent = new HashMap<>();
  private HashMap<String, TyperTextView> typerComponent = new HashMap<>();
  private HashMap<String, LineTextView> lineComponent = new HashMap<>();
  private HashMap<String, EvaporateTextView> evaporateComponent=new HashMap<>();
  private HashMap<String, Object> components = new HashMap<>();
  private HashMap<Object, String> idList = new HashMap<>();
  private HashMap<String, ParticleTextView> particalComponent=new HashMap<>();

  public CustomTextView(ComponentContainer container) {
    super(container.$form());
    this.context = container.$context();
    this.container=container;
  }
  @SimpleEvent
  public void Click(String id) {
    EventDispatcher.dispatchEvent(this, "Click", id);
  }
  @SimpleEvent
  public void TypingFinished(String id) {
    EventDispatcher.dispatchEvent(this, "TypingFinished", id);
  }
  @SimpleEvent
  public void AnimationStart(String id) {
    EventDispatcher.dispatchEvent(this, "AnimationStart", id);
  }
  @SimpleEvent
  public void AnimationEnd(String id) {
    EventDispatcher.dispatchEvent(this, "AnimationEnd", id);
  }
  @SimpleEvent
  public void LongClick(String id) {
    EventDispatcher.dispatchEvent(this, "LongClick", id);
  }
  @SimpleFunction
  public Object GetTextView(String id) {
        return this.components.get(id);
    }
  @SimpleFunction
  public String GetId(Object textView) {
    return this.idList.get(textView);
  }
  @SimpleFunction
  public void Delete(String id) {
    if (isExist(id)) {
      View view = (View) this.components.get(id);
      ((ViewGroup) view.getParent()).removeView(view);
      if (this.components.get(id) instanceof FallTextView)
        this.fallComponent.remove(id);
      else if (this.components.get(id) instanceof FadeTextView)
        this.fadeComponent.remove(id);
      else if (this.components.get(id) instanceof ScaleTextView)
        this.scaleComponent.remove(id);
      else if (this.components.get(id) instanceof LineTextView)
        this.lineComponent.remove(id);
      else if (this.components.get(id) instanceof RainbowTextView)
        this.rainbowComponent.remove(id);
      else if (this.components.get(id) instanceof TyperTextView)
        this.typerComponent.remove(id);
      else if (this.components.get(id) instanceof EvaporateTextView)
          this.evaporateComponent.remove(id);
      else if (this.components.get(id) instanceof ParticleTextView)
        this.particalComponent.remove(id);
      this.idList.remove(this.components.get(id));
      this.components.remove(id);
    }
  }
  @SimpleFunction
  public int GetTyperSpeed(String id){
    return this.typerComponent.get(id).getTyperSpeed();
  }
  @SimpleFunction
  public void SetSpeed(String id, int speed) {
    if (isExist(id)) {
      if (this.components.get(id) instanceof TyperTextView)
        this.typerComponent.get(id).setTyperSpeed(speed);
      else if (this.components.get(id) instanceof RainbowTextView)
        this.rainbowComponent.get(id).setColorSpeed(speed);
    }
  }
  @SimpleFunction
  public void SetAnimationDuration(String id, int duration) {
    if (isExist(id)) {
      if (this.components.get(id) instanceof FadeTextView)
        this.fadeComponent.get(id).setAnimationDuration(duration);
      else if (this.components.get(id) instanceof LineTextView)
        this.lineComponent.get(id).setAnimationDuration(duration);
    }
  }
  @SimpleFunction
  public String GetText(String id){
    if (isExist(id)) {
      return TextViewUtil.getText((TextView) this.components.get(id));
    }else
      return null;
  }
  @SimpleFunction
  public void SetText(String id, String text) {
    if (isExist(id)) {
      if (this.components.get(id) instanceof FallTextView)
        this.fallComponent.get(id).animateText(text);
      else if (this.components.get(id) instanceof FadeTextView)
        this.fadeComponent.get(id).animateText(text);
      else if (this.components.get(id) instanceof ScaleTextView)
        this.scaleComponent.get(id).animateText(text);
      else if (this.components.get(id) instanceof LineTextView)
        this.lineComponent.get(id).animateText(text);
      else if (this.components.get(id) instanceof RainbowTextView)
        this.rainbowComponent.get(id).animateText(text);
      else if (this.components.get(id) instanceof TyperTextView)
        this.typerComponent.get(id).animateText(text);
      else if (this.components.get(id) instanceof EvaporateTextView)
          this.evaporateComponent.get(id).animateText(text);
    }
  }
  @SimpleFunction
  public void SetPadding(String id,YailList paddings){
    if (paddings.toArray().length == 4) {
      int tMargin = Integer.parseInt(String.valueOf(paddings.toArray()[1]));
      int lMargin = Integer.parseInt(String.valueOf(paddings.toArray()[0]));
      int bMargin = Integer.parseInt(String.valueOf(paddings.toArray()[2]));
      int rMargin = Integer.parseInt(String.valueOf(paddings.toArray()[3]));
      if (isExist(id)) {
        if (this.components.get(id) instanceof FallTextView) {
          this.fallComponent.get(id).setPadding(lMargin,tMargin,rMargin,bMargin);
        } else if (this.components.get(id) instanceof FadeTextView) {
          this.fadeComponent.get(id).setPadding(lMargin,tMargin,rMargin,bMargin);
        }else if (this.components.get(id) instanceof ScaleTextView) {
          this.scaleComponent.get(id).setPadding(lMargin,tMargin,rMargin,bMargin);
        }else if (this.components.get(id) instanceof LineTextView) {
          this.lineComponent.get(id).setPadding(lMargin,tMargin,rMargin,bMargin);
        }else if(this.components.get(id)instanceof RainbowTextView) {
          this.rainbowComponent.get(id).setPadding(lMargin,tMargin,rMargin,bMargin);
        }else if (this.components.get(id) instanceof TyperTextView) {
          this.typerComponent.get(id).setPadding(lMargin,tMargin,rMargin,bMargin);
        }else if (this.components.get(id) instanceof EvaporateTextView)
            this.evaporateComponent.get(id).setPadding(lMargin,tMargin,rMargin,bMargin);
      }
    }else
      throw new YailRuntimeError("Padding list's length must be length 4","Custom TextView");
  }
  @SimpleFunction
  public void SetMargin(String id, YailList margins) {
    if (margins.toArray().length == 4) {
      int tMargin = Integer.parseInt(String.valueOf(margins.toArray()[1]));
      int lMargin = Integer.parseInt(String.valueOf(margins.toArray()[0]));
      int bMargin = Integer.parseInt(String.valueOf(margins.toArray()[2]));
      int rMargin = Integer.parseInt(String.valueOf(margins.toArray()[3]));
      if (isExist(id)) {
        if (this.components.get(id) instanceof FallTextView) {
          LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) this.fallComponent.get(id).getLayoutParams();
          params.setMargins(lMargin, tMargin, rMargin, bMargin);
          this.fallComponent.get(id).setLayoutParams(params);
        } else if (this.components.get(id) instanceof FadeTextView) {
          LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) this.fadeComponent.get(id).getLayoutParams();
          params.setMargins(lMargin, tMargin, rMargin, bMargin);
          this.fadeComponent.get(id).setLayoutParams(params);
        }else if (this.components.get(id) instanceof ScaleTextView) {
          LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) this.scaleComponent.get(id).getLayoutParams();
          params.setMargins(lMargin, tMargin, rMargin, bMargin);
          this.scaleComponent.get(id).setLayoutParams(params);
        }else if (this.components.get(id) instanceof LineTextView) {
          LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) this.lineComponent.get(id).getLayoutParams();
          params.setMargins(lMargin, tMargin, rMargin, bMargin);
          this.lineComponent.get(id).setLayoutParams(params);
        }else if(this.components.get(id)instanceof RainbowTextView) {
          LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) this.rainbowComponent.get(id).getLayoutParams();
          params.setMargins(lMargin, tMargin, rMargin, bMargin);
          this.rainbowComponent.get(id).setLayoutParams(params);
        }else if (this.components.get(id) instanceof TyperTextView) {
          LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) this.typerComponent.get(id).getLayoutParams();
          params.setMargins(lMargin, tMargin, rMargin, bMargin);
          this.typerComponent.get(id).setLayoutParams(params);
        }else if (this.components.get(id) instanceof EvaporateTextView) {
          LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) this.evaporateComponent.get(id).getLayoutParams();
          params.setMargins(lMargin, tMargin, rMargin, bMargin);
          this.evaporateComponent.get(id).setLayoutParams(params);
        }
      }
    }else
      throw new YailRuntimeError("Margins list's length must be length 4","Custom TextView");
  }
  @SimpleFunction
  public void SetTextColor(String id,int color){
    if (isExist(id)){
      TextViewUtil.setTextColor(((TextView) this.components.get(id)),color);
    }
  }
  @SimpleFunction
  public int GetTextSize(String id){
    if (isExist(id)){
      return (int) TextViewUtil.getFontSize((TextView) this.components.get(id),this.context);
    }else
      return 0;
  }
  @SimpleFunction
  public void SetTextSize(String id,int size){
    if (isExist(id)){
        TextViewUtil.setFontSize(((TextView) this.components.get(id)),size);
    }
  }
  @SimpleFunction
  public void SetVisible(String id,boolean visible){
    if (isExist(id)){
      if (visible)
        ((View) this.components.get(id)).setVisibility(View.VISIBLE);
      else
        ((View) this.components.get(id)).setVisibility(View.INVISIBLE);
    }
  }
  @SimpleFunction
  public String GetType(Object textView){
    if (this.idList.containsKey(textView)){
      if (textView instanceof RainbowTextView)
        return "Rainbow TextView";
      else if (textView instanceof FadeTextView)
        return "Fade TextView";
      else if (textView instanceof FallTextView)
        return "Fall TextView";
      else if (textView instanceof ScaleTextView)
        return "Scale TextView";
      else if (textView instanceof TyperTextView)
        return "Typer TextView";
      else if (textView instanceof LineTextView)
        return "Line TextView";
      else if (textView instanceof EvaporateTextView)
          return "Evaporate TextView";
      else
        return null;
    }
    else
      return null;
  }
  @SimpleFunction
  public void SetLine(String id,int lineColor,int lineWidth){
    if (isExist(id)){
      if (this.components.get(id) instanceof LineTextView){
        this.lineComponent.get(id).setLineColor(lineColor);
        this.lineComponent.get(id).setLineWidth(lineWidth);
      }
    }
  }
  @SimpleFunction
  public void SetBackgroundColor(String id,int color){
      if (isExist(id))
          TextViewUtil.setBackgroundColor(((TextView) this.components.get(id)),color);
  }
  @SimpleFunction
  public boolean GetClickable(String id){
    return ((View) this.components.get(id)).isClickable();
  }
  @SimpleFunction
  public void SetClickable(String id,boolean clickable){
    if (isExist(id)){
        ((View) this.components.get(id)).setClickable(clickable);
    }
  }
  @SimpleFunction
  public void SetCustomFontTypeface(String id,String name){
    if (isExist(id)){
      TextView textView=(TextView) this.components.get(id);
        String path = name;
        Typeface titleTypeface;
        titleTypeface = Typeface.createFromAsset(this.context.getAssets(), path);
        if (components.get(id) instanceof FadeTextView)
          this.fadeComponent.get(id).setTypeface(titleTypeface);
        else if (this.components.get(id) instanceof FallTextView)
          this.fallComponent.get(id).setTypeface(titleTypeface);
        else if (this.components.get(id) instanceof LineTextView)
          this.lineComponent.get(id).setTypeface(titleTypeface);
        else if (this.components.get(id) instanceof TyperTextView)
          this.typerComponent.get(id).setTypeface(titleTypeface);
        else if (this.components.get(id) instanceof RainbowTextView)
          this.rainbowComponent.get(id).setTypeface(titleTypeface);
        else if (this.components.get(id) instanceof ScaleTextView)
          this.scaleComponent.get(id).setTypeface(titleTypeface);
      }
  }
  @SimpleFunction
  public void CreateRainbowTextView(final String id, final AndroidViewComponent in, final String text, YailList colors){
    if (!this.components.containsKey(id)){
      RainbowTextView textView=new RainbowTextView(this.context);
      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-2,-2, 17);
      textView.setLayoutParams(params);
      textView.animateText(text);
      if (colors.toArray().length>1) {
        String[] colorString=colors.toStringArray();
        int[] colorsList = new int[colorString.length];
        for (int i = 0; i < colorString.length; i++) {
          colorsList[i] = Integer.parseInt(colorString[i]);
        }
        textView.setColors(colorsList);
      }
      this.components.put(id,textView);
      this.rainbowComponent.put(id,textView);
      this.idList.put(textView,id);
      this.AddTextView(in,textView);
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
    }
  }
  @SimpleFunction
  public void CreateFadeTextView(final String id, final AndroidViewComponent in, final String text,int letterSpacing){
    if (!this.components.containsKey(id)){
      FadeTextView textView=new FadeTextView(this.context);
      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-2,-2, 5);
      textView.setLayoutParams(params);
      textView.setLetterSpacing(letterSpacing);
      textView.animateText(text);
      textView.animateText(text);
      this.components.put(id,textView);
      this.fadeComponent.put(id,textView);
      this.AddTextView(in,textView);
      this.idList.put(textView,id);
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
      textView.setAnimationListener(new AnimationListener() {
        @Override
        public void onAnimationEnd(HTextView hTextView) {
          AnimationEnd(id);
        }

        @Override
        public void onAnimationStart(HTextView var2) {
          AnimationStart(id);
        }
      });
    }
  }
  @SimpleFunction
  public void CreateFallTextView(final String id, final AndroidViewComponent in, final String text){
    if (!this.components.containsKey(id)){
      FallTextView textView=new FallTextView(this.context);
      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-2,-2, 5);
      textView.setLayoutParams(params);
      textView.animateText(text);
      this.components.put(id,textView);
      this.fallComponent.put(id,textView);
      this.idList.put(textView,id);
      this.AddTextView(in,textView);
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
      textView.setAnimationListener(new AnimationListener() {
        @Override
        public void onAnimationEnd(HTextView hTextView) {
          AnimationEnd(id);
        }

        @Override
        public void onAnimationStart(HTextView var2) {
          AnimationStart(id);
        }
      });
    }
  }
  @SimpleFunction
  public void CreateTyperTextView(final String id, final AndroidViewComponent in, final String text,int charIncrease){
    if (!this.components.containsKey(id)){
      TyperTextView textView=new TyperTextView(this.context);
      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-2,-2, 5);
      textView.setLayoutParams(params);
      textView.setCharIncrease(charIncrease);
      textView.animateText(text);
      textView.setTextColor(COLOR_BLACK);
      this.components.put(id,textView);
      this.typerComponent.put(id,textView);
      this.AddTextView(in,textView);
      this.idList.put(textView,id);
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
      textView.setAnimationListener(new AnimationListener() {
        @Override
        public void onAnimationEnd(HTextView var1) {
          TypingFinished(id);
        }

        @Override
        public void onAnimationStart(HTextView var2) {
          AnimationStart(id);
        }
      });
    }
  }
  @SimpleFunction
  public void CreateEvaporateTextView(final String id, final AndroidViewComponent in, final String text){
    if (!this.components.containsKey(id)){
      EvaporateTextView textView=new EvaporateTextView(this.context);
      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-2,-2, 5);
      textView.setLayoutParams(params);
      textView.animateText(text);
      this.components.put(id,textView);
      this.evaporateComponent.put(id,textView);
      this.AddTextView(in,textView);
      this.idList.put(textView,id);
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
      textView.setAnimationListener(new AnimationListener() {
        @Override
        public void onAnimationEnd(HTextView var1) {
          AnimationEnd(id);
        }

        @Override
        public void onAnimationStart(HTextView var2) {
          AnimationStart(id);
        }
      });
    }
  }
  @SimpleFunction
  public void CreateScaleTextView(final String id, final AndroidViewComponent in, final String text){
    if (!this.components.containsKey(id)){
      ScaleTextView textView=new ScaleTextView(this.context);
      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-2,-2, 5);
      textView.setLayoutParams(params);
      textView.setText(text);
      textView.animateText(text);
      this.SetText(id,text);
      this.components.put(id,textView);
      this.scaleComponent.put(id,textView);
      this.AddTextView(in,textView);
      this.idList.put(textView,id);
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
      textView.setAnimationListener(new AnimationListener() {
        @Override
        public void onAnimationEnd(HTextView var1) {
          AnimationEnd(id);
        }

        @Override
        public void onAnimationStart(HTextView var2) {
          AnimationStart(id);
        }
      });
    }
  }
  @SimpleFunction
  public void CreateLineTextView(final String id, final AndroidViewComponent in, final String text,int lineColor,int lineWidth){
    if (!this.components.containsKey(id)){
      LineTextView textView=new LineTextView(this.context);
      LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(-2,-2, 5);
      textView.setLayoutParams(params);
      textView.animateText(text);
      textView.setLineWidth(lineWidth);
      textView.setLineColor(lineColor);
      this.AddTextView(in,textView);
      this.components.put(id,textView);
      this.lineComponent.put(id,textView);
      this.idList.put(textView,id);
      this.SetPadding(id,YailList.makeList(new Object[]{15,8,15,8}));
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
      textView.setAnimationListener(new AnimationListener() {
        @Override
        public void onAnimationEnd(HTextView hTextView) {
          AnimationEnd(id);
        }
        @Override
        public void onAnimationStart(HTextView var2) {
          AnimationStart(id);
        }
      });
    }
  }
  @SimpleFunction
  public void SetTypeface(String id,int typeface,boolean fontBold,boolean fontItalic){
    if (isExist(id)){

      TextViewUtil.setFontTypeface((TextView) this.components.get(id),typeface,fontBold,fontItalic);
    }
  }
  @SimpleProperty
  public String Random(){
    return "Random";
  }
  @SimpleProperty
  public String BidiVertical(){
    return "Bidi Vertical";
  }
  @SimpleProperty
  public String BidiHorizontal(){
    return "Bidi Horizontal";
  }
  @SimpleProperty
  public String Corner(){
    return "Corner";
  }
  @SimpleProperty
  public String Vertical(){
    return "Vertical";
  }
  @SimpleProperty
  public String Horizontal(){
    return "Horizontal";
  }
  @SimpleFunction
  public void CreateParticleTextView(String id,AndroidViewComponent in,String text,String strategy,int fontSize,float speed,YailList particleColors){
    if (!this.particalComponent.containsKey(id)){
      ParticleTextView textView=new ParticleTextView(this.context);
      ((LinearLayout)((ViewGroup) in.getView()).getChildAt(0)).addView(textView);
      this.particalComponent.put(id,textView);
      this.components.put(id,textView);
      Object[] array=particleColors.toArray();
      String [] list=new String[array.length];
      for (int i=0;i<list.length;i++){
        list[i] = "#"+getColor(array[i]);
      }
      ParticleTextViewConfig config=new ParticleTextViewConfig.Builder()
              .setRowStep(8)
              .setColumnStep(8)
              .setTargetText(text)
              .setReleasing(speed/100)
              .setParticleRadius(4)
              .setMiniDistance(0.1)
              .setTextSize(dp2px(fontSize))
              .setMovingStrategy(getMovingStrategy(strategy))
              .setParticleColorArray(list)
              .instance();
      textView.setConfig(config);
      this.Start(id);
      textView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
          LongClick(id);
          return false;
        }
      });
      textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Click(id);
        }
      });
    }
  }
  private void Start(String id){
    if (this.isExist(id))
      this.particalComponent.get(id).startAnimation();
  }
  private String getColor(Object color){
    int col=Integer.parseInt(String.valueOf(color));
    return Integer.toHexString(col);
  }
  private int dp2px(int size){
    return (int) (((float) size) * context.getResources().getDisplayMetrics().density);
  }
  private void AddTextView(AndroidViewComponent in, View textView){
    this.SetTextSize(this.idList.get(textView),16);
    if (!(textView instanceof RainbowTextView))
      TextViewUtil.setTextColor((TextView) textView,Color.BLACK);
    TextViewUtil.setAlignment((TextView) textView,ALIGNMENT_CENTER,true);
    TextViewUtil.setFontTypeface((TextView) textView,TYPEFACE_DEFAULT,true,false);
    ((LinearLayout)((ViewGroup) in.getView()).getChildAt(0)).addView(textView);
  }
  private boolean isExist(String id){
    return this.components.containsKey(id);
  }
  private MovingStrategy getMovingStrategy(String strategy){
    MovingStrategy movingStrategy=null;
    if (strategy.equals(Random()))
      movingStrategy=new RandomMovingStrategy();
    else if (strategy.equals(Horizontal()))
      movingStrategy=new HorizontalStrategy();
    else if (strategy.equals(Vertical()))
      movingStrategy=new VerticalStrategy();
    else if (strategy.equals(Corner()))
      movingStrategy=new CornerStrategy();
    else if (strategy.equals(BidiHorizontal()))
      movingStrategy=new BidiHorizontalStrategy();
    else if (strategy.equals(BidiVertical()))
      movingStrategy=new BidiVerticalStrategy();
    else
      throw new YailRuntimeError("Invalid Strategy name Recheck your strategy name","Invalid Strategy");
    return movingStrategy;
  }
}