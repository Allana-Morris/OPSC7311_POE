package com.example.opsc7311poe

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewGraphTotalHours : AppCompatActivity() {

    private lateinit var editTextStartDate: EditText
    private lateinit var editTextEndDate: EditText
    private lateinit var buttonSelect: Button
    private lateinit var barChart: BarChart
    private var startDate: Date? = null
    private var endDate: Date? = null
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_graph_total_hours) // Make sure this matches your layout file

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



        editTextStartDate = findViewById(R.id.editTextDate)
        editTextEndDate = findViewById(R.id.editTextDate2)
        buttonSelect = findViewById(R.id.button)
        barChart = findViewById(R.id.barChart)

        editTextStartDate.setOnClickListener {
            showDatePicker { date ->
                startDate = date
                editTextStartDate.setText(dateFormat.format(date))
            }
        }

        editTextEndDate.setOnClickListener {
            showDatePicker { date ->
                endDate = date
                editTextEndDate.setText(dateFormat.format(date))
            }
        }

        buttonSelect.setOnClickListener {
            if (startDate != null && endDate != null) {
                updateChart()
            }
        }
    }

    private fun showDatePicker(listener: (Date) -> Unit) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                listener(calendar.time)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateChart() {
        val tasks = getTasksBetweenDates(startDate, endDate)
        val entries = ArrayList<BarEntry>()
        var totalHours = 0.0
        for ((index, task) in tasks.withIndex()) {
            totalHours += task.taskRecords.sumOf { parseDurationToHours(it.Duration) }
            entries.add(BarEntry(index.toFloat(), totalHours.toFloat()))
        }
        val dataSet = BarDataSet(entries, "Total Hours Worked")
        val barData = BarData(dataSet)
        barChart.data = barData
        barChart.invalidate() // Refresh chart
    }

    private fun getTasksBetweenDates(startDate: Date?, endDate: Date?): List<Task> {
        val start = startDate ?: return emptyList()
        val end = endDate ?: return emptyList()

        return TaskRepository.tasks.filter { task ->
            task.taskRecords.any { record ->
                !record.RecDate.before(start) && !record.RecDate.after(end)
            }
        }
    }

    private fun parseDurationToHours(duration: String): Double {
        val parts = duration.split(":").map { it.toInt() }
        val hours = parts[0]
        val minutes = parts[1]
        val seconds = parts[2]
        return hours + minutes / 60.0 + seconds / 3600.0
    }
}
