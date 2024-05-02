package com.example.sundial

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.play.core.integrity.ap

class Dashboard : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle //hamburger drawer button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.DrwLayout)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val drwLayout : DrawerLayout = findViewById(R.id.DrwLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drwLayout, R.string.open,R.string.close)
        drwLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.itmAccount -> makeText(applicationContext,"Open Account .Xml", Toast.LENGTH_SHORT).show()
                R.id.itmNewCategory -> makeText(applicationContext,"Open Category .Xml", Toast.LENGTH_SHORT).show()
                R.id.itmViewCategories -> makeText(applicationContext,"Open Category .Xml", Toast.LENGTH_SHORT).show()
                R.id.itmAddTimeSheetEntry -> setContentView(R.layout.activity_create_time_sheet_entry)
                R.id.itmEntryReports -> makeText(applicationContext,"Open Time Sheet Report .Xml", Toast.LENGTH_SHORT).show()
                R.id.itmSettings -> makeText(applicationContext,"Open Settings .Xml", Toast.LENGTH_SHORT).show()
                R.id.itmLogout -> makeText(applicationContext,"Logout User .Xml", Toast.LENGTH_SHORT).show()


            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}