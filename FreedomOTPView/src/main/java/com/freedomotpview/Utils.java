package com.freedomotpview;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Utils {
    public static int getValueFromKeyCode(int code) {
        return code - 7;
    }

    @SuppressLint("ClickableViewAccessibility")
    public static  void addCursorAlwaysInLast(EditText editText) {
        editText.setOnTouchListener((v, event) -> {
            editText.onTouchEvent(event);
            editText.setSelection(editText.getText().length());
            return true;
        });
    }

    public static EditText getEditTextFromIndex(LinearLayout linearLayout, int index) {
        if (linearLayout != null) {
            View editText = linearLayout.findViewById(index);
            if (editText instanceof EditText) {
                return (EditText) editText;
            }
        }
        throw new RuntimeException("EditText not found on index : " + index);
    }
}
