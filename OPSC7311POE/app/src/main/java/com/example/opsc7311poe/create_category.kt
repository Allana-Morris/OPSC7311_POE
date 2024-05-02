package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.time.LocalTime
import java.util.*


class create_category : AppCompatActivity() {
    private val vali = validation()

    @SuppressLint("MissingInflatedId")
    private var mDefaultColour = 0
    private lateinit var ivColourPicker: ImageView
    private lateinit var ivColourPreview: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_category)

        // Initialize views after setContentView()
        ivColourPicker = findViewById(R.id.iv_Pick_Colour)
        ivColourPreview = findViewById(R.id.iv_colourPreview)

        val AddCategory = findViewById<TextView>(R.id.tvAddTask)
        val catMinHours : TextView = findViewById(R.id.ett_Min_Goal)
        val catMaxHours : TextView = findViewById(R.id.ett_Max_Goal)

        AddCategory.setOnClickListener(){
            val catName : TextView = findViewById(R.id.edtName)
            val catColor = mDefaultColour
            val catIcon = 0


            var cateName = catName.text.toString()
            var minHours = catMinHours.text.toString()
            var maxHours = catMaxHours.text.toString()
            var catMin = vali.parseTimeToHours(LocalTime.parse(minHours + ":00"))
            var catMax = vali.parseTimeToHours(LocalTime.parse(maxHours+ ":00"))

            val cate = Category(cateName, catIcon, catColor, catMin, catMax)

            val intent = Intent(this, ViewData::class.java)
            startActivity(intent)
        }
        catMinHours.setOnClickListener {


            Toast.makeText(this, "it clicked", Toast.LENGTH_SHORT).show()
            val calendar = java.util.Calendar.getInstance()
            val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
            val minute = calendar.get(java.util.Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val selectedTime = LocalTime.of(selectedHour, selectedMinute)
                    catMinHours.text = selectedTime.toString()
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }
        catMaxHours.setOnClickListener {


            Toast.makeText(this, "it clicked", Toast.LENGTH_SHORT).show()
            val calendar = java.util.Calendar.getInstance()
            val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
            val minute = calendar.get(java.util.Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, selectedHour, selectedMinute ->
                    val selectedTime = LocalTime.of(selectedHour, selectedMinute)
                    catMaxHours.text = selectedTime.toString()
                },
                hour,
                minute,
                true
            )
            timePickerDialog.show()
        }
        val HomeOpenActivity = findViewById<TextView>(R.id.tv_Home)
        val ProfileOpenActivity = findViewById<TextView>(R.id.tv_Profile)
        val CalendarOpenActivity = findViewById<TextView>(R.id.tv_calendar)
        val TimerOpenActivity = findViewById<TextView>(R.id.tv_timer)

    /*    HomeOpenActivity.setOnClickListener()
        {
            navBar.OpenHomeButton()
        }

        ProfileOpenActivity.setOnClickListener()
        {
            navBar.OpenProfileButton()
        }

        CalendarOpenActivity.setOnClickListener()
        {
            navBar.OpenCalendarButton()
        }

        TimerOpenActivity.setOnClickListener()
        {
            navBar.OpenTimerButton()
        }*/

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mDefaultColour = 0

        ivColourPicker.setOnClickListener {
            openColorPickerDialogue()
        }

    }

    fun openColorPickerDialogue() {
        val colorPickerDialogue = AmbilWarnaDialog(this, mDefaultColour, object :
            OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog?) {

            }

            override fun onOk(dialog: AmbilWarnaDialog?, colour: Int) {
                mDefaultColour = colour
                ivColourPreview.setBackgroundColor((mDefaultColour))
            }
        })
        colorPickerDialogue.show()
        Toast.makeText(this, "it clicked", Toast.LENGTH_SHORT).show()

    }

}