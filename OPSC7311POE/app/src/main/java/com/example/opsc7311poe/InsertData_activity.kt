package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalTime

class InsertData_activity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_task)

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
        // Spinner for Categories
        val catSpin: Spinner = findViewById(R.id.sp_Category)
        val categoryList = mutableListOf<String>()
        SessionUser.currentUser?.categories?.forEach { (name, _) ->
            categoryList.add(name)
        }

        // If there are no categories, disable the task spinner and display "No categories"
        if (categoryList.isEmpty()) {
            catSpin.adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                listOf("No categories")
            )
        } else {
            // Create an adapter for the category Spinner
            val categoryAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            catSpin.adapter = categoryAdapter
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

            if ((selectedCategory == "No categories") || selectedCategory.isEmpty() || taskName.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
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
                // Create and add the task to the selected category for the user
                val selectedCategoryObj = SessionUser.currentUser?.categories?.get(selectedCategory)
                if (selectedCategoryObj != null) {
                    val createdTask =
                        Task(taskName, desc, repeatSwitch.isChecked, startHour, endHour)
                    selectedCategoryObj.tasks[createdTask.name] = createdTask

                    // Optional: Display a toast to confirm task creation
                    Toast.makeText(
                        this,
                        "Task '$taskName' added to category '$selectedCategory'",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Start the ViewData activity or perform any other necessary action
                    val intent = Intent(this, ViewTasks_activity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Selected category not found", Toast.LENGTH_SHORT).show()
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

    fun parseTimeToHours(enteredTime: LocalTime): Double {
        val hours = enteredTime.hour
        val minutes = enteredTime.minute
        require(hours >= 0 && minutes >= 0 && minutes < 60) { "Invalid time format" }
        return hours + minutes / 60.0
    }
}
