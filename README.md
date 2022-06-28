# Freedom-OTPView [![](https://jitpack.io/v/kishansinghpanwar/AutoPlayer.svg)](https://jitpack.io/#kishansinghpanwar/AutoPlayer)

A readymade OTP View, which gives you the freedom to customize every settings which is visible to the user. 


**Sample App (Light & Dark Mode):**

<img src="https://github.com/kishansinghpanwar/FreedomOTPView/blob/master/screenshot/Screenshot_20220626_233146.png" width="180" >  <img src="https://github.com/kishansinghpanwar/FreedomOTPView/blob/master/screenshot/Screenshot_20220626_233220.png" width="180">

 **Features:**
 - This library supports the OTP View with unlimited digits, you can achieve this by just adding a single line property and the library will draw OTP boxes for you.
 - Ready to use: This library has tested on 15+ live apps and 1000+ devices.
- Fully customizable settings: You can change everything, the library has option to update every property which is visible to the user (Box background, Text size, Text color, Hint text, Hint color, Height - Width, Margin and many more..)
- Have inbuilt functions to validate OTP size.
- Scroll support if OTP digit length are more than 6 Digits.
- Support dark and light both modes.
- You can customize the properties directly from XML or Code.


 **To add this Library in your project :**
 - Step 1. Add the JitPack repository to your build file
    
    
	```
	allprojects {
	    repositories {
	       ...
	       maven { url 'https://jitpack.io' }
	    }
	}
	 ```  
   
 - Step 2. Add the dependency
	```
	dependencies {
	    implementation 'com.github.kishansinghpanwar:AutoPlayer:v1.0.0'
	}
	```
  
**Basic Usage :**


- Add OTPView in your **XML** :
  ```
  <com.freedomotpview.OTPView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:otpBackground="@drawable/bg_otp"
        app:otpLength="5"
        app:otpHintText="#"
        app:otpHintTextColor="#DDC9FF"
        app:otpTextColor="#64B5F6"
        app:otpTextSize="10sp"
        app:otpHeight="40dp"
        app:otpWidth="40dp" />
  ```
   *See usage:* [activity_main.xml](https://github.com/kishansinghpanwar/FreedomOTPView/blob/master/app/src/main/res/layout/activity_main.xml)
   
    ------
    
- Set Properties from your **Code** :
  
  Note: You can directly set these settings from XML also, if you are setting everything in XML then don't need to set again this in your code. 
  ```
  val otpView:OTPView? = findViewById(R.id.otpView)
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
  ```
  *See usage:* [MainActivity.kt](https://github.com/kishansinghpanwar/FreedomOTPView/blob/master/app/src/main/java/com/app/freedomotpviewexample/MainActivity.kt)
   
   
   
**Properties :**
