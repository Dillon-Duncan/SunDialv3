package com.example.sundial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.database.database

class AddTimesheet : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_timesheet)

        val database = Firebase.database

        val buttonClick = findViewById<Button>(R.id.btnAddTimesheet)
        buttonClick.setOnClickListener {

            val date: String = findViewById<EditText>(R.id.edtDate).text.toString()
            val startTime: String = findViewById<EditText>(R.id.edtStartTime).text.toString()
            val endTime: String = findViewById<EditText>(R.id.edtEndTime).text.toString()
            val description: String = findViewById<EditText>(R.id.edtDescription).text.toString()
            val category: String = findViewById<EditText>(R.id.edtCategory).text.toString()
            val imageURL: String = findViewById<EditText>(R.id.edtImageURL).text.toString()

            val timesheet : TimesheetClass = TimesheetClass(date,startTime,endTime,description,category,imageURL)

            val myRef = database.getReference(category).push()

            myRef.setValue(timesheet)

            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}