package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.time.LocalTime

class create_category : AppCompatActivity() {
    private val vali = validation()

    @SuppressLint("MissingInflatedId")
    private var mDefaultColour = 0
    private lateinit var ivColourPicker: ImageView
    private lateinit var ivColourPreview: View
    private var catIcon = 0
    private lateinit var catMinHours: EditText
    private lateinit var catMaxHours: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_category)

        // Initialize views after setContentView()
        ivColourPicker = findViewById(R.id.iv_Pick_Colour)
        ivColourPreview = findViewById(R.id.iv_colourPreview)
        catMinHours = findViewById(R.id.ett_Min_Goal)
        catMaxHours = findViewById(R.id.ett_Max_Goal)

        val AddCategory = findViewById<TextView>(R.id.tvAddTask)
        val iconPicker: ImageButton = findViewById(R.id.ib_Icon)
        AddCategory.setOnClickListener {
            val catName: TextView = findViewById(R.id.edtName)
            val catColor = mDefaultColour

            val cateName = catName.text.toString()
            val minHours = catMinHours.text.toString()
            val maxHours = catMaxHours.text.toString()

            if (validateGoals(minHours, maxHours)) {
                val catMin = vali.parseTimeToHours(LocalTime.parse(minHours + ":00"))
                val catMax = vali.parseTimeToHours(LocalTime.parse(maxHours + ":00"))

                val cate = Category(cateName, catIcon, catColor, catMin, catMax)
                SessionUser.currentUser?.categories?.set(cate.name, cate)

                val intent = Intent(this, ViewData::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid goals. Please ensure the max goal is greater than the min goal.", Toast.LENGTH_SHORT).show()
            }
        }


        catMinHours.setOnClickListener {
            showTimePicker(catMinHours)
        }

        catMaxHours.setOnClickListener {
            showTimePicker(catMaxHours)
        }

        setupNavigationBar()
        setupColorPicker()
    }

    private fun setupNavigationBar() {
        val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
        val ProfileOpenActivity = findViewById<ImageButton>(R.id.ib_Profile)
        val CalendarOpenActivity = findViewById<ImageButton>(R.id.ib_Calendar)
        val TimerOpenActivity = findViewById<ImageButton>(R.id.ib_Timer)

        HomeOpenActivity.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        ProfileOpenActivity.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        TimerOpenActivity.setOnClickListener {
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupColorPicker() {
        ivColourPicker.setOnClickListener {
            openColorPickerDialogue()
        }
    }

    private fun showTimePicker(editText: EditText) {
        val calendar = java.util.Calendar.getInstance()
        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = calendar.get(java.util.Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                val selectedTime = LocalTime.of(selectedHour, selectedMinute)
                editText.text = Editable.Factory.getInstance().newEditable(selectedTime.toString())
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    private fun validateGoals(minHours: String, maxHours: String): Boolean {
        return try {
            val minTime = LocalTime.parse(minHours + ":00")
            val maxTime = LocalTime.parse(maxHours + ":00")
            minTime.isBefore(maxTime)
        } catch (e: Exception) {
            false
        }
    }

    fun openColorPickerDialogue() {
        val colorPickerDialogue = AmbilWarnaDialog(this, mDefaultColour, object :
            AmbilWarnaDialog.OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {}
            override fun onOk(dialog: AmbilWarnaDialog?, colour: Int) {
                mDefaultColour = colour
                ivColourPreview.setBackgroundColor((mDefaultColour))
            }
        })
        colorPickerDialogue.show()
    }

}
