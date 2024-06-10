package com.example.sundial

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Delay

class MainActivity : AppCompatActivity() {

    private val rotationDuration = 4000L // 4 seconds
    private val moveUpDuration = 1000L // 1 second
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imgSplash)
        val sundialLogo = findViewById<TextView>(R.id.SundialLogo)

        // Rotate Animation
        val rotate = RotateAnimation(
            0f,
            1080f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        ).apply {
            interpolator = LinearInterpolator()
            duration = rotationDuration
        }

        imageView.startAnimation(rotate)

        rotate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                // Move Up Animation
                val moveUp = TranslateAnimation(0f, 0f, 0f, -200f).apply {
                    duration = moveUpDuration
                    fillAfter = true
                }
                imageView.startAnimation(moveUp)

                // Fade in App Name TextView
                sundialLogo.alpha = 0f // Make text invisible initially
                sundialLogo.animate().alpha(1f).duration = 500L // Fade in the text
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        handler.postDelayed({
            startActivity(Intent(this, LogInOrSignUp::class.java))
            finish()
        }, rotationDuration + moveUpDuration)
    }
}
