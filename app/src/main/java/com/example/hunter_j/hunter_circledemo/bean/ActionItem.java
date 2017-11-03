package com.example.hunter_j.hunter_circledemo.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by hunter_J on 2017/11/2.
 */

public class ActionItem {
    //定义图片对象
    public Drawable mDrawable;
    //定义文本对象
    public CharSequence mTitle;

    public ActionItem(Drawable mDrawable, CharSequence mTitle) {
        this.mDrawable = mDrawable;
        this.mTitle = mTitle;
    }

    public ActionItem(CharSequence mTitle) {
        this.mDrawable = null;
        this.mTitle = mTitle;
    }

    public ActionItem(Context context, int titleId, int drawableId) {
        this.mTitle = context.getResources().getText(titleId);
        this.mDrawable = context.getResources().getDrawable(drawableId);
    }

    public ActionItem(Context context, CharSequence title, int drawableId) {
        this.mTitle = title;
        this.mDrawable = context.getResources().getDrawable(drawableId);
    }

    public void setItemTv(CharSequence tv) {
        mTitle = tv;
    }
}
