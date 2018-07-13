package com.abdulaala.meterzy.ui.models;

import android.graphics.drawable.Drawable;

public class MenuItemModel {
    public MenuItemModel(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    private String name;
    private Drawable icon;
}
