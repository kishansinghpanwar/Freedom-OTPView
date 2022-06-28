package com.app.freedomotpviewexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.freedomotpview.OTPView

class MainActivity : AppCompatActivity() {
    var otpView: OTPView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        otpView = findViewById(R.id.otpView)
        val configuration = OTPView.Builder()
            .setOTPLength(5)
            .setHeight(130)
            .setWidth(100)
            .setMargin(8)
            .setHintText("-")
            .setHintColor(ContextCompat.getColor(this, R.color.color_hint_color_from_code))
            .setTextSize(22)
            .setTextColor(ContextCompat.getColor(this, R.color.color_text_color_from_code))
            .setBackground(ContextCompat.getDrawable(this, R.drawable.bg_otp_rectangle_code))
            .build()
        otpView?.setConfiguration(configuration)
    }
}