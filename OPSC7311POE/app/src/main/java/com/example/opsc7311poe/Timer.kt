package com.example.opsc7311poe

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Time
import java.time.Clock
import java.util.*


class Timer : AppCompatActivity() {
    private val navBar = Navbar()
    private lateinit var edtTime: TextClock
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button
    private var isTimerRunning = false
    private var secondsElapsed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        edtTime = findViewById(R.id.txtClock)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)

        val HomeOpenActivity = findViewById<TextView>(R.id.tv_Home)
        val ProfileOpenActivity = findViewById<TextView>(R.id.tv_Profile)
        val CalendarOpenActivity = findViewById<TextView>(R.id.tv_calendar)
        val TimerOpenActivity = findViewById<TextView>(R.id.tv_timer)

        HomeOpenActivity.setOnClickListener {
            navBar.OpenHomeButton()
        }

        ProfileOpenActivity.setOnClickListener {
            navBar.OpenProfileButton()
        }

        CalendarOpenActivity.setOnClickListener {
            navBar.OpenCalendarButton()
        }

        TimerOpenActivity.setOnClickListener {
            navBar.OpenTimerButton()
        }

        //creating a fake category and task seeing as we have no way of inputting stuff n things
        val selectedCat = Category("Work", 1, 2.0, 4.0)
        //adding to the user category hashmap
        SessionUser.currentUser?.categories?.put(selectedCat.name, selectedCat)
        //adding the task to the category for user
        val selectedTask = Task("Sex ", "Great cardio", true, 36.0, 36.0)
        selectedCat.tasks[selectedCat.name] = selectedTask

        btnStart.setOnClickListener {
            if (!isTimerRunning) {
                startTimer()
            } else {
                stopTimer()
            }
        }

        btnReset.setOnClickListener ()
        {
            secondsElapsed = 0;
        }

        btnStop.setOnClickListener {
            stopTimer()
            val timed = edtTime.text.toString()
            edtTime.setText("0")
            secondsElapsed = 0;

            val parts = timed.split(":")
            val hours = parts[0].toInt()
            val minutes = parts[1].toInt()
            val seconds = parts[2].toInt()

            val time = Time(hours, minutes, seconds) // Assuming Time is a custom class representing time

            val currentDate = Date()
            val rec = Recording(currentDate, 0.0, 0.0, time)
            selectedTask.taskRecords.add(rec)

            for (recording in selectedTask.taskRecords) {
                if (recording.RecDate == currentDate) {
                    Toast.makeText(this, "Recording date: " + recording.RecDate, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            secondsElapsed++
            val hours = secondsElapsed / 3600
            val minutes = (secondsElapsed % 3600) / 60
            val seconds = secondsElapsed % 60
            val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            edtTime.setText(timeString)
            handler.postDelayed(this, 1000)
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        btnStart.text = "Pause"
        handler.post(runnable)
    }

    private fun stopTimer() {
        isTimerRunning = false
        btnStart.text = "Start"
        handler.removeCallbacks(runnable)
    }
}
