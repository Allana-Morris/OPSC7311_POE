package com.example.opsc7311poe


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    //Below is Firebase Variable
    val AppDataBase = Firebase.firestore
    lateinit var bottomNav: BottomNavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //Variables for each button on Navbar™

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.selectedItemId = R.id.home
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }

                R.id.profile -> {
                    startActivity(Intent(this, Profile_activity::class.java))
                    true
                }

                R.id.calendar -> {
                    startActivity(Intent(this, TaskCalendar_activity::class.java))
                    true
                }

                R.id.timer -> {
                    startActivity(Intent(this, Timer_activity::class.java))
                    true
                }

                else -> {
                    false
                }
            }
        }


        val activityCreateTask = findViewById<Button>(R.id.btnTask)
        val activityCreateCategory = findViewById<Button>(R.id.btnCategory)
        val activityCreateEntry = findViewById<Button>(R.id.btnTimesheet)
        val activityViewTask = findViewById<Button>(R.id.btnViewTasks)
        val activityViewEntries = findViewById<Button>(R.id.btnViewEntries)
        val activityCatHours = findViewById<Button>(R.id.btnCatHours)
        val activityViewGraph = findViewById<Button>(R.id.btnViewGraph)
        val activityMonthlySummary = findViewById<Button>(R.id.btnMonthlySummary)

        //onClickListener to open InsertData page
        activityCreateTask.setOnClickListener {

            val Taskintent = Intent(this, InsertData_activity::class.java)
            startActivity(Taskintent)
        }

        //onClickListener to open Create Category page
        activityCreateCategory.setOnClickListener{

            val Catintent = Intent(this, create_category_activity::class.java)
            startActivity(Catintent)
        }

        //onClickListener to open Enter Timesheet page
        activityCreateEntry.setOnClickListener{

            val Entryintent = Intent(this, Create_Entry_activity::class.java)
            startActivity(Entryintent)
        }

        activityViewTask.setOnClickListener{

            val ViewTaskintent = Intent(this, ViewTasks_activity::class.java)
            startActivity(ViewTaskintent)
        }

        activityViewEntries.setOnClickListener{

            val ViewEntriesintent = Intent(this, ViewTimeSheetEntry_activity::class.java)
            startActivity(ViewEntriesintent)
        }

        activityCatHours.setOnClickListener{

            val CatViewintent = Intent(this, cat_total_activity::class.java)
            startActivity(CatViewintent)
        }

        activityCatHours.setOnClickListener{

            val CatViewintent = Intent(this, cat_total_activity::class.java)
            startActivity(CatViewintent)
        }

        activityViewGraph.setOnClickListener{

            val ViewGraphintent = Intent(this, ViewGraphTotalHours::class.java)
            startActivity(ViewGraphintent)
        }

        activityMonthlySummary.setOnClickListener{

            val MonthlySummaryintent = Intent(this, MonthlySummary::class.java)
            startActivity(MonthlySummaryintent)
        }

        //Code used to Welcome User once logged in
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            val headerText: TextView = findViewById(R.id.tvBlackBox)
            val username = SessionUser.currentUser?.username
            val welcomeMessage = getString(R.string.homeWelcomeBack)
            headerText.text = if (username != null) {
                "$welcomeMessage $username"
            } else {
                "$welcomeMessage, guest"
            }



            insets

        }
    }

}
