package com.freedomotpview

import com.freedomotpview.Utils.getEditTextFromIndex
import com.freedomotpview.Utils.getValueFromKeyCode
import android.widget.LinearLayout
import android.text.TextWatcher
import android.view.View
import android.text.Editable
import android.view.KeyEvent
import android.util.Log

class TextChangeAndKeyListener(
    private val linOTPView: LinearLayout,
    private val index: Int,
    private val otpLength: Int
) : TextWatcher, View.OnKeyListener {
    private fun getTAG() = TextChangeAndKeyListener::class.java.simpleName
    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
    override fun afterTextChanged(editable: Editable) {
        if (editable.isNotEmpty()) {
            if (index < otpLength - 1) {
                val editText: View = getEditTextFromIndex(linOTPView, index + 1)
                editText.requestFocus()
            }
        } else {
            if (index > 0) {
                val editText = linOTPView.findViewById<View>(index - 1)
                editText.requestFocus()
            }
        }
    }

    override fun onKey(view: View, keyCode: Int, keyEvent: KeyEvent): Boolean {
        if (keyEvent.action == KeyEvent.ACTION_DOWN) {
            Log.i(getTAG(), "onKeyPress: $keyCode")
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                if (index > 0) {
                    val currentEditText = getEditTextFromIndex(linOTPView, index)
                    if (currentEditText.text.isEmpty()) {
                        val previousEditText: View = getEditTextFromIndex(linOTPView, index - 1)
                        previousEditText.requestFocus()
                    }
                }
            } else if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                if (index < otpLength) {
                    val currentEditText = getEditTextFromIndex(linOTPView, index)
                    if (currentEditText.text.isNotEmpty()) {
                        currentEditText.setText(getValueFromKeyCode(keyCode).toString())
                        currentEditText.setSelection(currentEditText.text.length)
                    }
                }
            }
        }
        return false
    }
}