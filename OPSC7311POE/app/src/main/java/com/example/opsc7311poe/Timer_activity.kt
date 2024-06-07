package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.sql.Time
import java.util.*

class Timer_activity : AppCompatActivity() {
    private lateinit var edtTime: TextClock
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button
    private var isTimerRunning = false
    private var secondsElapsed = 0
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        edtTime = findViewById(R.id.edtClock)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)
        edtTime.setText("00:00:00")

        val btnSave: Button = findViewById(R.id.saveTimebtn)
        val spinCat: Spinner = findViewById(R.id.spinCat)
        val spinTask: Spinner = findViewById(R.id.spinTask)

        var startTime = Time(0, 0, 0);
        var endTime = Time(0, 0, 0);

        // Creating a fake category and task seeing as we have no way of inputting stuff n things
        // val selectedCat = Category("Work", 1, 2, 4.0, 8.0)
        // Adding the first category to the user category hashmap
        //  SessionUser.currentUser?.categories?.put(selectedCat.name, selectedCat)

        // Creating another fake category and task
        // val fakeCat = Category("Work2", 1, 2, 4.0, 8.0)
        // Adding the second category to the user category hashmap with a different key
        //  SessionUser.currentUser?.categories?.put(fakeCat.name, fakeCat)

        // Adding the task to the first category for the user
        // val selectedTask = Task("Sex ", "Great cardio", true, 36.0, 36.0)
        //selectedCat.tasks[selectedTask.name] = selectedTask


        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
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


        // Create a list to hold category names
        val categoryList = mutableListOf<String>()
        val taskList = mutableListOf<String>()

        // Retrieve user's categories and populate the category list
        SessionUser.currentUser?.categories?.forEach { (name, _) ->
            categoryList.add(name)
        }

        // Set up category spinner
        if (categoryList.isEmpty()) {
            spinCat.isEnabled = false
            spinCat.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("No categories"))
            spinTask.isEnabled = false
            spinTask.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("No tasks"))
            btnSave.isEnabled = false
        } else {
            spinCat.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
            spinCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedCategoryName = categoryList[position]
                    val tasks =
                        SessionUser.currentUser?.categories?.get(selectedCategoryName)?.tasks?.keys?.toList()
                            ?: emptyList()

                    spinTask.adapter =
                        ArrayAdapter(this@Timer_activity, android.R.layout.simple_spinner_item, tasks)
                    spinTask.isEnabled = tasks.isNotEmpty()
                    btnSave.isEnabled = tasks.isNotEmpty()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    spinTask.isEnabled = false
                    spinTask.adapter = ArrayAdapter<String>(
                        this@Timer_activity,
                        android.R.layout.simple_spinner_item,
                        emptyList()
                    )
                    btnSave.isEnabled = false
                }
            }
        }

        btnSave.setOnClickListener {
            if (!isTimerRunning) {
                val selectedTaskName = spinTask.selectedItem.toString()

                // Get the selected category name
                val selectedCategoryName = spinCat.selectedItem.toString()

                // Get the selected category from the user's hashmap
                val selectedCategory =
                    SessionUser.currentUser?.categories?.get(selectedCategoryName)

                // If selectedCategory is not null and it contains the selected task
                if (selectedCategory != null && selectedCategory.tasks.containsKey(selectedTaskName)) {
                    val selectedTask = selectedCategory.tasks[selectedTaskName]

                    // Your code to work with the selected task goes here
                    if (selectedTask != null) {
                        /*val timed = edtTime.text.toString()
                        edtTime.setText("00:00:00")
                        secondsElapsed = 0

                        val parts = timed.split(":")
                        val hours = parts[0].toInt()
                        val minutes = parts[1].toInt()
                        val seconds = parts[2].toInt()

                        val time = Time(
                            hours,
                            minutes,
                            seconds
                        ) // Assuming Time is a custom class representing time*/

                        val currentDate =
                            Date() // Assuming you have already initialized currentDate


                        // Calculate duration here if needed
                        val durationInMillis = endTime.time - startTime.time

// Convert duration in milliseconds to seconds
                        val seconds = durationInMillis / 1000

// Calculate hours, minutes, and seconds
                        val hours = seconds / 3600
                        val minutes = (seconds % 3600) / 60
                        val remainingSeconds = seconds % 60

// Format duration as "HH:MM:SS"
                        val duration =
                            String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)

                        val rec = Recording(currentDate, startTime, endTime, duration, null)

                        //adding a recording object to the list in the right task
                        selectedTask.taskRecords.add(rec)

                        for (recording in selectedTask.taskRecords) {
                            if (recording.RecDate == currentDate) {
                                Toast.makeText(
                                    this,
                                    "Recording duration: " + recording.Duration,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Selected task is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Selected task or category is null", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }



        btnStart.setOnClickListener {
            if (!isTimerRunning) {
                var currentTimeMillis = System.currentTimeMillis()
                startTime = Time(currentTimeMillis)
                startTimer()
            } else {
                stopTimer()
                var currentTimeMillis = System.currentTimeMillis()
                endTime = Time(currentTimeMillis)
            }
        }

        btnReset.setOnClickListener()
        {
            secondsElapsed = 0;
            //for debug purposes display all the recordings for selected Task


        }

        btnStop.setOnClickListener {
            if (secondsElapsed != 0) {
                stopTimer()
                var currentTimeMillis = System.currentTimeMillis()
                endTime = Time(currentTimeMillis)
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
/* ya im killing my self
⠀⣠⣅⡧⠤⠴⠶⠾⢿⣟⠃⠀⢉⣽⠛⠻⣯⡉⠀⢠⣿⣤⣤⣤⣿⣿⣷⣶⣶⣾⣿⣿⣧⣶⣾⣿⣿⣿⣿⣯⣴⣷⣾⣷⣬⣿⣷⡞⠉⠰⣿⠇
⠀⠹⣿⣷⠖⢲⣶⣶⢿⣿⢃⣤⣌⣿⣦⡀⢼⣧⣾⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢟⣉⣹⣏⡉⠻⣿⣿⡆⠂⠐⠀
⠀⠀⣾⣿⢿⡿⢠⡥⠀⠻⠋⠀⠙⢿⠂⢹⣾⠟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢏⡴⠋⠩⠘⠉⠙⠳⣄⠈⣿⡄⠀⢀
⢠⣿⣿⠁⢸⣿⡟⠀⠀⠀⠀⠀⠀⢸⡇⣾⣧⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠈⠀⠀⠀⠀⠀⠀⠀⠹⣆⢹⣷⠀⠾
⠀⠀⠀⠀⠀⢻⡻⣄⠀⠀⠀⠀⢠⡟⣰⣿⣿⣿⠏⠙⠻⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⣿⣛⢿⣟⣦⠀⠀⠀⠀⠀⠀⠀⠀⡿⢠⡿⠀⠀
⠀⠁⠀⠀⠀⠀⢳⣍⠳⣤⣞⣰⠏⣿⣿⡟⠋⠀⠀⠀⠀⠀⠀⠈⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠉⠻⣶⣿⣿⣯⡳⣄⣤⠀⠀⢀⡼⠁⣿⣧⡄⢀
⠀⠀⠲⠀⠀⠀⠀⠙⠿⠿⣽⣥⣾⣿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡈⢻⣿⣿⣿⣮⡉⠳⣶⣋⣠⣾⣿⣿⡄⣸
⠀⠀⠈⠁⠀⠀⠰⣶⣶⡾⠋⢡⣿⠟⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⠀⠹⠋⠻⢷⣿⣿⠟⣋⠙⣿⣯⣤⠋⣿
⠈⠀⠊⠀⠀⠀⠀⠹⠋⠀⠀⣸⡏⣐⣤⣤⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠰⢖⡀⢹⣿⣷⡀⢼⣿⠟⣿⠀⠘
⢀⣀⡀⠀⠀⠀⠀⠀⣰⡆⡀⣿⠙⠛⠋⠉⠙⠻⣿⠇⠀⠀⠀⠀⠀⢾⣿⠿⠛⠛⠛⠻⢿⡶⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⠺⡏⠀⣉⢠⣀
⠘⣿⣿⡷⠂⠀⠀⠀⠈⠠⠀⣿⣦⠀⢀⣴⣿⣷⣮⣁⠀⠀⠀⠀⠀⠀⣠⣴⠶⢦⠶⡷⠀⠀⠀⠀⠀⠠⡄⠀⠀⡄⢸⣿⣿⣿⣾⣧⡀⣽⣾⣽
⠀⠋⠁⠀⠀⢰⣷⣦⡀⠀⠀⣼⣿⢀⣼⣿⣿⠛⢻⣟⢻⡍⠉⢀⣴⣿⣿⣷⠞⢿⣟⢲⣿⣆⠀⠀⠀⠙⠻⣤⣼⣶⣻⣿⣿⣿⢿⣿⣿⠈⢻⣿
⠀⠀⠀⠀⠀⠀⣿⣿⣿⠗⠀⢹⣧⠚⣿⣿⠻⣆⠈⠛⠿⡇⠀⢸⣿⣿⣿⣿⡄⠈⠛⠟⢋⣿⠀⠀⠀⠀⠀⢿⣿⣟⠻⣿⣿⣿⣾⣟⣿⣷⡄⢿
⠀⠀⠀⠈⠀⠀⠛⠋⠁⠀⠀⢸⣿⣷⡜⣿⣶⣿⣷⣶⣾⠃⠀⠀⢿⣿⣿⣿⣿⣷⣶⣶⣿⡏⠀⠀⠀⠀⠀⢸⣿⣿⣆⣸⣿⣿⣷⡛⢻⣿⡇⠸
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⠿⠿⣿⣿⣿⣿⣷⣶⣶⡄⠻⢿⣿⣿⡿⣿⣟⠋⠀⠀⠀⠀⢀⣤⠘⣿⣿⣿⣿⡟⣿⣿⣿⡇⣀⣴⡆
⠀⠀⠀⠀⠀⠀⠀⠀⠈⠂⠀⢸⡿⠉⠀⠀⠀⡿⠿⣿⣿⣿⡿⠟⠃⠀⠀⠀⠀⠀⠀⠈⠳⡄⠀⠀⠠⡼⠃⠀⣿⣿⣿⢿⢤⠸⣿⣛⣇⣿⣿⠃
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡾⠁⠀⠀⠀⠀⠀⠀⠀⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡆⢀⠀⠀⠀⠀⣿⣿⣿⡏⣼⣦⢿⠉⣵⣿⣽⣆
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⢈⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⢸⡷⠀⠀⠀⢀⣴⢿⣿⣿⣷⣿⡟⢸⡟⠻⠟⠸⠟
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠀⠀⢷⡀⢄⠀⠀⠀⠀⠀⠀⠀⢠⠏⠀⣸⣿⣠⣤⠶⠛⣻⡟⠀⠀⣿⣿⠀⣤⣗⠀⣤⢌⠀
⠀⠀⠀⠀⠀⡀⠀⢀⡀⠀⠀⠈⠳⣄⡀⠀⠀⠀⠀⠀⠘⣯⡀⠀⣀⠀⠀⠀⠀⢀⣯⡴⢿⣿⣾⠿⢿⣤⢼⡿⠁⠀⠀⣼⣷⣦⠉⣿⠀⢛⡈⠀
⠀⠀⠀⠀⠀⠛⠛⡂⠀⠁⠀⠀⠀⣈⣿⡟⠲⣖⠚⠉⣿⣽⣟⡓⠒⠶⣶⡶⠚⣻⣯⣤⣾⣿⡀⣠⡼⠟⠈⠀⠀⠀⣼⣏⣿⡟⠀⣿⠀⣼⡅⠠
⠀⠀⠀⠀⡄⠀⠀⠈⠀⢠⣠⣶⣿⡟⣿⣷⠒⢹⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⢿⡏⢀⡨⠟⠉⠀⠀⡄⠀⢠⣴⣿⣿⣭⣇⠀⣿⣶⡿⢿⠿
⠀⠀⠀⠀⠀⠀⠀⠆⠀⣾⣿⠿⣿⠿⣿⣟⣷⣴⡋⠉⠙⣿⣿⡿⠋⠉⢻⣿⣅⣠⣤⠟⠋⠁⠀⠀⠀⢰⡇⣠⣾⠋⠛⢿⣿⣿⣿⠟⠞⠁⠀⠀
⡀⠀⠀⠀⠀⠀⠀⠀⢰⣿⡿⠀⠒⠀⣿⠿⠻⠈⠙⠛⢛⡛⠻⠿⠶⠶⠛⠛⠉⠉⠀⠀⠀⠀⠀⢲⡀⢈⣿⠋⠉⠀⣦⣦⢻⣿⡇⠀⠀⠀⠀⠀
⢴⠃⠀⠀⠀⠀⠀⠀⣾⡃⠀⠀⠀⣴⣿⣷⣧⡐⠎⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣸⡷⠿⡿⠀⠀⠀⣿⣇⠘⢿⡇⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⢀⣴⣿⡿⠃⣄⣀⣼⣿⣿⣽⠟⠿⢶⣶⣶⣤⣤⣤⠤⣤⣦⣤⣤⣄⣀⣴⣶⣾⣿⣿⡇⠀⠀⠀⠀⠀⠉⠉⠀⣀⣗⠀⠀⠀⠀⢀*/