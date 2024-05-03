package com.example.sundial

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class AccountDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account_details)

        val user = FirebaseAuth.getInstance().currentUser

        val email = user!!.email.toString()
        val password = "Inaccessible"
        val username = user.uid.toString()
        val profilePic = user.photoUrl.toString()

        var emailText = findViewById<EditText>(R.id.edtEmail3).text
        var passwordtext = findViewById<EditText>(R.id.edtPassword3).text
        var usernametext = findViewById<EditText>(R.id.edtUsername2).text
        var profilePictext = findViewById<EditText>(R.id.edtDisplayPicURL).text

       /* emailText = email
        passwordtext = password
        usernametext = username
        profilePictext = profilePic */




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}