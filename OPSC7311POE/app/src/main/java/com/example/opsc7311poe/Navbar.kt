package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class Navbar : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }

    public fun NavbarActions() {
        val HomeOpenActivity = findViewById<TextView>(R.id.tv_Home)
        val ProfileOpenActivity = findViewById<TextView>(R.id.tv_Profile)
        val CalendarOpenActivity = findViewById<TextView>(R.id.tv_calendar)
        val TimerOpenActivity = findViewById<TextView>(R.id.tv_timer)

        HomeOpenActivity.setOnClickListener()
        {
            OpenHomeButton()
        }

        ProfileOpenActivity.setOnClickListener()
        {
            OpenProfileButton()
        }

        CalendarOpenActivity.setOnClickListener()
        {
            OpenCalendarButton()
        }

        TimerOpenActivity.setOnClickListener()
        {
            OpenTimerButton()
        }
    }

    public fun OpenProfileButton() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }

    public fun OpenHomeButton() {
        val intent2 = Intent(this, MainActivity::class.java)
        startActivity(intent2)
    }

    public fun OpenCalendarButton() {
        val intent3 = Intent(this, Calendar::class.java)
        startActivity(intent3)
    }

    public fun OpenTimerButton() {
        val intent4 = Intent(this, Timer::class.java)
        startActivity(intent4)
    }

}