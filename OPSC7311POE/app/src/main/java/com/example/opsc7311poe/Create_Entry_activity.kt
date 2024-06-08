package com.example.opsc7311poe


import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Create_Entry_activity : AppCompatActivity() {

    val categoryList = mutableListOf<String>()
    val taskList = mutableListOf<String>()
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_time_sheet)

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

        val spinCat: Spinner = findViewById(R.id.spinEntryCat)
        val spinTask: Spinner = findViewById(R.id.spinEntryTask)
        val btnSave: Button = findViewById(R.id.btnSaveEntry)
        val tvDate: TextView = findViewById(R.id.tvEntryDate)
        val tvStart: TextView = findViewById(R.id.tvStartTime)
        val tvEnd: TextView = findViewById(R.id.tvEndTime)
        val btnPhoto: TextView = findViewById(R.id.tvUpPhoto)
        val uploaded: ImageView = findViewById(R.id.img_photoUp)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap?
                uploaded.setImageBitmap(imageBitmap)
            } else {
                Toast.makeText(this@Create_Entry_activity, "Failed to capture image", Toast.LENGTH_SHORT).show()
            }
        }

        // Retrieve user's categories and populate the category list
        SessionUser.currentUser?.categories?.forEach { (name, _) ->
            categoryList.add(name)
        }

        // Set up category spinner
        if (categoryList.isEmpty()) {
            spinCat.isEnabled = false
            spinCat.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("No categories"))
            spinTask.isEnabled = false
            spinTask.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listOf("No tasks"))
            btnSave.isEnabled = false
        } else {
            spinCat.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
            spinCat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedCategoryName = categoryList[position]
                    val tasks = SessionUser.currentUser?.categories?.get(selectedCategoryName)?.tasks?.keys?.toList() ?: emptyList()

                    spinTask.adapter = ArrayAdapter(this@Create_Entry_activity, android.R.layout.simple_spinner_item, tasks)
                    spinTask.isEnabled = tasks.isNotEmpty()
                    btnSave.isEnabled = tasks.isNotEmpty()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    spinTask.isEnabled = false
                    spinTask.adapter = ArrayAdapter<String>(this@Create_Entry_activity, android.R.layout.simple_spinner_item, emptyList())
                    btnSave.isEnabled = false
                }
            }
        }

        tvDate.setOnClickListener {
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
                    tvDate.text = formattedDate
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }



        tvStart.setOnClickListener()
        {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val selectedTime = LocalTime.of(selectedHour, selectedMinute)
                    tvStart.text = selectedTime.toString()
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()

        }

        btnPhoto.setOnClickListener {
            selectImageFromCamera()
        }


        tvEnd.setOnClickListener()
        {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val selectedTime = LocalTime.of(selectedHour, selectedMinute)
                    tvEnd.text = selectedTime.toString()
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()

        }

        btnSave.setOnClickListener {
            val selectedTaskName = spinTask.selectedItem.toString()
            val selectedCategoryName = spinCat.selectedItem.toString()
            val selectedCategory = SessionUser.currentUser?.categories?.get(selectedCategoryName)

            if (selectedCategory != null && selectedCategory.tasks.containsKey(selectedTaskName)) {
                val selectedTask = selectedCategory.tasks[selectedTaskName]

                if (selectedTask != null) {
                    //val dateFormat: SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val selectedDate: Date = formatter.parse(tvDate.text.toString())// Parse the date string
                    val startTime = Time.valueOf(tvStart.text.toString() + ":00") // Parse the start time string
                    val endTime = Time.valueOf(tvEnd.text.toString()+ ":00") // Parse the end time string

                    val durationInMillis = endTime.time - startTime.time

// Convert duration in milliseconds to seconds
                    val seconds = durationInMillis / 1000

// Calculate hours, minutes, and seconds
                    val hours = seconds / 3600
                    val minutes = (seconds % 3600) / 60
                    val remainingSeconds = seconds % 60

// Format duration as "HH:MM:SS"
                    val duration = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)

                    val recording = Recording(
                        RecDate = selectedDate,
                        StartTime = startTime,
                        EndTime = endTime,
                        Duration = Time.valueOf(duration.toString()).toString(),
                        image = null
                    )

                    selectedTask.taskRecords.add(recording)

                    Toast.makeText(this, "Recording saved for task: ${selectedTask.name}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Selected task is null", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Selected task or category is null", Toast.LENGTH_SHORT).show()
            }
        }



    }
    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }
    private fun selectImageFromCamera() {
        // Create an intent to capture an image
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Check if there's a camera app to handle the intent
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            // Launch the camera app
            resultLauncher.launch(takePictureIntent)
        } else {
            // If no camera app is available, display a toast or handle it in another way
            Toast.makeText(this, "No camera app available", Toast.LENGTH_SHORT).show()
        }
    }


}
