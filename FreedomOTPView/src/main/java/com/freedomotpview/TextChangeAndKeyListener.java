package com.freedomotpview;

import static com.freedomotpview.Utils.getEditTextFromIndex;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class TextChangeAndKeyListener implements TextWatcher, View.OnKeyListener {
    private final String TAG = TextChangeAndKeyListener.class.getSimpleName();
    private final int index;
    private final int otpLength;
    private final LinearLayout linOTPView;

    public TextChangeAndKeyListener(LinearLayout linOTPView, int index, int otpLength) {
        this.linOTPView = linOTPView;
        this.index = index;
        this.otpLength = otpLength;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable != null && editable.length() > 0) {
            if (index < (otpLength - 1)) {
                View editText = getEditTextFromIndex(linOTPView, index + 1);
                editText.requestFocus();
            }
        } else {
            if (index > 0) {
                View editText = linOTPView.findViewById(index - 1);
                editText.requestFocus();
            }
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            //check if the right key was pressed
            Log.i(TAG, "onKeyPress: " + keyCode);
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (index > 0) {
                    EditText currentEditText = getEditTextFromIndex(linOTPView, index);
                    if (currentEditText.getText().length() == 0) {
                        View previousEditText = getEditTextFromIndex(linOTPView, index - 1);
                        previousEditText.requestFocus();
                    }
                }
            } else if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                if (index < otpLength) {
                    EditText currentEditText = getEditTextFromIndex(linOTPView, index);
                    if (currentEditText.getText().length() > 0) {
                        currentEditText.setText(String.valueOf(Utils.getValueFromKeyCode(keyCode)));
                        currentEditText.setSelection(currentEditText.getText().length());
                    }
                }

            }
        }
        return false;
    }


}