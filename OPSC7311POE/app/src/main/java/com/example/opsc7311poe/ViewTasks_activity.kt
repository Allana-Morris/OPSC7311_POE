package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewTasks_activity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    lateinit var currentUserTasksRef: DatabaseReference


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tasks)

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        // Clear selection by setting invalid item ID
        bottomNav.menu.setGroupCheckable(0, true, false) // Enable manual selection
        bottomNav.menu.findItem(R.id.home).isChecked = false
        bottomNav.menu.findItem(R.id.profile).isChecked = false
        bottomNav.menu.findItem(R.id.calendar).isChecked = false
        bottomNav.menu.findItem(R.id.timer).isChecked = false
        bottomNav.menu.setGroupCheckable(0, true, true) // Re-enable auto selection
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

        //Declaration of Create Task Button
        val btnCreate: ImageButton = findViewById(R.id.createTask)

        //  Set OnClickListener for the Task Create button
        btnCreate.setOnClickListener {
            val intent = Intent(this, InsertData_activity::class.java)
            startActivity(intent)
        }

        //Declaration of Create Category Button
        val btnCat: ImageButton = findViewById(R.id.createCat)

        // Set onClickListener for the Category Create button
        btnCat.setOnClickListener {
            val intent2 = Intent(this, create_category_activity::class.java)
            startActivity(intent2)
        }


        //Declaration of Layout where data will be outputted into
        val layout: LinearLayout = findViewById(R.id.vertLayout)

        //For Each loop for Categories
        val database: FirebaseDatabase =
            FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

        //val DbRef = database.getReference("user")

        // Get the reference to the current user's tasks
        val currentUsername = SessionUser.currentUser?.username
        if (currentUsername != null) {
            currentUserTasksRef = database.getReference("user").child(currentUsername).child("categories")
        } else {
            Toast.makeText(this, "Current user not found", Toast.LENGTH_SHORT).show()
            return
        }

        // Add a ValueEventListener to fetch tasks from the database
        currentUserTasksRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (categorySnapshot in dataSnapshot.children) {
                    for (taskSnapshot in categorySnapshot.child("tasks").children) {
                        val task = taskSnapshot.getValue(Task::class.java)
                        task?.let {
                            // Inflate the layout task_listing.xml for each task and add it to the LinearLayout
                            val inflatedView = LayoutInflater.from(this@ViewTasks_activity)
                                .inflate(R.layout.task_listing, layout, false)
                            val taskNameTextView =
                                inflatedView.findViewById<TextView>(R.id.tvTask_name)
                            val taskStart = inflatedView.findViewById<TextView>(R.id.tvTask_start_time)
                            val taskEnd = inflatedView.findViewById<TextView>(R.id.tvTask_end_time)
                            val taskDesc = inflatedView.findViewById<TextView>(R.id.tvTask_description)
                            val imgSheet = inflatedView.findViewById<ImageView>(R.id.imgSheet)

                            taskNameTextView.text = it.name // Set the text to the task name dynamically
                            taskDesc.text = it.description
                            taskStart.text = it.startTime.toString()
                            taskEnd.text = it.endTime.toString()
                            imgSheet.visibility = ImageView.GONE

                            layout.addView(inflatedView)
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here
                Toast.makeText(
                    applicationContext,
                    "Failed to retrieve tasks: ${databaseError.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}