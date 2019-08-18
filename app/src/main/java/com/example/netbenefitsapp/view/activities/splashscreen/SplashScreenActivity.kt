package com.example.netbenefitsapp.view.activities.splashscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.netbenefitsapp.R
import com.example.netbenefitsapp.model.User
import com.google.firebase.auth.FirebaseUser
import android.os.CountDownTimer
import android.widget.ImageView
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.netbenefitsapp.view.activities.main.MainActivity
import com.squareup.picasso.Picasso

class SplashScreenActivity : AppCompatActivity() {

    private var imageAlpha : Float = 1F
    private lateinit var ivSplashScreen : ImageView
    private lateinit var tvSplashScreen : TextView
    private var mFirebaseUser : FirebaseUser? = null
    private var mUser : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()
        //supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.color_green)))

        ivSplashScreen = findViewById(R.id.ivSplashScreen)
        tvSplashScreen = findViewById(R.id.tvSplashScreen)

        val bundle = intent.getBundleExtra("bundle")
        mFirebaseUser = bundle.getParcelable("firebaseUser")
        mUser = bundle.getParcelable("user")

        Picasso.get().load(mUser?.companyLogoUrl).into(ivSplashScreen)
        tvSplashScreen.text = "Hello, " + mUser!!.firstName

        splashScreenUseTimer(5000)
    }

    // Show splash screen in fixed time.
    private fun splashScreenUseTimer(milliSeconds: Long) {

        // Create a count down timer object.It will count down every 0.1 seconds and last for milliSeconds milliseconds..
        val countDownTimer = object : CountDownTimer(milliSeconds, 100) {
            override fun onTick(l: Long) {
                // Reduce the splash screen background image's alpha value for each count down.
                ivSplashScreen.setAlpha(imageAlpha)
                imageAlpha -= 0.1.toFloat()

                if (imageAlpha <= 0) {
                    imageAlpha = 1F
                }
            }

            override fun onFinish() {
                // When count down complete, set the image to invisible.
                imageAlpha = 0F
                ivSplashScreen.setAlpha(imageAlpha)
            }
        }
        // Start the count down timer.
        countDownTimer.start()

        // Create a new Handler object.
        val splashScreenHandler = Handler()
        // Create a thread object.
        val splashScreenThread = Runnable {

            val userIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable("user", mUser)
            bundle.putParcelable("firebaseUser", mFirebaseUser)
            userIntent.putExtra("bundle", bundle)
            this@SplashScreenActivity.startActivity(userIntent)
            this@SplashScreenActivity.finish()
        }
        // Execute splashScreenThread after 5 seconds.
        splashScreenHandler.postDelayed(splashScreenThread, milliSeconds)
    }


}
