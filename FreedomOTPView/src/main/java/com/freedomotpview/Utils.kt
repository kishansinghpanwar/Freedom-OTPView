package com.freedomotpview

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout

object Utils {

    fun getValueFromKeyCode(code: Int): Int {
        return code - 7
    }

    @SuppressLint("ClickableViewAccessibility")
    fun addCursorAlwaysInLast(editText: EditText) {
        editText.setOnTouchListener { _: View?, event: MotionEvent? ->
            editText.onTouchEvent(event)
            editText.setSelection(editText.text.length)
            true
        }
    }

    fun getEditTextFromIndex(linearLayout: LinearLayout?, index: Int): EditText {
        if (linearLayout != null) {
            val editText = linearLayout.findViewById<View>(index)
            if (editText is EditText) {
                return editText
            }
        }
        throw RuntimeException("EditText not found on index : $index")
    }
}