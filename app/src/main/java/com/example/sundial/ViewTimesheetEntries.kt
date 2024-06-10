package com.example.sundial

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

class ViewTimesheetEntries : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drwLayout: DrawerLayout
    private lateinit var navView: NavigationView
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.DrwLayoutViewTimeSheet)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar : Toolbar = findViewById(R.id.toolBarViewTimeSheet)
        setSupportActionBar(toolbar)

        drwLayout = findViewById(R.id.DrwLayoutViewTimeSheet)
        toggle = ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.open, R.string.close)
        drwLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView = findViewById(R.id.nav_view_viewTimeSheet)

        navView.setNavigationItemSelectedListener {
            drwLayout.closeDrawer(GravityCompat.START)
            when(it.itemId) {
                R.id.itmAccount -> {intent = Intent(this, AccountDetails::class.java)
                    startActivity(intent) }
                R.id.itmAnalyticalGraph -> {
                    intent = Intent(this, AnalyticsGraph::class.java)
                    startActivity(intent)
                }
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