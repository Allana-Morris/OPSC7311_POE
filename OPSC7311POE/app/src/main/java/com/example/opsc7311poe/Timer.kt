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
        edtTime.setText("00:00:00")


        edtTime = findViewById(R.id.edtClock)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)
        val btnSave  : Button = findViewById(R.id.saveTimebtn)
        val spnCat : Spinner = findViewById(R.id.spinCat)
        val spnTask : Spinner = findViewById(R.id.spinTask)

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


        // Create a list to hold category names
        val categoryList = mutableListOf<String>()
        val taskList = mutableListOf<String>()

        // If there are no categories, disable the task spinner and display "No categories"
        if (categoryList.isEmpty()) {
            spnCat.isEnabled = false
            spnCat.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOf("No categories"))

            spnTask.isEnabled = false
            spnTask.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listOf("No Tasks"))
            btnSave.isEnabled = false;
        } else {
            // If there are categories, set the spinner to enabled and set its adapter to empty
            spnTask.isEnabled = true
            spnCat.isEnabled = true
            btnSave.isEnabled = true;
            spnTask.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emptyList())
            spnCat.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emptyList())
        }

        // Iterate through the user's categories and add their names to the list
        SessionUser.currentUser?.categories?.forEach { (name, _) ->
            categoryList.add(name)
        }

        // Create an adapter for the category Spinner
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCat.adapter = categoryAdapter

        //set task spinner to disabled and empty
        spnTask.isEnabled = false
        spnTask.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emptyList())

        // Set a listener for category selection to update the task Spinner
        spnCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Get the selected category name
                val selectedCategoryName = categoryList[position]

                // Create a list to hold task names for the selected category
                taskList.clear() // Clear the existing task list

                // Get the selected category from the user's hashmap
                val selectedCategory = SessionUser.currentUser?.categories?.get(selectedCategoryName)

                // If selectedCategory is not null and it contains tasks, add their names to the list
                if (selectedCategory != null && selectedCategory.tasks.isNotEmpty()) {
                    selectedCategory.tasks.forEach { (taskName, _) ->
                        taskList.add(taskName)
                    }
                }

                // Create an adapter for the task Spinner
                val taskAdapter = ArrayAdapter(this@Timer, android.R.layout.simple_spinner_item, taskList)
                taskAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spnTask.adapter = taskAdapter

                // Enable task spinner when a category is selected
                spnTask.isEnabled = true

                // Set the visibility of btnSave based on whether taskList is empty or not
                btnSave.isEnabled = taskList.isNotEmpty()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                spnTask.isEnabled = false
                spnTask.adapter = ArrayAdapter<String>(this@Timer, android.R.layout.simple_spinner_item, emptyList())

                // If nothing is selected, disable btnSave
                btnSave.isEnabled = false
            }
        }

        btnSave.setOnClickListener {
            if (!isTimerRunning) {
                val selectedTaskName = spnTask.selectedItem.toString()

                // Get the selected category name
                val selectedCategoryName = spnCat.selectedItem.toString()

                // Get the selected category from the user's hashmap
                val selectedCategory = SessionUser.currentUser?.categories?.get(selectedCategoryName)

                // If selectedCategory is not null and it contains the selected task
                if (selectedCategory != null && selectedCategory.tasks.containsKey(selectedTaskName)) {
                    val selectedTask = selectedCategory.tasks[selectedTaskName]

                    // Your code to work with the selected task goes here
                    if (selectedTask != null) {
                        val timed = edtTime.text.toString()
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
                        ) // Assuming Time is a custom class representing time

                        val currentDate = Date()
                        val rec = Recording(currentDate, 0.0, 0.0, time, null)
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
                    Toast.makeText(this, "Selected task or category is null", Toast.LENGTH_SHORT).show()
                }
            }
        }



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


        }

        btnStop.setOnClickListener {
            if (secondsElapsed != 0)
            {
                stopTimer()

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