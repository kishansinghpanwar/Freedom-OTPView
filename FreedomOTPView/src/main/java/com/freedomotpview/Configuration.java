package com.freedomotpview;

import android.graphics.drawable.Drawable;

public class Configuration {
    int builderOtpLength;
    int builderTextSize;
    int builderTextColor;
    int builderHintColor;
    String builderHintText;
    Drawable builderBackground;
    int builderMargin;
    int builderHeight;
    int builderWidth;

    Configuration(int builderOtpLength, int builderTextSize, int builderTextColor,
                  int builderHintColor, String builderHintText, Drawable builderBackground,
                  int builderMargin, int builderHeight, int builderWidth) {
        this.builderOtpLength = builderOtpLength;
        this.builderTextSize = builderTextSize;
        this.builderTextColor = builderTextColor;
        this.builderHintColor = builderHintColor;
        this.builderHintText = builderHintText;
        this.builderBackground = builderBackground;
        this.builderMargin = builderMargin;
        this.builderHeight = builderHeight;
        this.builderWidth = builderWidth;
    }

    public int getBuilderOtpLength() {
        return builderOtpLength;
    }

    public int getBuilderTextColor() {
        return builderTextColor;
    }

    public int getBuilderHintColor() {
        return builderHintColor;
    }

    public Drawable getBuilderBackground() {
        return builderBackground;
    }

    public int getBuilderTextSize() {
        return builderTextSize;
    }

    public String getBuilderHintText() {
        return builderHintText;
    }

    public int getBuilderMargin() {
        return builderMargin;
    }

    public int getBuilderHeight() {
        return builderHeight;
    }

    public int getBuilderWidth() {
        return builderWidth;
    }
}
