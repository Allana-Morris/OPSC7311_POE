package com.example.opsc7311poe

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewGraphTotalHours : AppCompatActivity() {

    private lateinit var editTextStartDate: EditText
    private lateinit var editTextEndDate: EditText
    private lateinit var buttonSelect: Button
    private lateinit var lineChart: GraphView
    private var startDate: Date? = null
    private var endDate: Date? = null
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    lateinit var bottomNav: BottomNavigationView
    var dataSet = LineGraphSeries<DataPoint>()

    private lateinit var database: FirebaseDatabase
    private lateinit var currentUserRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_graph_total_hours) // Make sure this matches your layout file

        database =
            FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")
        currentUserRef = database.getReference("user").child(SessionUser.currentUser!!.username)

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



        editTextStartDate = findViewById(R.id.editTextDate)
        editTextEndDate = findViewById(R.id.editTextDate2)
        buttonSelect = findViewById(R.id.button)
        lineChart = findViewById(R.id.graf)

        editTextStartDate.setOnClickListener {
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
                    editTextStartDate.setText(formattedDate)
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }

        editTextEndDate.setOnClickListener {
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
                    editTextEndDate.setText(formattedDate)
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }

        buttonSelect.setOnClickListener {
            startDate = editTextStartDate.text.toString().let { parseDate(it) }
            endDate = editTextEndDate.text.toString().let { parseDate(it) }
            if (startDate != null && endDate != null) {

                updateChart()
            }
        }
    }
    private fun updateChart() {


        var calStart = Calendar.getInstance()
        calStart.time = startDate

        var calEnd = Calendar.getInstance()
        calEnd.time = endDate


var i = 0;
        var entryCheck : Double = 0.0
        while (calStart.time != calEnd.time)
        {
            getTasksBetweenDates(calStart.time, calEnd.time, i.toDouble())
             //   Toast.makeText(this,"first " + entryCheck.toString() , Toast.LENGTH_SHORT).show()

                calStart.add(Calendar.DATE, 1)
                i++
        }

        lineChart.animate()

        // on below line we are setting scrollable
        // for point graph view
        lineChart.viewport.isScrollable = true

        // on below line we are setting scalable.
        lineChart.viewport.isScalable = true

        // on below line we are setting scalable y
        lineChart.viewport.setScalableY(true)

        // on below line we are setting scrollable y
        lineChart.viewport.setScrollableY(true)

        // on below line we are setting color for series.

        // on below line we are adding
        // data series to our graph view.
        lineChart.addSeries(dataSet)
    }

    private fun getTasksBetweenDates(
        startDate: Date?,
        endDate: Date?,
        i: Double
    ){
        var totalHours = 0.0

        currentUserRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                // Counter to keep track of the number of tasks processed

                for (categorySnapshot in snapshot.child("categories").children) {
                    for (taskSnapshot in categorySnapshot.child("tasks").children) {
                        for (recordingSnapshot in taskSnapshot.child("recordings").children) {
                            val recordingDate = recordingSnapshot.child("recDate").getValue(String::class.java)
                            val duration = recordingSnapshot.child("duration").getValue(String::class.java)

                            val parsed = parseDate(recordingDate)

                            if (recordingDate != null && duration != null && parsed != null) {
                                if (parsed >= startDate && parsed <= endDate) {
                                    totalHours += parseDurationToHours(duration)

                                }
                            }
                        }
                    }
                }

                add(i, totalHours)


            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

    }

    private fun parseDurationToHours(duration: String): Double {
        val parts = duration.split(":").map { it.toInt() }
        val hours = parts[0]
        val minutes = parts[1]
        val seconds = parts[2]
        return hours + minutes / 60.0 + seconds / 3600.0
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
        dataSet.appendData(DataPoint((i), entryCheck), true, 100000)
    }


}
