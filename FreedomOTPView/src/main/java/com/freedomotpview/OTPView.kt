package com.freedomotpview

import com.freedomotpview.Utils.addCursorAlwaysInLast
import com.freedomotpview.Utils.getEditTextFromIndex
import android.widget.LinearLayout
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.app.freedomotpview.R
import android.graphics.drawable.Drawable
import kotlin.jvm.JvmOverloads
import android.util.AttributeSet
import android.annotation.SuppressLint
import android.content.Context
import android.widget.EditText
import android.view.inputmethod.EditorInfo
import java.lang.StringBuilder
import androidx.annotation.ColorInt
import androidx.annotation.Dimension

class OTPView : LinearLayout {
    private var mOTPLength = 6
    private var linOTPView: LinearLayout? = null
    private var inflater: LayoutInflater? = null
    private var defaultHintText = "#"
    private var defaultMargin = 10f
    private var defaultTextSize = 22f
    private var defaultHeight = LayoutParams.WRAP_CONTENT.toFloat()
    private var defaultWidth = LayoutParams.WRAP_CONTENT.toFloat()
    private var defaultTextColor = ContextCompat.getColor(context, R.color.otp_default_text_color)
    private var defaultHintColor = ContextCompat.getColor(context, R.color.otp_default_hint_color)
    private var defaultBackground = ContextCompat.getDrawable(context, R.drawable.bg_otp_component)

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        getAttributes(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttributes(context, attrs)
    }

    private fun getAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.OTPView)
        mOTPLength = typedArray.getInteger(R.styleable.OTPView_otpLength, mOTPLength)
        defaultTextColor = typedArray.getColor(R.styleable.OTPView_otpTextColor, defaultTextColor)
        defaultHintColor =
            typedArray.getColor(R.styleable.OTPView_otpHintTextColor, defaultHintColor)
        defaultMargin = typedArray.getDimension(R.styleable.OTPView_otpMargin, defaultMargin)
        defaultHeight = typedArray.getDimension(R.styleable.OTPView_otpHeight, defaultHeight)
        defaultWidth = typedArray.getDimension(R.styleable.OTPView_otpWidth, defaultWidth)
        defaultTextSize = typedArray.getDimension(R.styleable.OTPView_otpTextSize, defaultTextSize)
        val hintText = typedArray.getString(R.styleable.OTPView_otpHintText)
        if (hintText != null) {
            defaultHintText = hintText
        }
        val background = typedArray.getDrawable(R.styleable.OTPView_otpBackground)
        if (background != null) {
            defaultBackground = background
        }
        typedArray.recycle()
        init()
    }

    private fun init() {
        if (mOTPLength < 7) {
            inflate(context, R.layout.layout_otp_view, this)
        } else {
            inflate(context, R.layout.layout_long_otp_view, this)
        }
        inflater = LayoutInflater.from(context)
        linOTPView = findViewById(R.id.linOTPView)
        addViews()
    }

    @SuppressLint("InflateParams")
    private fun addViews() {
        for (i in 0 until mOTPLength) {
            val editView = inflater!!.inflate(R.layout.item_otp_view, null)
            if (editView is EditText) {
                val params = LayoutParams(
                    defaultWidth.toInt(),
                    defaultHeight.toInt()
                )
                params.setMargins(defaultMargin.toInt(), 0, defaultMargin.toInt(), 0)
                editView.setLayoutParams(params)
                editView.setId(i)
                val listener = TextChangeAndKeyListener(linOTPView!!, i, mOTPLength)
                editView.addTextChangedListener(listener)
                editView.setOnKeyListener(listener)
                if (i != mOTPLength - 1) {
                    editView.imeOptions = EditorInfo.IME_ACTION_NEXT
                } else {
                    editView.imeOptions = EditorInfo.IME_ACTION_DONE
                }
                setupAttributes(editView)
                addCursorAlwaysInLast(editView)
            }
            linOTPView!!.addView(editView)
        }
    }

    private fun setupAttributes(editView: EditText) {
        editView.textSize = defaultTextSize
        editView.setTextColor(defaultTextColor)
        editView.setHintTextColor(defaultHintColor)
        editView.background = defaultBackground
        editView.hint = defaultHintText
    }

    val otp: String
        get() {
            val otp = StringBuilder()
            for (i in 0 until mOTPLength) {
                val editText = getEditTextFromIndex(linOTPView, i)
                otp.append(editText.text.toString())
            }
            return otp.toString()
        }

    val isOTPValid: Boolean
        get() = mOTPLength == otp.length


    val otpLength: Int
        get() = mOTPLength

    fun setConfiguration(configuration: Configuration?) {
        if (configuration != null) {
            var haveToUpdateConfiguration = false
            if (configuration.builderOtpLength > -1) {
                haveToUpdateConfiguration = true
                mOTPLength = configuration.builderOtpLength
            }
            if (configuration.builderTextColor != -1) {
                haveToUpdateConfiguration = true
                defaultTextColor = configuration.builderTextColor
            }
            if (configuration.builderHintColor != -1) {
                haveToUpdateConfiguration = true
                defaultHintColor = configuration.builderHintColor
            }
            if (configuration.builderBackground != null) {
                haveToUpdateConfiguration = true
                defaultBackground = configuration.builderBackground
            }
            if (configuration.builderTextSize > -1) {
                haveToUpdateConfiguration = true
                defaultTextSize = configuration.builderTextSize.toFloat()
            }
            if (configuration.builderHintText != null) {
                haveToUpdateConfiguration = true
                defaultHintText = configuration.builderHintText!!
            }
            if (configuration.builderMargin > -1) {
                haveToUpdateConfiguration = true
                defaultMargin = configuration.builderMargin.toFloat()
            }
            if (configuration.builderHeight > -1) {
                haveToUpdateConfiguration = true
                defaultHeight = configuration.builderHeight.toFloat()
            }
            if (configuration.builderWidth > -1) {
                haveToUpdateConfiguration = true
                defaultWidth = configuration.builderWidth.toFloat()
            }
            if (haveToUpdateConfiguration) {
                if (linOTPView != null) {
                    linOTPView!!.removeAllViews()
                }
                addViews()
            }
        }
    }

    class Builder {
        private var builderOtpLength = -1
        private var builderTextSize = -1
        private var builderTextColor = -1
        private var builderHintColor = -1
        private var builderMargin = -1
        private var builderHeight = -1
        private var builderWidth = -1
        private var builderHintText: String? = null
        private var builderBackground: Drawable? = null
        fun setOTPLength(length: Int): Builder {
            builderOtpLength = length
            return this
        }

        fun setTextColor(@ColorInt textColor: Int): Builder {
            builderTextColor = textColor
            return this
        }

        fun setTextSize(@Dimension textSize: Int): Builder {
            builderTextSize = textSize
            return this
        }

        fun setHintColor(@ColorInt hintColor: Int): Builder {
            builderHintColor = hintColor
            return this
        }

        fun setHintText(hintText: String?): Builder {
            builderHintText = hintText
            return this
        }

        fun setBackground(background: Drawable?): Builder {
            builderBackground = background
            return this
        }

        fun setMargin(@Dimension margin: Int): Builder {
            builderMargin = margin
            return this
        }

        fun setHeight(@Dimension height: Int): Builder {
            builderHeight = height
            return this
        }

        fun setWidth(@Dimension width: Int): Builder {
            builderWidth = width
            return this
        }

        fun build(): Configuration {
            return Configuration(
                builderOtpLength,
                builderTextSize,
                builderTextColor,
                builderHintColor,
                builderHintText!!,
                builderBackground!!,
                builderMargin,
                builderHeight,
                builderWidth
            )
        }
    }
}