package com.freedomotpview;

import static com.freedomotpview.Utils.addCursorAlwaysInLast;
import static com.freedomotpview.Utils.getEditTextFromIndex;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.app.freedomotpview.R;

public class OTPView extends LinearLayout {
    private final String TAG = OTPView.class.getSimpleName();
    private int defaultOTPLength = 6;
    private LinearLayout linOTPView = null;
    private LayoutInflater inflater = null;
    private String defaultHintText = "#";
    private float defaultMargin = 10;
    private float defaultTextSize = 22;
    private float defaultHeight = LayoutParams.WRAP_CONTENT;
    private float defaultWidth = LayoutParams.WRAP_CONTENT;
    private int defaultTextColor = ContextCompat.getColor(getContext(), R.color.otp_default_text_color);
    private int defaultHintColor = ContextCompat.getColor(getContext(), R.color.otp_default_hint_color);
    private Drawable defaultBackground = ContextCompat.getDrawable(getContext(), R.drawable.bg_otp_component);

    public OTPView(Context context) {
        this(context, null);
    }

    public OTPView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getAttributes(context, attrs);
    }

    public OTPView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttributes(context, attrs);
    }

    private void getAttributes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OTPView);
        defaultOTPLength = typedArray.getColor(R.styleable.OTPView_otpLength, defaultOTPLength);
        defaultTextColor = typedArray.getColor(R.styleable.OTPView_otpTextColor, defaultTextColor);
        defaultHintColor = typedArray.getColor(R.styleable.OTPView_otpHintTextColor, defaultHintColor);
        defaultMargin = typedArray.getDimension(R.styleable.OTPView_otpMargin, defaultMargin);
        defaultHeight = typedArray.getDimension(R.styleable.OTPView_otpHeight, defaultHeight);
        defaultWidth = typedArray.getDimension(R.styleable.OTPView_otpWidth, defaultWidth);
        defaultTextSize = typedArray.getDimension(R.styleable.OTPView_otpTextSize, defaultTextSize);

        String hintText = typedArray.getString(R.styleable.OTPView_otpHintText);
        if (hintText != null) {
            defaultHintText = hintText;
        }

        Drawable background = typedArray.getDrawable(R.styleable.OTPView_otpBackground);
        if (background != null) {
            defaultBackground = background;
        }

        typedArray.recycle();
        init();
    }

    private void init() {
        if (defaultOTPLength < 7) {
            inflate(getContext(), R.layout.layout_otp_view, this);
        } else {
            inflate(getContext(), R.layout.layout_long_otp_view, this);
        }
        inflater = LayoutInflater.from(getContext());
        linOTPView = findViewById(R.id.linOTPView);
        addViews();
    }


    @SuppressLint("InflateParams")
    private void addViews() {
        for (int i = 0; i < defaultOTPLength; i++) {
            View editView = inflater.inflate(R.layout.item_otp_view, null);
            if (editView instanceof EditText) {
                LayoutParams params = new LayoutParams(
                        (int) defaultWidth,
                        (int) defaultHeight
                );
                params.setMargins((int) defaultMargin, 0, (int) defaultMargin, 0);
                editView.setLayoutParams(params);
                editView.setId(i);
                TextChangeAndKeyListener listener = new TextChangeAndKeyListener(linOTPView, i, defaultOTPLength);
                ((EditText) editView).addTextChangedListener(listener);
                editView.setOnKeyListener(listener);
                if (i != (defaultOTPLength - 1)) {
                    ((EditText) editView).setImeOptions(EditorInfo.IME_ACTION_NEXT);
                } else {
                    ((EditText) editView).setImeOptions(EditorInfo.IME_ACTION_DONE);
                }

                setupAttributes((EditText) editView);
                addCursorAlwaysInLast((EditText) editView);
            }
            linOTPView.addView(editView);
        }

    }

    private void setupAttributes(EditText editView) {
        editView.setTextSize(defaultTextSize);
        editView.setTextColor(defaultTextColor);
        editView.setHintTextColor(defaultHintColor);
        editView.setBackground(defaultBackground);
        editView.setHint(defaultHintText);
    }


    public int getOTPLength() {
        return defaultOTPLength;
    }

    public String getOTP() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < defaultOTPLength; i++) {
            EditText editText = getEditTextFromIndex(linOTPView, i);
            otp.append(editText.getText().toString());
        }
        return otp.toString();
    }

    public boolean isOTPValid() {
        return getOTPLength() == getOTP().length();
    }


    public void setConfiguration(Configuration configuration) {
        if (configuration != null) {
            boolean haveToUpdateConfiguration = false;
            if (configuration.getBuilderOtpLength() > -1) {
                haveToUpdateConfiguration = true;
                defaultOTPLength = configuration.getBuilderOtpLength();
            }
            if (configuration.getBuilderTextColor() != -1) {
                haveToUpdateConfiguration = true;
                defaultTextColor = configuration.getBuilderTextColor();
            }
            if (configuration.getBuilderHintColor() != -1) {
                haveToUpdateConfiguration = true;
                defaultHintColor = configuration.getBuilderHintColor();
            }
            if (configuration.getBuilderBackground() != null) {
                haveToUpdateConfiguration = true;
                defaultBackground = configuration.getBuilderBackground();
            }
            if (configuration.getBuilderTextSize() > -1) {
                haveToUpdateConfiguration = true;
                defaultTextSize = configuration.getBuilderTextSize();
            }
            if (configuration.getBuilderHintText() != null) {
                haveToUpdateConfiguration = true;
                defaultHintText = configuration.getBuilderHintText();
            }
            if (configuration.getBuilderMargin() > -1) {
                haveToUpdateConfiguration = true;
                defaultMargin = configuration.getBuilderMargin();
            }
            if (configuration.getBuilderHeight() > -1) {
                haveToUpdateConfiguration = true;
                defaultHeight = configuration.getBuilderHeight();
            }
            if (configuration.getBuilderWidth() > -1) {
                haveToUpdateConfiguration = true;
                defaultWidth = configuration.getBuilderWidth();
            }

            if (haveToUpdateConfiguration) {
                if (linOTPView != null) {
                    linOTPView.removeAllViews();
                }
                addViews();
            }
        }
    }

    public static class Builder {
        int builderOtpLength = -1;
        int builderTextSize = -1;
        int builderTextColor = -1;
        int builderHintColor = -1;
        int builderMargin = -1;
        int builderHeight = -1;
        int builderWidth = -1;
        String builderHintText = null;
        Drawable builderBackground = null;

        public Builder() {
        }

        public Builder setOTPLength(int length) {
            builderOtpLength = length;
            return this;
        }

        public Builder setTextColor(@ColorInt int textColor) {
            builderTextColor = textColor;
            return this;
        }

        public Builder setTextSize(@Dimension int textSize) {
            builderTextSize = textSize;
            return this;
        }

        public Builder setHintColor(@ColorInt int hintColor) {
            builderHintColor = hintColor;
            return this;
        }

        public Builder setHintText(String hintText) {
            builderHintText = hintText;
            return this;
        }

        public Builder setBackground(Drawable background) {
            builderBackground = background;
            return this;
        }

        public Builder setMargin(@Dimension int margin) {
            builderMargin = margin;
            return this;
        }

        public Builder setHeight(@Dimension int height) {
            builderHeight = height;
            return this;
        }

        public Builder setWidth(@Dimension int width) {
            builderWidth = width;
            return this;
        }


        public Configuration build() {
            return new Configuration(builderOtpLength,
                    builderTextSize,
                    builderTextColor,
                    builderHintColor,
                    builderHintText,
                    builderBackground,
                    builderMargin,
                    builderHeight,
                    builderWidth);
        }

    }

}
