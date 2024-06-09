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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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

        val database: FirebaseDatabase =
            FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

        val DbRef = database.getReference("user")


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

            btnSelect.setOnClickListener {
                val currentUser = SessionUser.currentUser
                if (currentUser == null) {
                    Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (startDate.text.isBlank() || endDate.text.isBlank()) {
                    Toast.makeText(
                        this,
                        "Please select both start date and end date",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                // Clear existing views
                layout.removeAllViews()

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val start: Date = dateFormat.parse(startDate.text.toString())
                val end: Date = dateFormat.parse(endDate.text.toString())

                // Database query to fetch tasks within the selected date range
                val currentUserRef = DbRef.child(currentUser.username)
                currentUserRef.child("tasks").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        // Iterate through tasks
                        for (taskSnapshot in snapshot.children) {
                            val task = taskSnapshot.getValue(Task::class.java)
                            task?.taskRecords?.forEach { recording ->
                                // Parse the recording date string back into a Date object
                                val recordingDate = dateFormat.parse(recording.RecDate)

                                // Check if the recording date is within the selected date range
                                if (recordingDate != null && recordingDate >= start && recordingDate <= end) {
                                    // Inflate the layout task_listing.xml for each recording
                                    val inflatedView =
                                        LayoutInflater.from(this@ViewTimeSheetEntry_activity)
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

                                    val recordingDateTextView =
                                        TextView(this@ViewTimeSheetEntry_activity).apply {
                                            text = recording.RecDate // Use the original date string
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

                    override fun onCancelled(error: DatabaseError) {
                        // Handle error
                    }
                })
            }
        }
    }
}
