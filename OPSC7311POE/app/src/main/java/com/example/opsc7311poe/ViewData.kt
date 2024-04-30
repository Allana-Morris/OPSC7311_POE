package com.example.opsc7311poe

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

class ViewData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)

        val layout: LinearLayout = findViewById(R.id.penis)


// Adding a category
        val workCategory = Category("Work")
        SessionUser.currentUser?.categories?.put(workCategory.name, workCategory)

// Adding a task to the Work category
        val task1 = Task("eat a fork")
        workCategory.tasks[task1.name] = task1

// Retrieving tasks from the Work category
        val tasksInWorkCategory = SessionUser.currentUser?.categories?.get("Work")?.tasks

        tasksInWorkCategory?.forEach { (taskName, task) ->
            println("Task: $taskName, Description: ${task.name}")

            // Inflate the layout task_listing.xml for each task and add it to the LinearLayout
            val inflatedView = LayoutInflater.from(this).inflate(R.layout.task_listing, layout, false)
            val taskNameTextView = inflatedView.findViewById<TextView>(R.id.tvTask_name)
            taskNameTextView.text = taskName // Set the text to the task name dynamically
            layout.addView(inflatedView)
        }

        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show()
    }
}
