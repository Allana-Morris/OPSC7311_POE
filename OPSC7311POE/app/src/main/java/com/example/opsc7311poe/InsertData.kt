package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InsertData : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
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

        CalendarOpenActivity.setOnClickListener{
            val intent3 = Intent(this, TaskCalendar::class.java)
            startActivity(intent3)
        }

        TimerOpenActivity.setOnClickListener{
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

        val AddTaskButton : TextView = findViewById(R.id.tvAddTask)

        AddTaskButton.setOnClickListener()
        {
            val catTask = findViewById<Spinner>(R.id.sp_Category).selectedItem.toString()
            val taskName = findViewById<TextView?>(R.id.edtTaskName).text.toString()
            val repeatSwitch : Switch = findViewById(R.id.repeatSwitch)
            val startTime = findViewById<TextView?>(R.id.edtStart).text.toString()
            val endTime = findViewById<TextView?>(R.id.edtEnd).text.toString()
            val desc = findViewById<TextView?>(R.id.edtDescription).text.toString()

            //testing catagory
            var objCategory = Category()
            var selectCat =
            //adding to the user catagory hashmap
            SessionUser.currentUser?.categories?.put(.name, workCategory)
            //adding the task to the catagory for user
            var CreatedTask = Task(taskName, desc, repeatSwitch.isEnabled, startTime.toDouble(), endTime.toDouble())
            objCategory.tasks[CreatedTask.name] = CreatedTask

            val intent = Intent(this, ViewData::class.java)
            startActivity(intent)

        }


    }
}