package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311poe.TaskRepository.tasks
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale

class TaskCalendar_activity : AppCompatActivity() {
    lateinit var set_event: TextView
    lateinit var DateToday: TextView
    lateinit var backButton: TextView
    lateinit var forwardButton: TextView
    var datenow = LocalDate.now()
    private lateinit var taskAdapter: TaskAdapter
    lateinit var bottomNav: BottomNavigationView

    lateinit var  currentUserTasksRef: DatabaseReference
    private val tasks = mutableListOf<Task>()
    private val dateFormat = SimpleDateFormat("dd MM", Locale.getDefault())
    private var currentDate = Calendar.getInstance()
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReference("categories")


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_calendar)

        // Initialize views
        set_event = findViewById<TextView>(R.id.btnNewEvent)
        DateToday = findViewById<TextView>(R.id.dayOfWeekTV)
        backButton = findViewById<TextView>(R.id.tv_Back)
        forwardButton = findViewById<TextView>(R.id.tv_Forward)
        var taskListing = findViewById<RecyclerView>(R.id.rvCalendar)
        DateToday.text = dateFormat.format(currentDate.time)

        taskListing.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter(tasks)
        taskListing.adapter = taskAdapter

        //Opens Create Task page when clicked
        set_event.setOnClickListener {
            val intent = Intent(this, InsertData_activity::class.java)
            startActivity(intent)
        }

        //Goes back a day (Date Displayed minus one)
        backButton.setOnClickListener {
            currentDate.add(Calendar.DAY_OF_YEAR, -1)
            updateDateDisplay()
        }

        //Goes forward a day (Date Displayed plus one)
        forwardButton.setOnClickListener {
            currentDate.add(Calendar.DAY_OF_YEAR, 1)
            updateDateDisplay()
        }

        bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)!!
        bottomNav.selectedItemId = R.id.calendar
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

        //For Each loop for Categories
        val database: FirebaseDatabase =
            FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

        //val DbRef = database.getReference("user")
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
                            if (it.isRepeating) {
                                tasks.add(it)
                            }
                        }
                    }
                }
                tasks.add(2, Task("Cellphone", "You used to call me on my", true, 12.0, 14.0))
                taskAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    applicationContext,
                    "Failed to retrieve tasks: ${databaseError.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
        private fun updateDateDisplay() {
            DateToday.text = dateFormat.format(currentDate.time)
        }

}
