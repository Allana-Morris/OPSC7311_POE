package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalTime

class InsertData_activity : AppCompatActivity() {
    val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

    val DbRef = database.getReference("user")
    lateinit var bottomNav: BottomNavigationView
    private lateinit var backbutton: ImageView
    val Vali = validation()

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_task)

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

        //back button
        backbutton = findViewById(R.id.iv_Back)
        backbutton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            true
        }

        // Spinner for Categories
        // Spinner for Categories
        val catSpin: Spinner = findViewById(R.id.sp_Category)

// Retrieve categories from the database for the current user
        val currentUser = SessionUser.currentUser
        if (currentUser != null) {
            val currentUsername = currentUser.username
            if (currentUsername != null) {
                val userCategoriesRef = DbRef.child(currentUsername).child("categories")

                // Add a ValueEventListener to fetch categories from the database
                userCategoriesRef.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        val categoryList = mutableListOf<String>()

                        for (categorySnapshot in dataSnapshot.children) {
                            val categoryName = categorySnapshot.key
                            if (categoryName != null) {
                                categoryList.add(categoryName)
                            }
                        }

                        // If there are no categories, disable the task spinner and display "No categories"
                        if (categoryList.isEmpty()) {
                            catSpin.adapter = ArrayAdapter<String>(
                                this@InsertData_activity,
                                android.R.layout.simple_spinner_item,
                                listOf("No categories")
                            )
                        } else {
                            // Create an adapter for the category Spinner
                            val categoryAdapter = ArrayAdapter(
                                this@InsertData_activity,
                                android.R.layout.simple_spinner_item,
                                categoryList
                            )
                            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            catSpin.adapter = categoryAdapter
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle errors here
                        Toast.makeText(
                            applicationContext,
                            "Failed to retrieve categories: ${databaseError.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "Invalid username. Cannot retrieve categories.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "No user logged in. Cannot retrieve categories.",
                Toast.LENGTH_SHORT
            ).show()
        }


        // Add Task Button
        val AddTaskButton: TextView = findViewById(R.id.tvAddTask)

        AddTaskButton.setOnClickListener {
            val selectedCategory = catSpin.selectedItem?.toString() ?: ""
            val taskName = findViewById<TextView>(R.id.edtTaskName).text.toString().trim()
            val repeatSwitch: Switch = findViewById(R.id.repeatSwitch)
            val startTime = findViewById<TextView>(R.id.edtStart).text.toString().trim()
            val endTime = findViewById<TextView>(R.id.edtEnd).text.toString().trim()
            val desc = findViewById<TextView>(R.id.edtDescription).text.toString().trim()

            Vali.isTaskNameExists(selectedCategory, taskName) { exists ->
                if (exists) {
                    Toast.makeText(this, "Task Already Exists", Toast.LENGTH_SHORT).show()
                } else {
                    if (selectedCategory == "No categories" || selectedCategory.isEmpty() || taskName.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                        Toast.makeText(
                            this@InsertData_activity,
                            "Please fill in all required fields",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (!isValidTime(startTime) || !isValidTime(endTime)) {
                        Toast.makeText(
                            this@InsertData_activity,
                            "Please enter valid time format (HH:mm)",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (!isEndTimeAfterStartTime(startTime, endTime)) {
                        Toast.makeText(
                            this@InsertData_activity,
                            "End time must be after start time",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        // Convert start time and end time to decimal hours
                        val startHour = parseTimeToHours(LocalTime.parse(startTime))
                        val endHour = parseTimeToHours(LocalTime.parse(endTime))

                        // Input validation passed, proceed with task creation
                        val currentUsername = SessionUser.currentUser?.username
                        if (currentUsername != null && currentUsername.isNotEmpty()) {
                            val userCategoryTasksRef = FirebaseDatabase.getInstance().reference
                                .child(currentUsername)
                                .child("categories")
                                .child(selectedCategory)
                                .child("tasks")

                            // Create a unique task ID using the task name
                            val taskId = taskName

                            // Create the Task object
                            val createdTask =
                                Task(taskId, desc, repeatSwitch.isChecked, startHour, endHour)

                            // Save the task under the category's tasks node with the task name as the ID
                            userCategoryTasksRef.child(taskId).setValue(createdTask)
                                .addOnSuccessListener {
                                    // Optional: Display a toast to confirm task creation
                                    Toast.makeText(
                                        this,
                                        "Task '$taskName' added to category '$selectedCategory'",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    // Add an empty node for recordings under the task node
                                    userCategoryTasksRef.child(taskId).child("recordings")
                                        .setValue("")

                                    // Start the ViewData activity or perform any other necessary action
                                    val intent = Intent(this, ViewTasks_activity::class.java)
                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed to add task", Toast.LENGTH_SHORT)
                                        .show()
                                }
                        } else {
                            Toast.makeText(
                                this,
                                "Invalid username. Cannot add task.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }


        }
    }
        private fun isValidTime(time: String): Boolean {
            // Regular expression to validate time format (HH:mm)
            val regex = Regex("^([01]\\d|2[0-3]):([0-5]\\d)$")
            return regex.matches(time)
        }

        private fun isEndTimeAfterStartTime(startTime: String, endTime: String): Boolean {
            val startTimeParts = startTime.split(":")
            val endTimeParts = endTime.split(":")
            val startHour = startTimeParts[0].toInt()
            val startMinute = startTimeParts[1].toInt()
            val endHour = endTimeParts[0].toInt()
            val endMinute = endTimeParts[1].toInt()
            return if (endHour == startHour) {
                endMinute > startMinute
            } else {
                endHour > startHour
            }
        }

        private fun parseTimeToHours(enteredTime: LocalTime): Double {
            val hours = enteredTime.hour
            val minutes = enteredTime.minute
            require(hours >= 0 && minutes >= 0 && minutes < 60) { "Invalid time format" }
            return hours + minutes / 60.0
        }
}