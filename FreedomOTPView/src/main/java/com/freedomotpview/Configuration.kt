package com.freedomotpview

import android.graphics.drawable.Drawable

class Configuration internal constructor(
    var builderOtpLength: Int,
    var builderTextSize: Int,
    var builderTextColor: Int,
    var builderHintColor: Int,
    var builderHintText: String?,
    var builderBackground: Drawable?,
    var builderMargin: Int,
    var builderHeight: Int,
    var builderWidth: Int
)