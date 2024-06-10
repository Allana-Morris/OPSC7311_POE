package com.example.opsc7311poe

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class cat_total_activity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var currentUserRef: DatabaseReference

    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_total_cat_hours)

        database =
            FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")
        currentUserRef = database.getReference("user").child(SessionUser.currentUser!!.username)

        val start: TextView = findViewById(R.id.tvStartDate1)
        val end: TextView = findViewById(R.id.tvEndDate1)
        val select: Button = findViewById(R.id.btnSelectHours)
        val bigTextView: TextView = findViewById(R.id.tv_Total)

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

        // Date picker dialog for start date
        start.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a date picker dialog
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // Update the date TextView with the selected date
                    val calendar = Calendar.getInstance()
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    val selectedDate: Date = calendar.time
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = formatter.format(selectedDate)
                    start.text = formattedDate
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }

        // Date picker dialog for end date
        end.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            // Create a date picker dialog
            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                    // Update the date TextView with the selected date
                    val calendar = Calendar.getInstance()
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    val selectedDate: Date = calendar.time
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate = formatter.format(selectedDate)
                    end.text = formattedDate
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }

        select.setOnClickListener {
            var start = findViewById<TextView>(R.id.tvStartDate1).text.toString()
            var end = findViewById<TextView>(R.id.tvEndDate1).text.toString()

            if (start.isEmpty() || end.isEmpty()) {
                Toast.makeText(this, "Please select both start and end dates", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val startDate = dateFormat.parse(start)
            val endDate = dateFormat.parse(end)

            Toast.makeText(this, "Start date: $startDate, End date: $endDate", Toast.LENGTH_SHORT).show()

            calculateTotalHoursByCategory(startDate, endDate)
        }
    }

    private fun showDatePicker(textView: TextView) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                textView.text = "$year-${monthOfYear + 1}-$dayOfMonth"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun calculateTotalHoursByCategory(startDate: Date, endDate: Date) {
        currentUserRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val totalHoursByCategory = mutableMapOf<String, Double>()

                for (categorySnapshot in snapshot.child("categories").children) {
                    val categoryName = categorySnapshot.key
                    var totalHours = 0.0

                    for (taskSnapshot in categorySnapshot.child("tasks").children) {
                        for (recordingSnapshot in taskSnapshot.child("recordings").children) {
                            val recordingDate =
                                recordingSnapshot.child("recDate").getValue(String::class.java)
                            val duration =
                                recordingSnapshot.child("duration").getValue()


                            Toast.makeText(this@cat_total_activity,
                                "rec date" + parseDate(recordingDate), Toast.LENGTH_SHORT).show()

                            val parsed = parseDate(recordingDate)

                            if (recordingDate != null && duration != null) {
                                if (parsed != null) {
                                    if (parsed >= startDate && parsed <= endDate) {
                                        totalHours += parseDurationToHours(duration.toString())
                                    }
                                }
                            }
                        }
                    }

                    categoryName?.let {
                        totalHoursByCategory[it] = totalHours
                    }
                }

                displayTotalHoursByCategory(totalHoursByCategory)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun parseDurationToHours(duration: String): Double {
        val parts = duration.split(":")
        val hours = parts[0].toDouble()
        val minutes = parts[1].toDouble()
        val seconds = parts[2].toDouble()
        return hours + (minutes / 60) + (seconds / 3600)
    }

    // Function to parse duration string to hours

    private fun displayTotalHoursByCategory(totalHoursByCategory: Map<String, Double>) {
        val bigTextView: TextView = findViewById(R.id.tv_Total)
        var displayText = ""
        totalHoursByCategory.forEach { (categoryName, totalHours) ->
            displayText += "$categoryName: $totalHours hours\n"
        }
        bigTextView.text = displayText
    }
    fun convertDateFormat(inputDate: String): String {
        val inputDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val date = inputDateFormat.parse(inputDate)
        return outputDateFormat.format(date)
    }

    fun convertOtherDateFormat(inputDate: String): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val out = dateFormat.parse(inputDate)
        return (inputDate)
    }

    private fun parseDate(dateString: String?): Date? {
        val dateFormats = arrayOf(
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()),
            SimpleDateFormat(
                "EEE MMM dd HH:mm:ss z yyyy",
                Locale.ENGLISH
            ) // Format for "Sun Jun 09 19:59:44 GMT+02:00 2024"
        )

        for (format in dateFormats) {
            try {
                return format.parse(dateString)
            } catch (e: ParseException) {
                // Try the next format
            }
        }
        return null
    }
}

