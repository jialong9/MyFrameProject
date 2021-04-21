package com.vcyber.myframe.bean;

import android.text.Spanned;

/**
 * description ：
 * author : zjl
 * date : 2021/4/1
 */
public class DanMuBean {
    private Spanned content;
    private boolean showBorder;

    public Spanned getContent() {
        return content;
    }

    public void setContent(Spanned content) {
        this.content = content;
    }

    public boolean isShowBorder() {
        return showBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.showBorder = showBorder;
    }
}
