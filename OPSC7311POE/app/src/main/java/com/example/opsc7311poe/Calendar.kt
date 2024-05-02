package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.opsc7311poe.R.id
import java.time.LocalDate
import java.util.Calendar

class Calendar : AppCompatActivity() {

    val set_event = findViewById<TextView>(id.tv_add_event)
    var DateToday = findViewById<TextView>(id.dayOfWeekTV)
    val backButton = findViewById<TextView>(id.tv_Back)
    val forwardButton = findViewById<TextView>(id.tv_Forward)
    var datenow = LocalDate.now()
    var onScreenDate = datenow

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        DateToday.text = datenow.toString()

        set_event.setOnClickListener {
            val intent = Intent(this, InsertData::class.java)
            startActivity(intent)
        }

        backButton.setOnClickListener {
            onScreenDate = onScreenDate.minusDays(1)
            DateToday.text = onScreenDate.toString()
            datenow = onScreenDate
        }

        forwardButton.setOnClickListener {
            onScreenDate = onScreenDate.plusDays(1)
            DateToday.text = onScreenDate.toString()
            datenow = onScreenDate
        }

        val HomeOpenActivity = findViewById<ImageButton>(id.ib_Home)
        val ProfileOpenActivity = findViewById<ImageButton>(id.ib_Profile)
        val CalendarOpenActivity = findViewById<ImageButton>(id.ib_Calendar)
        val TimerOpenActivity = findViewById<ImageButton>(id.ib_Timer)

        HomeOpenActivity.setOnClickListener{
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        ProfileOpenActivity.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        CalendarOpenActivity.setOnClickListener{
            val intent3 = Intent(this, Calendar::class.java)
            startActivity(intent3)
        }

        TimerOpenActivity.setOnClickListener{
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

    }
}