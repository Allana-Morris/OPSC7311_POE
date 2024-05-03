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

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
        val ProfileOpenActivity = findViewById<ImageButton>(R.id.ib_Profile)
        val CalendarOpenActivity = findViewById<ImageButton>(R.id.ib_Calendar)
        val TimerOpenActivity = findViewById<ImageButton>(R.id.ib_Timer)

        HomeOpenActivity.setOnClickListener{
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        ProfileOpenActivity.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
/*
        CalendarOpenActivity.setOnClickListener{
            val intent3 = Intent(this, TaskCalendar::class.java)
            startActivity(intent3)
        }
*/
        TimerOpenActivity.setOnClickListener{
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

        val activityCreateTask = findViewById<Button>(R.id.btnTask)
        val activityCreateCategory = findViewById<Button>(R.id.btnCategory)
        val activityCreateEntry = findViewById<Button>(R.id.btnTimesheet)

        activityCreateTask.setOnClickListener{
            val intent = Intent(this, InsertData::class.java)
            startActivity(intent)
        }

        activityCreateCategory.setOnClickListener{
            val intent2 = Intent(this, create_category::class.java)
            startActivity(intent2)
        }

        activityCreateEntry.setOnClickListener {
            val intent3 = Intent(this, enterTimeSheet::class.java)
            startActivity(intent3)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            val headerText: TextView = findViewById(R.id.tvBlackBox)
            headerText.text = SessionUser.currentUser?.username ?: "its null L"


            insets

        }
    }

}