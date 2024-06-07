package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate

class TaskCalendar_activity : AppCompatActivity() {
    lateinit var set_event: TextView
    lateinit var DateToday: TextView
    lateinit var backButton: TextView
    lateinit var forwardButton: TextView
    var datenow = LocalDate.now()
    var onScreenDate = datenow
    lateinit var bottomNav: BottomNavigationView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_calendar)


        // Initialize views
        set_event = findViewById<TextView>(R.id.tv_add_event)
        DateToday = findViewById<TextView>(R.id.dayOfWeekTV)
        backButton = findViewById<TextView>(R.id.tv_Back)
        forwardButton = findViewById<TextView>(R.id.tv_Forward)
        var taskListing = findViewById<ListView>(R.id.listView)
        //Sets Textview to current Date
        DateToday.text = datenow.toString()

        //Opens Create Task page when clicked
        set_event.setOnClickListener {
            val intent = Intent(this, InsertData_activity::class.java)
            startActivity(intent)
        }

        //Goes back a day (Date Displayed minus one)
        backButton.setOnClickListener {
            onScreenDate = onScreenDate.minusDays(1)
            DateToday.text = onScreenDate.toString()
            datenow = onScreenDate
        }

        //Goes forward a day (Date Displayed plus one)
        forwardButton.setOnClickListener {
            onScreenDate = onScreenDate.plusDays(1)
            DateToday.text = onScreenDate.toString()
            datenow = onScreenDate
        }

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.selectedItemId = R.id.calendar
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}