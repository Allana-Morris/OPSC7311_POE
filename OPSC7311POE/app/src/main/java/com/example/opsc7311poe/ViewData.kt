package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewData : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_view_tasks)
       //Variables for each button on Navbar™
       val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
       val ProfileOpenActivity = findViewById<ImageButton>(R.id.ib_Profile)
       val CalendarOpenActivity = findViewById<ImageButton>(R.id.ib_Calendar)
       val TimerOpenActivity = findViewById<ImageButton>(R.id.ib_Timer)

       //Intent to open Home Page
       HomeOpenActivity.setOnClickListener{
           val intent2 = Intent(this, MainActivity::class.java)
           startActivity(intent2)
       }

       //Intent to open Profile
       ProfileOpenActivity.setOnClickListener{
           val intent = Intent(this, Profile::class.java)
           startActivity(intent)
       }
/*
        //Intent to open Calendar
       CalendarOpenActivity.setOnClickListener{
           val intent3 = Intent(this, TaskCalendar::class.java)
           startActivity(intent3)
       }
*/
       //Intent to Open Timer
       TimerOpenActivity.setOnClickListener{
           val intent4 = Intent(this, Timer::class.java)
           startActivity(intent4)
       }

       //Declaration of Create Task Button
       val btnCreate : ImageButton = findViewById(R.id.createTask)

       //  Set OnClickListener for the Task Create button
        btnCreate.setOnClickListener {
            val intent = Intent(this, InsertData::class.java)
            startActivity(intent)
        }

       //Declaration of Create Category Button
       val btnCat : ImageButton = findViewById(R.id.createCat)

       // Set onClickListener for the Category Create button
       btnCat.setOnClickListener {
           val intent2 = Intent(this, create_category::class.java)
           startActivity(intent2)
       }


      //Declaration of Layout where data will be outputted into
        val layout: LinearLayout = findViewById(R.id.vertLayout)

       //For Each loop for Categories
       SessionUser.currentUser?.categories?.forEach{(categoryName, category) ->
        //this is a for each loop to loop through all the retrieved tasks
        category.tasks?.forEach { (taskName, task) ->

            //Show Message - Toast Addition
            Toast.makeText(this, "Task Name: $taskName", Toast.LENGTH_SHORT).show()

            // Inflate the layout task_listing.xml for each task and add it to the LinearLayout
            val inflatedView = LayoutInflater.from(this).inflate(R.layout.task_listing, layout, false)
            val taskNameTextView = inflatedView.findViewById<TextView>(R.id.tvTask_name)
            val taskStart = inflatedView.findViewById<TextView>(R.id.tvTask_start_time)
            val taskEnd = inflatedView.findViewById<TextView>(R.id.tvTask_end_time)
            val taskDesc = inflatedView.findViewById<TextView>(R.id.tvTask_description)
            taskNameTextView.text = task.name // Set the text to the task name dynamically
            taskDesc.text = task.description
            taskStart.text = task.startTime.toString()
            taskEnd.text = task.endTime.toString()
            layout.addView(inflatedView)
        }
       }
    }
}