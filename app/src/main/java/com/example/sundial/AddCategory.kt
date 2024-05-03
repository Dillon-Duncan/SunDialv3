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
import com.google.android.material.navigation.NavigationView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class AddCategory : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drwLayout: DrawerLayout
    private lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_category)

        val database = Firebase.database

        val buttonClick = findViewById<Button>(R.id.btnAddCategory)
        buttonClick.setOnClickListener {

            val category: String = findViewById<EditText>(R.id.edtAddCategory).text.toString()

            val myRef = database.getReference(category)

            myRef.setValue("new category")

            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.DrwLayoutAddCategory)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar : Toolbar = findViewById(R.id.toolBarAddCategory)
        setSupportActionBar(toolbar)

        drwLayout = findViewById(R.id.DrwLayoutAddCategory)
        toggle = ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.open, R.string.close)
        drwLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView = findViewById(R.id.nav_view_addCategory)

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

}