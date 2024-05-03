package com.example.sundial

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewTimesheetEntries : AppCompatActivity() {

    private lateinit var category : String
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var list: ArrayList<NewTimesheetClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_timesheet_entries)

        val buttonClick3 = findViewById<Button>(R.id.btnDisplay)
        buttonClick3.setOnClickListener {

            category = findViewById<EditText>(R.id.edtChooseCategory).text.toString()

            userRecyclerView = findViewById(R.id.timesheetList2)
            userRecyclerView.layoutManager = LinearLayoutManager(this)
            userRecyclerView.setHasFixedSize(true)

            list = arrayListOf<NewTimesheetClass>()

            getUserData()

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getUserData() {

        database = FirebaseDatabase.getInstance().getReference(category)

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