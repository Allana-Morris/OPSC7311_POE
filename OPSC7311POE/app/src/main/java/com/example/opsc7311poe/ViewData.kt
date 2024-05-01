package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewData : AppCompatActivity() {
   // private val navBar = Navbar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_view_tasks)
     //   val HomeOpenActivity = findViewById<TextView>(R.id.tv_Home)
      //  val ProfileOpenActivity = findViewById<TextView>(R.id.tv_Profile)
     //   val CalendarOpenActivity = findViewById<TextView>(R.id.tv_calendar)
     //   val TimerOpenActivity = findViewById<TextView>(R.id.tv_timer)

       val btnCreate : FloatingActionButton = findViewById(R.id.createBtn)

       //  Set OnClickListener for the button
        btnCreate.setOnClickListener {
            val intent = Intent(this, InsertData::class.java)
            startActivity(intent)
        }



   /*     HomeOpenActivity.setOnClickListener()
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
        }*/
        //this is the fetching user task info section ---------------------------------------------

        val layout: LinearLayout = findViewById(R.id.vertLayout)
       /*

        // Adding a category catagories have a bunch of params this example is only the name
    //    val workCategory = Category("Work", )
     //   SessionUser.currentUser?.categories?.put(workCategory.name, workCategory)


// Adding a task to the Work category, same story with the params
//        val task1 = Task("eat a fork")
      //  workCategory.tasks[task1.name] = task1
*/
// Retrieving tasks from the Work category, the "Work" is just the desired catagory name
        val tasksInWorkCategory = SessionUser.currentUser?.categories?.get("Work")?.tasks

        //this is a for each loop to loop through all the retrieved tasks
        tasksInWorkCategory?.forEach { (taskName, task) ->

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