package com.example.sundial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LogInOrSignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in_or_sign_up)

        val buttonClick = findViewById<Button>(R.id.btnLogin)
        buttonClick.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

       val buttonClick2 = findViewById<Button>(R.id.btnSignUp)
        buttonClick2.setOnClickListener {
            val intent2 = Intent(this, SignUp::class.java)
            startActivity(intent2)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}