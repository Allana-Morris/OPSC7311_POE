package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
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

        CalendarOpenActivity.setOnClickListener{
            val intent3 = Intent(this, TaskCalendar::class.java)
            startActivity(intent3)
        }

        TimerOpenActivity.setOnClickListener{
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }


    }
}