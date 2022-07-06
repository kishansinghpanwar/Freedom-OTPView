
# Freedom-OTPView [![](https://jitpack.io/v/kishansinghpanwar/FreedomOTPView.svg)](https://jitpack.io/#kishansinghpanwar/FreedomOTPView)

A ready-made OTP View, which gives you the freedom to customize every setting of the UI.

**Sample App (Light & Dark Mode):**

<img src="https://github.com/kishansinghpanwar/FreedomOTPView/blob/master/screenshot/Screenshot_light_mode.png" width="180" >  <img src="https://github.com/kishansinghpanwar/FreedomOTPView/blob/master/screenshot/Screenshot_night_mode.png" width="180">

 **Features:**
 - This library supports the OTP View with unlimited digits, you can achieve this by just adding a single line property and the library will draw OTP boxes for you.
- Ready to use: This library has been tested on 15+ live apps and 1000+ devices.
- Fully customizable settings: You can change everything, the library has the option to update every property which is visible to the user (Box background, Text size, Text color, Hint text, Hint color, Height - Width, Margin and many more..)
- Have inbuilt functions to validate OTP size.
- Scroll support if OTP digit length is more than 6 Digits.
- Support dark and light(both) modes.
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
   
 - Step 2. Add the dependency<br/><br/>
 	For new Kotlin version:
	
	```
	dependencies {
	      implementation 'com.github.kishansinghpanwar:FreedomOTPView:v2.0'
	}
	```
	
 	For old Java version:
	```
	dependencies {
	     implementation 'com.github.kishansinghpanwar:FreedomOTPView:v1.0'
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
   
   
   
    ------
    
   
 **Properties :**

    
   

 - **OTP Boxes Length :**
	  This library supports dynamic OTP length, the library will create the OTP boxes automatically according to you set the size of OTP view length.<br/>
	  you can set OTP length through these properties :<br/>
	from XML : `app:otpLength="5"` <br/>
	from Code: `builder.setOTPLength(5)`

 - **Boxes Height :**
	 You can set Height of the Box according to your UI design.<br/>
	 for set the Height you can use these properties :<br/>
	 from XML : `app:otpHeight="40dp"` <br/>
	from Code: `builder.setHeight(130)`
	
 - **Boxes Width :**
	 You can set Width of the Box according to your UI design.<br/>
	 for set the Width you can use these properties :<br/>
	 from XML : `app:otpWidth="40dp"` <br/>
	from Code: `builder.setWidth(100)`
	
 - **Margin between Boxes :**
	 You can set the Margin between 2 or Multiple boxes according to your UI design.<br/>
	 for set the Margin you can use these properties :<br/>
	 from XML : `app:otpMargin="8dp"` <br/>
	from Code: `builder.setMargin(8)`
	
 - **Hint Text:**
	 Using Hint Text property, you can set the hint text of OTP Boxes, the same hint will apply on all boxes automatically.<br/>
	 for set the Hint text you can use these properties :<br/>
	 from XML : `app:otpHintText="#"` <br/>
	from Code: `builder.setHintText(8)`<br/>
	for example you  can set :  `# ,  * , &` etc...
	
 - **Hint Text Color:**
	 Using Hint Text Color property, you can set the color of hint text in OTP Boxes, the same hint color will apply on all boxes automatically.<br/>
	 for set the Hint color you can use these properties :<br/>
	 from XML : `app:otpHintTextColor="#DDC9FF"`<br/>
	from Code: `builder.setHintColor(color)`
	
 - **Text Size:**
	 Using Text Size property, you can set the size of text in OTP Boxes, the same size will apply on all boxes automatically.<br/>
	 for set the Text size  you can use these properties :<br/>
	 from XML : `app:otpTextSize="10sp"`<br/>
	from Code: `builder.setTextSize(22)`
	
 - **Text Color:**
	 Using Text Color property, you can set the color of text in OTP Boxes, the same color will apply on all boxes automatically.<br/>
	 for set the Text color you can use these properties :<br/>
	 from XML : `app:otpTextColor="#64B5F6"`<br/>
	from Code: `builder.setTextColor(ContextCompat.getColor(this, R.color.color_text_color_from_code))`
	
 - **OTP Boxes Background:**
	 Using Background property, you can set the background of OTP Boxes which can be a color or drawable, you can create your own drawable and set it as background of boxes according to your UI design, the same background will be apply on all boxes automatically.<br/>
	 for set the Background you can use these properties :<br/>
	 from XML : `app:otpBackground="@drawable/bg_otp"`<br/>
	 from Code: `builder.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_otp_rectangle_code))`


	
## Support

For support, email singhkishanpanwar@gmail.com or send message to Skype : https://join.skype.com/invite/fBcaUWiNAWTh



