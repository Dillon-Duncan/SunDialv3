package com.example.sundial

import android.content.Intent
import android.os.Bundle
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
import com.google.firebase.auth.FirebaseAuth

class AnalyticsGraph : AppCompatActivity() {
    private lateinit var drwLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_analytics_graph)


            val toolbar : Toolbar = findViewById(R.id.toolBarAnalyticalGraph)
            setSupportActionBar(toolbar)

            drwLayout = findViewById(R.id.DrwLayoutAnalyticsGraph)
            toggle = ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.open, R.string.close)
            drwLayout.addDrawerListener(toggle)
            toggle.syncState()

            navView = findViewById(R.id.nav_view_analytical_graph)

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
}