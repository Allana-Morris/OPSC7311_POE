package com.example.opsc7311poe

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
    private val navBar = Navbar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val HomeOpenActivity = findViewById<TextView>(R.id.tv_Home)
        val ProfileOpenActivity = findViewById<TextView>(R.id.tv_Profile)
        val CalendarOpenActivity = findViewById<TextView>(R.id.tv_calendar)
        val TimerOpenActivity = findViewById<TextView>(R.id.tv_timer)

        HomeOpenActivity.setOnClickListener()
        {
            navBar.OpenHomeButton()
        }

        ProfileOpenActivity.setOnClickListener()
        {
            navBar.OpenProfileButton()
        }

        CalendarOpenActivity.setOnClickListener()
        {
            navBar.OpenCalendarButton()
        }

        TimerOpenActivity.setOnClickListener()
        {
            navBar.OpenTimerButton()
        }
    }
}