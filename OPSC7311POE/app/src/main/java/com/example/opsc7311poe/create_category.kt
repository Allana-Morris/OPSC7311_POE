package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.time.LocalTime
import java.util.Calendar

class create_category : AppCompatActivity() {
    private val vali = validation()

    @SuppressLint("MissingInflatedId")
    private var mDefaultColour = 0
    private var ivColourPicker: ImageView = findViewById(R.id.iv_Pick_Colour)
    private var ivColourPreview: View = findViewById(R.id.iv_colourPreview)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_category)

        val AddCategory = findViewById<TextView>(R.id.tvAddTask)

        AddCategory.setOnClickListener {
            val catName = findViewById<EditText>(R.id.edtTaskName)
            val catColor = mDefaultColour
            val catIcon = 0
            val catMinHours = findViewById<EditText>(R.id.ett_Min_Goal)
            val catMaxHours = findViewById<EditText>(R.id.ett_Max_Goal)

            var cateName = catName.text.toString()
            var minHours = catMinHours.text.toString()
            var maxHours = catMaxHours.text.toString()
            var catMin = vali.parseTimeToHours(LocalTime.parse(minHours))
            var catMax = vali.parseTimeToHours(LocalTime.parse(maxHours))

           val cate = Category(cateName, catIcon, catColor, catMin, catMax)

            val intent = Intent(this, ViewData::class.java)
            startActivity(intent)
        }
        val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
        val ProfileOpenActivity = findViewById<ImageButton>(R.id.ib_Profile)
        val CalendarOpenActivity = findViewById<ImageButton>(R.id.ib_Calendar)
        val TimerOpenActivity = findViewById<ImageButton>(R.id.ib_Timer)

        HomeOpenActivity.setOnClickListener{
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        ProfileOpenActivity.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        CalendarOpenActivity.setOnClickListener{
            val intent3 = Intent(this, Calendar::class.java)
            startActivity(intent3)
        }

        TimerOpenActivity.setOnClickListener{
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mDefaultColour = 0

        ivColourPicker.setOnClickListener {
            fun onClick(v: View?) {
                openColorPickerDialogue()
            }
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
    }

}