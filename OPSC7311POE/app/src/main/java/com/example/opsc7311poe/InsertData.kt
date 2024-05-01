package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InsertData : AppCompatActivity() {
    private val navBar = Navbar()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        val HomeOpenActivity = findViewById<TextView>(R.id.tv_Home)
        val ProfileOpenActivity = findViewById<TextView>(R.id.tv_Profile)
        val CalendarOpenActivity = findViewById<TextView>(R.id.tv_calendar)
        val TimerOpenActivity = findViewById<TextView>(R.id.tv_timer)

        val AddTaskButton : TextView = findViewById(R.id.tvAddTask)

        AddTaskButton.setOnClickListener()
        {
            val taskName = findViewById<TextView?>(R.id.edtTaskName).text.toString();
            val repeatSwitch : Switch = findViewById(R.id.repeatSwitch)
            val startTime = findViewById<TextView?>(R.id.edtStart).text.toString()
            val endTime = findViewById<TextView?>(R.id.edtEnd).text.toString()
            val desc = findViewById<TextView?>(R.id.edtDescription).text.toString()

            //testing catagory
            val workCategory = Category("Work", 1, 2.0,4.0)
            //adding to the user catagory hashmap
            SessionUser.currentUser?.categories?.put(workCategory.name, workCategory)
            //adding the task to the catagory for user
            var CreatedTask = Task(taskName, desc, repeatSwitch.isEnabled, startTime.toDouble(), endTime.toDouble())
            workCategory.tasks[CreatedTask.name] = CreatedTask;

            val intent = Intent(this, ViewData::class.java)
            startActivity(intent)

        }

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