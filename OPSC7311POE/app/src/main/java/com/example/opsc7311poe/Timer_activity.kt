package com.example.opsc7311poe

import Recording
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextClock
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.sql.Time
import java.util.*

class Timer_activity : AppCompatActivity() {
    private lateinit var edtTime: TextClock
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var btnReset: Button
    private var isTimerRunning = false
    private var secondsElapsed = 0
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)

        edtTime = findViewById(R.id.edtClock)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        btnReset = findViewById(R.id.btnReset)
        edtTime.setText("00:00:00")

        val btnSave: Button = findViewById(R.id.saveTimebtn)
        val spinCat: Spinner = findViewById(R.id.spinCat)
        val spinTask: Spinner = findViewById(R.id.spinTask)

        var startTime = Time(0, 0, 0)
        var endTime = Time(0, 0, 0)

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.selectedItemId = R.id.timer
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

        // Initialize Firebase database reference
        val database = FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")
        val currentUserRef = database.getReference("user").child(SessionUser.currentUser?.username ?: "")

        // Set up category spinner
        currentUserRef.child("categories").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val categoryList = mutableListOf<String>()
                for (categorySnapshot in dataSnapshot.children) {
                    val categoryName = categorySnapshot.key
                    categoryName?.let { categoryList.add(it) }
                }

                if (categoryList.isEmpty()) {
                    spinCat.isEnabled = false
                    spinCat.adapter = ArrayAdapter(this@Timer_activity, android.R.layout.simple_spinner_item, listOf("No categories"))
                    spinTask.isEnabled = false
                    spinTask.adapter = ArrayAdapter(this@Timer_activity, android.R.layout.simple_spinner_item, listOf("No tasks"))
                    btnSave.isEnabled = false
                } else {
                    spinCat.adapter = ArrayAdapter(this@Timer_activity, android.R.layout.simple_spinner_item, categoryList)
                    spinCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                            val selectedCategoryName = categoryList[position]
                            currentUserRef.child("categories").child(selectedCategoryName).child("tasks").addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(tasksSnapshot: DataSnapshot) {
                                    val taskList = mutableListOf<String>()
                                    for (taskSnapshot in tasksSnapshot.children) {
                                        val taskName = taskSnapshot.key
                                        taskName?.let { taskList.add(it) }
                                    }

                                    spinTask.adapter = ArrayAdapter(this@Timer_activity, android.R.layout.simple_spinner_item, taskList)
                                    spinTask.isEnabled = taskList.isNotEmpty()
                                    btnSave.isEnabled = taskList.isNotEmpty()
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // Handle error
                                }
                            })
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                            spinTask.isEnabled = false
                            spinTask.adapter = ArrayAdapter<String>(this@Timer_activity, android.R.layout.simple_spinner_item, emptyList())
                            btnSave.isEnabled = false
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
            }
        })

        btnSave.setOnClickListener {
            if (!isTimerRunning) {
                val selectedTaskName = spinTask.selectedItem.toString()
                val selectedCategoryName = spinCat.selectedItem.toString()
11
                currentUserRef.child("categories").child(selectedCategoryName).child("tasks").child(selectedTaskName).child("recordings").addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // Your code to work with the selected task goes here
                        val currentTimeMillis = System.currentTimeMillis()
                        endTime = Time(currentTimeMillis)
                        val currentDate = Date()

                        val durationInMillis = endTime.time - startTime.time
                        val seconds = durationInMillis / 1000
                        val hours = seconds / 3600
                        val minutes = (seconds % 3600) / 60
                        val remainingSeconds = seconds % 60
                        val duration = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)

                        val rec = Recording(currentDate.toString(), startTime.toString(), endTime.toString(), duration, null)
                        val newRecordingRef = dataSnapshot.ref.child(currentDate.toString())
                        newRecordingRef.setValue(rec).addOnSuccessListener {
                            Toast.makeText(this@Timer_activity, "Recording saved successfully", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this@Timer_activity, "Failed to save recording", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        // Handle error
                    }
                })
            }
        }

        btnStart.setOnClickListener {
            if (!isTimerRunning) {
                val currentTimeMillis = System.currentTimeMillis()
                startTime = Time(currentTimeMillis)
                startTimer()
            } else {
                stopTimer()
                val currentTimeMillis = System.currentTimeMillis()
                endTime = Time(currentTimeMillis)
            }
        }

        btnReset.setOnClickListener {
            secondsElapsed = 0
        }

        btnStop.setOnClickListener {
            if (secondsElapsed != 0) {
                stopTimer()
                val currentTimeMillis = System.currentTimeMillis()
                endTime = Time(currentTimeMillis)
            }
        }
    }

    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            secondsElapsed++
            val hours = secondsElapsed / 3600
            val minutes = (secondsElapsed % 3600) / 60
            val seconds = secondsElapsed % 60
            val timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            edtTime.setText(timeString)
            handler.postDelayed(this, 1000)
        }
    }

    private fun startTimer() {
        isTimerRunning = true
        btnStart.text = "Pause"
        handler.post(runnable)
    }

    private fun stopTimer() {
        isTimerRunning = false
        btnStart.text = "Start"
        handler.removeCallbacks(runnable)
    }
}
