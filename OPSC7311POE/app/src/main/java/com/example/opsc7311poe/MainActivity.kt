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

        loadFragment(HomeFragment())
        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }

                R.id.calendar -> {
                    loadFragment(CalendarFragment())
                    true
                }

                R.id.timer -> {
                    loadFragment(TimerFragment())
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

        //onClickListener to open InsertData page
        activityCreateTask.setOnClickListener {

            val Taskintent = Intent(this, InsertData::class.java)
            startActivity(Taskintent)
        }

        //onClickListener to open Create Category page
        activityCreateCategory.setOnClickListener{

            val Catintent = Intent(this, create_category::class.java)
            startActivity(Catintent)
        }

        //onClickListener to open Enter Timesheet page
        activityCreateEntry.setOnClickListener{

            val Entryintent = Intent(this, CreateEntry::class.java)
            startActivity(Entryintent)
        }

        activityViewTask.setOnClickListener{

            val ViewTaskintent = Intent(this, ViewData::class.java)
            startActivity(ViewTaskintent)
        }

        activityViewEntries.setOnClickListener{

            val ViewEntriesintent = Intent(this, ViewTimeSheetEntry::class.java)
            startActivity(ViewEntriesintent)
        }

        activityCatHours.setOnClickListener{

            val CatViewintent = Intent(this, cat_total::class.java)
            startActivity(CatViewintent)
        }

        //Code used to Welcome User once logged in
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main))
        { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            val headerText: TextView = findViewById(R.id.tvBlackBox)
            headerText.text = SessionUser.currentUser?.username ?: "its null L"
            insets

        }
    }

        private fun loadFragment(fragment: Fragment) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.commit()
        }
    }
