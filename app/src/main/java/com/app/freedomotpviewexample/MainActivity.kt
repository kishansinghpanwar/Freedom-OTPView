package com.app.freedomotpviewexample

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.freedomotpview.OTPView

class MainActivity : AppCompatActivity() {
    var otpView: OTPView? = null
    var btnGetOtp: Button? = null
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

        btnGetOtp = findViewById(R.id.btnGetOtp)
        btnGetOtp?.setOnClickListener {
            if (otpView?.isOTPValid == true) {
                showToast(getString(R.string.your_otp_is, otpView?.otp, otpView?.otpLength))
            } else {
                showToast(getString(R.string.invalid_otp))
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }
}