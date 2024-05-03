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
import java.time.LocalDate

class TaskCalendar : AppCompatActivity() {
    lateinit var set_event: TextView
    lateinit var DateToday: TextView
    lateinit var backButton: TextView
    lateinit var forwardButton: TextView
    var datenow = LocalDate.now()
    var onScreenDate = datenow


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
            val intent = Intent(this, InsertData::class.java)
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

        //Variables for each button on Navbar™
        val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
        val ProfileOpenActivity = findViewById<ImageButton>(R.id.ib_Profile)
        val CalendarOpenActivity = findViewById<ImageButton>(R.id.ib_Calendar)
        val TimerOpenActivity = findViewById<ImageButton>(R.id.ib_Timer)

        //Intent to open Home Page
        HomeOpenActivity.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        //Intent to open Profile
        ProfileOpenActivity.setOnClickListener {
            val intent1 = Intent(this, Profile::class.java)
            startActivity(intent1)
        }

        //Intent to open Calendar
        CalendarOpenActivity.setOnClickListener {
            val intent3 = Intent(this, TaskCalendar::class.java)
            startActivity(intent3)
        }

        //Intent to Open Timer
        TimerOpenActivity.setOnClickListener {
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}