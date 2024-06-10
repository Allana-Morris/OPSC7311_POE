package com.example.opsc7311poe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.opsc7311poe.R.layout
import com.example.opsc7311poe.databinding.ActivityMonthlySummaryBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MonthlySummary : AppCompatActivity() {

    val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

    val DbRef = database.getReference("user")
    val currentUserRef = database.getReference("user").child(SessionUser.currentUser!!.username)


    var dataSet = LineGraphSeries<DataPoint>()

    var average = 0.0;
    var min = 0.0
    var max = 0.0



    lateinit var lineGraphView: GraphView
    private var _binding: ActivityMonthlySummaryBinding? = null
    private val binding get() = _binding!!
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_monthly_summary)
        _binding = ActivityMonthlySummaryBinding.inflate(layoutInflater)
        binding.apply {
        }

        val spinCat = this.findViewById<Spinner>(R.id.catSpinSum)
        val select = this.findViewById<Button>(R.id.btnSummary)

        lineGraphView = this.findViewById<GraphView>(R.id.idGraphView)

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
                            spinCat.adapter = ArrayAdapter<String>(
                                this@MonthlySummary,
                                android.R.layout.simple_spinner_item,
                                listOf("No categories")
                            )
                        } else {
                            // Create an adapter for the category Spinner
                            val categoryAdapter = ArrayAdapter(
                                this@MonthlySummary,
                                android.R.layout.simple_spinner_item,
                                categoryList
                            )
                            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                            spinCat.adapter = categoryAdapter
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

        select.setOnClickListener()
        {
            dataSet = LineGraphSeries<DataPoint>()

            var start = Calendar.getInstance()
            start.time = parseDate(start.time.toString())

            var thirtyDaysAgo = Calendar.getInstance()
            thirtyDaysAgo.add(Calendar.DAY_OF_YEAR, -30)

            thirtyDaysAgo.time = parseDate(thirtyDaysAgo.time.toString())

            var cat = spinCat.selectedItem.toString()

            var i = 0;
            while (thirtyDaysAgo.time != start.time) {
                getTasksBetweenDates(start.time, thirtyDaysAgo.time, i.toDouble(), cat)

                thirtyDaysAgo.add(Calendar.DATE, 1)

                i++
            }

            average = average/30


            val UserMin: TextView = findViewById(R.id.tvUserMin)
            val UserMax: TextView = findViewById(R.id.tvUserMax)
            val UserAve: TextView = findViewById(R.id.tvUserAve)

            UserMin.text = min.toString()
            UserMax.text = max.toString()
            UserAve.text = average.toString()

            // on below line adding animation
            lineGraphView.animate()

            // on below line we are setting scrollable
            // for point graph view
            lineGraphView.viewport.isScrollable = true

            // on below line we are setting scalable.
            lineGraphView.viewport.isScalable = true

            // on below line we are setting scalable y
            lineGraphView.viewport.setScalableY(true)

            // on below line we are setting scrollable y
            lineGraphView.viewport.setScrollableY(true)

            // on below line we are setting color for series.


            // on below line we are adding
            // data series to our graph view.
            lineGraphView.addSeries(dataSet)

            //Variable for each Button on Navbar™
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

        }

    }
    private fun getTasksBetweenDates(
        startDate: Date?,
        endDate: Date?,
        i: Double,
        cat :String,
    ){
        var totalHours = 0.0

        currentUserRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                // Counter to keep track of the number of tasks processed

                for (categorySnapshot in snapshot.child("categories").children) {
                    if ( categorySnapshot.key == cat )
                    {
                        min = categorySnapshot.child("minHours").getValue(Double::class.java)!!
                        max = categorySnapshot.child("maxHours").getValue(Double::class.java)!!


                        for (taskSnapshot in categorySnapshot.child("tasks").children) {
                            for (recordingSnapshot in taskSnapshot.child("recordings").children) {
                                val recordingDate =
                                    recordingSnapshot.child("recDate").getValue(String::class.java)
                                val duration =
                                    recordingSnapshot.child("duration").getValue(String::class.java)

                                val parsed = parseDate(recordingDate)

                                if (recordingDate != null && duration != null && parsed != null) {
                                    if (parsed >= endDate && parsed <= startDate) {
                                        totalHours += parseDurationToHours(duration)

                                    }
                                }
                            }
                        }
                    }
                }

                add(i, totalHours)
                getAverage(totalHours)


            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

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

    private fun add(i: Double, entryCheck: Double)
    {
        dataSet.appendData(DataPoint((i.toDouble()), entryCheck), true, 100000)
    }
    private fun parseDurationToHours(duration: String): Double {
        val parts = duration.split(":").map { it.toInt() }
        val hours = parts[0]
        val minutes = parts[1]
        val seconds = parts[2]
        return hours + minutes / 60.0 + seconds / 3600.0
    }

    private fun getAverage(totalHours: Double)
    {
        average += totalHours
    }

}