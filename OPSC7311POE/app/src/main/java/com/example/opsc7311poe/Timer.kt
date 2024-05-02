package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Time
import java.util.*


class Timer : AppCompatActivity() {
    private lateinit var edtTime: TextClock
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button
    private var isTimerRunning = false
    private var secondsElapsed = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        edtTime = findViewById(R.id.edtClock)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)



      /*  val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
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

       */
        //creating a fake category and task seeing as we have no way of inputting stuff n things
        val selectedCat = Category("Work", 1, 2, 4.0, 8.0)
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
            //for debug purposes display all the recordings for selected Task
            for (recording in selectedTask.taskRecords)
            {
                Toast.makeText(
                    this,
                    "Recording duration: " + recording.Duration,
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }

        btnStop.setOnClickListener {
            if (secondsElapsed != 0)
            {
                stopTimer()
                val timed = edtTime.text.toString()
                edtTime.setText("00:00:00")
                secondsElapsed = 0;

                val parts = timed.split(":")
                val hours = parts[0].toInt()
                val minutes = parts[1].toInt()
                val seconds = parts[2].toInt()

                val time = Time(
                    hours,
                    minutes,
                    seconds
                ) // Assuming Time is a custom class representing time

                val currentDate = Date()
                val rec = Recording(currentDate, 0.0, 0.0, time)
                //adding a recording object to the list in the right task
                selectedTask.taskRecords.add(rec)

                for (recording in selectedTask.taskRecords) {
                    if (recording.RecDate == currentDate) {
                        Toast.makeText(
                            this,
                            "Recording duration: " + recording.Duration,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
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
