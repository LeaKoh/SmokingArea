package com.example.smokingarealist;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable;
    private String titleStr;
    private String descStr;
    private Drawable find;

    public void setIcon(Drawable icon){iconDrawable = icon;}
    public void setTitle(String title){
        titleStr = title;
    }
    public void setDesc(String desc){
        descStr = desc;
    }
    public void setFind(Drawable Icon){ find = Icon;}

    public Drawable getIcon(){
        return this. iconDrawable;
    }
    public String getTitle(){
        return this.titleStr;
    }
    public String getDesc(){
        return this.descStr;
    }
    public Drawable getFind(){ return this.find;}
}
