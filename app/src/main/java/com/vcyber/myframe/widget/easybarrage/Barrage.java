package com.vcyber.myframe.widget.easybarrage;

import android.graphics.Color;

import com.vcyber.myframe.R;

public class Barrage {

    private CharSequence content;
    private int color;
    private boolean showBorder;
    private int backGroundColor;

    public Barrage(CharSequence content) {
        this(content, R.color.black, false,  Color.parseColor("#00000000"));
    }

    public Barrage(CharSequence content, int color) {
        this(content, color, false, Color.parseColor("#00000000"));
    }

    public Barrage(CharSequence content, boolean showBorder) {
        this(content, R.color.black, showBorder,  Color.parseColor("#00000000"));
    }

    public Barrage(CharSequence content, int color, int backGroundColor) {
        this(content, color, false, backGroundColor);
    }

    private Barrage(CharSequence content, int color, boolean showBorder, int backGroundColor) {
        this.content = content;
        this.color = color;
        this.showBorder = showBorder;
        this.backGroundColor = backGroundColor;
    }

    public boolean isShowBorder() {
        return showBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
    }

    public CharSequence getContent() {
        return content;
    }

    public void setContent(CharSequence content) {
        this.content = content;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.backGroundColor = backGroundColor;
    }
}
