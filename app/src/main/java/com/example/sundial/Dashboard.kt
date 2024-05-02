package com.example.sundial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Dashboard : AppCompatActivity() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var list: ArrayList<NewTimesheetClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)


        val buttonClick = findViewById<Button>(R.id.btnAddCategory2)
        buttonClick.setOnClickListener {
            val intent = Intent(this, AddCategory::class.java)
            startActivity(intent)
        }

        val buttonClick2 = findViewById<Button>(R.id.btnAddTimesheet2)
        buttonClick2.setOnClickListener {
            val intent2 = Intent(this, AddTimesheet::class.java)
            startActivity(intent2)
        }

        val buttonClick3 = findViewById<Button>(R.id.btnRefresh)
        buttonClick3.setOnClickListener {

            userRecyclerView = findViewById(R.id.timesheetList)
            userRecyclerView.layoutManager = LinearLayoutManager(this)
            userRecyclerView.setHasFixedSize(true)

            list = arrayListOf<NewTimesheetClass>()

            getUserData()

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drwLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getUserData() {

        database = FirebaseDatabase.getInstance().getReference("test category")

        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {

                        val timesheet = userSnapshot.getValue(NewTimesheetClass::class.java)
                        list.add(timesheet!!)

                    }

                    userRecyclerView.adapter = ListAdapterNew(list)

                }


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })

    }

}