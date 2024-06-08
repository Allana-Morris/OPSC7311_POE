package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.type.DateTime
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class TaskCalendar_activity : AppCompatActivity() {
    lateinit var set_event: TextView
    lateinit var DateToday: TextView
    lateinit var backButton: TextView
    lateinit var forwardButton: TextView
    var datenow = LocalDate.now()
    var onScreenDate = datenow
    lateinit var bottomNav: BottomNavigationView


    private val dateFormat = SimpleDateFormat("dd MM", Locale.getDefault())
    private var currentDate = Calendar.getInstance()

    private val tasks = listOf(
       Task("Meeting", "Discuss project status", true, 10.0, 11.0),
       // Task("Coding", "Work on feature X", false, 11.5, 13.0),
       // Task("Lunch", "Take a break", true, 13.0, 14.0),
       // Task("Review", "Code review", false, 14.0, 15.0)
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_calendar)

        // Initialize views
        set_event = findViewById<TextView>(R.id.btnNewEvent)
        DateToday = findViewById<TextView>(R.id.dayOfWeekTV)
        backButton = findViewById<TextView>(R.id.tv_Back)
        forwardButton = findViewById<TextView>(R.id.tv_Forward)
        var taskListing = findViewById<RecyclerView>(R.id.rvCalendar)
        DateToday.text = dateFormat.format(currentDate.time)

        taskListing.layoutManager = LinearLayoutManager(this)
        taskListing.adapter = TaskAdapter(tasks)

        //Opens Create Task page when clicked
        set_event.setOnClickListener {
            val intent = Intent(this, InsertData_activity::class.java)
            startActivity(intent)
        }

        //Goes back a day (Date Displayed minus one)
        backButton.setOnClickListener {
            currentDate.add(Calendar.DAY_OF_YEAR, -1)
            updateDateDisplay()
        }

        //Goes forward a day (Date Displayed plus one)
        forwardButton.setOnClickListener {
            currentDate.add(Calendar.DAY_OF_YEAR, 1)
            updateDateDisplay()
        }

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)!!
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

    private fun updateDateDisplay() {
        DateToday.text = dateFormat.format(currentDate.time)
    }

}