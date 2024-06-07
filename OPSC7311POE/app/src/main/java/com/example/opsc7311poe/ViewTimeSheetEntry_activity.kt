package com.example.opsc7311poe

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*

class ViewTimeSheetEntry_activity : AppCompatActivity() {

    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_time_sheet_entry)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        for (i in 0 until bottomNav.menu.size()) {
            bottomNav.menu.getItem(i).isChecked = false
        }
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


        val layout: LinearLayout = findViewById(R.id.linLayout)

        val startDate: TextView = findViewById(R.id.tvStartDate)
        val endDate: TextView = findViewById(R.id.tvEndDate)
        val btnSelect: Button = findViewById(R.id.selectBtn)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        startDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    startDate.text = "$dayOfMonth/${monthOfYear + 1}/$year"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }

        endDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    endDate.text = "$dayOfMonth/${monthOfYear + 1}/$year"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        val currentUser = SessionUser.currentUser
        if (currentUser != null) {
            val toastMessage = StringBuilder()

            // Iterate through each category of the current user
            currentUser.categories.values.forEach { category ->
                // Iterate through each task in the category
                category.tasks.values.forEach { task ->
                    toastMessage.append("Task: ${task.name}\n")

                    // Iterate through each recording in the task
                    task.taskRecords.forEach { recording ->
                        toastMessage.append("  Recording Date: ${recording.RecDate}\n")
                        toastMessage.append("  Start Time: ${recording.StartTime}\n")
                        toastMessage.append("  End Time: ${recording.EndTime}\n")
                        toastMessage.append("  Duration: ${recording.Duration}\n")
                    }
                }
            }

            Toast.makeText(this, toastMessage.toString(), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "No current user found", Toast.LENGTH_SHORT).show()
        }

        btnSelect.setOnClickListener {

            val currentUser = SessionUser.currentUser
            if (currentUser == null) {
                // If current user is null, display an error message and return
                Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate if both start date and end date are selected
            if (startDate.text.isBlank() || endDate.text.isBlank()) {
                // If either start date or end date is not selected, display an error message and return
                Toast.makeText(this, "Please select both start date and end date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (currentUser != null) {
                layout.removeAllViews() // Clear existing views
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                val start: Date = dateFormat.parse(startDate.text.toString()) // Parse the date string
                val end: Date = dateFormat.parse(endDate.text.toString()) // Parse the date string

                // Iterate through each category of the current user
                currentUser.categories.values.forEach { category ->
                    // Iterate through each task in the category
                    category.tasks.values.forEach { task ->
                        // Iterate through each recording in the task
                        task.taskRecords.forEach { recording ->

                            // Corrected condition to check if the recording date is within the range
                            if (recording.RecDate >= start && recording.RecDate <= end) {

                                // Inflate the layout task_listing.xml for each recording and add it to the LinearLayout
                                val inflatedView = LayoutInflater.from(this)
                                    .inflate(R.layout.task_listing, layout, false)
                                val taskNameTextView =
                                    inflatedView.findViewById<TextView>(R.id.tvTask_name)
                                val taskStart =
                                    inflatedView.findViewById<TextView>(R.id.tvTask_start_time)
                                val taskEnd =
                                    inflatedView.findViewById<TextView>(R.id.tvTask_end_time)
                                val taskDesc =
                                    inflatedView.findViewById<TextView>(R.id.tvTask_description)

                                taskNameTextView.text = task.name
                                taskDesc.text = task.description
                                taskStart.text = recording.StartTime.toString()
                                taskEnd.text = recording.EndTime.toString()

                                val recordingDateTextView = TextView(this).apply {
                                    text = dateFormat.format(recording.RecDate)
                                    layoutParams = LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                    )
                                }

                                layout.addView(inflatedView)
                                layout.addView(recordingDateTextView)
                            }
                        }
                    }
                }
            }
        }
    }
}