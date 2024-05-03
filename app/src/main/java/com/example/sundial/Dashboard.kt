package com.example.sundial

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Dashboard : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drwLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var database: DatabaseReference
    private lateinit var list: ArrayList<NewTimesheetClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        userRecyclerView = findViewById(R.id.timesheetList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        list = arrayListOf<NewTimesheetClass>()

        getUserData()

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

        /*    userRecyclerView = findViewById(R.id.timesheetList)
            userRecyclerView.layoutManager = LinearLayoutManager(this)
            userRecyclerView.setHasFixedSize(true)

            list = arrayListOf<NewTimesheetClass>()

            getUserData() */

        }

        val toolbar : Toolbar = findViewById(R.id.toolBar)
        setSupportActionBar(toolbar)

        drwLayout = findViewById(R.id.DrwLayout)
        toggle = ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.open, R.string.close)
        drwLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView = findViewById(R.id.nav_view)

        navView.setNavigationItemSelectedListener {
            drwLayout.closeDrawer(GravityCompat.START)
            when(it.itemId) {
                R.id.itmAccount -> {intent = Intent(this, AccountDetails::class.java)
                startActivity(intent) }
                R.id.itmAddCategory -> {intent = Intent(this, AddCategory::class.java)
                    startActivity(intent) }
                R.id.itmAddTimeSheet -> {intent = Intent(this, AddTimesheet::class.java)
                    startActivity(intent) }
                R.id.itmDashboard -> {intent = Intent(this, Dashboard::class.java)
                    startActivity(intent) }
                R.id.itmViewTimeSheetEntries -> {intent = Intent(this, ViewTimesheetEntries::class.java)
                    startActivity(intent) }
                R.id.itmSettings -> Toast.makeText(applicationContext, "Open Settings Layout", Toast.LENGTH_SHORT).show()
                R.id.itmLogout -> { FirebaseAuth.getInstance().signOut()
                    intent = Intent(this, MainActivity::class.java)
                finish()}

            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
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