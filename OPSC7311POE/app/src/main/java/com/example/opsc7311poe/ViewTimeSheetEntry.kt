package com.example.opsc7311poe
import android.app.DatePickerDialog
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
import java.util.*

class ViewTimeSheetEntry : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_time_sheet_entry)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
                    // Set selected date to startDate TextView
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
                    // Set selected date to endDate TextView
                    endDate.text = "$dayOfMonth/${monthOfYear + 1}/$year"
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
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
                // Iterate through each category of the current user
                currentUser.categories.values.forEach { category ->
                    // Iterate through each task in the category
                    category.tasks.values.forEach { task ->
                        // Inflate the layout task_listing.xml for each task and add it to the LinearLayout
                        val inflatedView = LayoutInflater.from(this).inflate(R.layout.task_listing, layout, false)
                        val taskNameTextView = inflatedView.findViewById<TextView>(R.id.tvTask_name)
                        val taskStart = inflatedView.findViewById<TextView>(R.id.tvTask_start_time)
                        val taskEnd = inflatedView.findViewById<TextView>(R.id.tvTask_end_time)
                        val taskDesc = inflatedView.findViewById<TextView>(R.id.tvTask_description)
                        taskNameTextView.text = task.name // Set the text to the task name dynamically
                        taskDesc.text = task.description
                        taskStart.text = task.startTime.toString()
                        taskEnd.text = task.endTime.toString()
                        layout.addView(inflatedView)
                    }
                }
            }
        }
    }
}
