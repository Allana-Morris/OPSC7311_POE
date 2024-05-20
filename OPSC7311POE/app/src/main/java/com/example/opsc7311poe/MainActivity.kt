package com.example.opsc7311poe


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    val AppDataBase = Firebase.firestore
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //Variables for each button on Navbar™
        val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
        val ProfileOpenActivity = findViewById<ImageButton>(R.id.ib_Profile)
        val CalendarOpenActivity = findViewById<ImageButton>(R.id.ib_Calendar)
        val TimerOpenActivity = findViewById<ImageButton>(R.id.ib_Timer)

        //Intent to open Home Page
        HomeOpenActivity.setOnClickListener{
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        //Intent to open Profile
        ProfileOpenActivity.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
/*
        //Intent to open Calendar
        CalendarOpenActivity.setOnClickListener{
            val intent3 = Intent(this, TaskCalendar::class.java)
            startActivity(intent3)
        }
*/
        //Intent to Open Timer
        TimerOpenActivity.setOnClickListener{
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

        val activityCreateTask = findViewById<Button>(R.id.btnTask)
        val activityCreateCategory = findViewById<Button>(R.id.btnCategory)
        val activityCreateEntry = findViewById<Button>(R.id.btnTimesheet)
        val activityViewTask = findViewById<Button>(R.id.btnViewTasks)
        val activityViewEntries = findViewById<Button>(R.id.btnViewEntries)
        val activityCatHours = findViewById<Button>(R.id.btnCatHours)

        //onClickListener to open InsertData page
        activityCreateTask.setOnClickListener{
            val Taskintent = Intent(this, InsertData::class.java)
            startActivity(Taskintent)
        }

        //onClickListener to open Create Category page
        activityCreateCategory.setOnClickListener{
            val Catintent = Intent(this, create_category::class.java)
            startActivity(Catintent)
        }

        //onClickListener to open Enter Timesheet page
        activityCreateEntry.setOnClickListener {
            val Entryintent = Intent(this, CreateEntry::class.java)
            startActivity(Entryintent)
        }

        activityViewTask.setOnClickListener {
            val ViewTaskintent = Intent(this, ViewData::class.java)
            startActivity(ViewTaskintent)
        }

        activityViewEntries.setOnClickListener {
            val ViewEntriesintent = Intent(this, ViewTimeSheetEntry::class.java)
            startActivity(ViewEntriesintent)
        }

        activityCatHours.setOnClickListener {
            val CatViewintent = Intent(this, cat_total::class.java)
            startActivity(CatViewintent)
        }

        //Code used to Welcome User once logged in
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            val headerText: TextView = findViewById(R.id.tvBlackBox)
            headerText.text = SessionUser.currentUser?.username ?: "its null L"
            insets

        }
    }

}