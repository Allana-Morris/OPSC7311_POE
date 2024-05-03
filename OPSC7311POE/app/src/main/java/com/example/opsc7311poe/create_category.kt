package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
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
        val iconPicker : ImageButton = findViewById(R.id.ib_Icon)
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

        //Icon Picker
        iconPicker.setOnClickListener{
            showIconPickerDialog()
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
/*
        CalendarOpenActivity.setOnClickListener{
            val intent3 = Intent(this, TaskCalendar::class.java)
            startActivity(intent3)
        }
*/
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
    //Get Icons from IconPicker Folder into Mutable List of Ints
    @SuppressLint("DiscouragedApi")
    fun getAllIcons(context: Context): List<Int>{
        val iconResources = mutableListOf<Int>()
        val packageName = context.packageName
        val resources = context.resources
        val iconDir = resources.getIdentifier("drawable/iconpick", "drawable", packageName)
        val iconNames = resources.getResourceName(iconDir).split("/")
        val iconPrefix = iconNames.first() + "/"

        val iconIds = resources.getIdentifier(iconPrefix, null, null)
        val iconFileNames = resources.getResourceEntryName(iconIds)

        val field = R.drawable::class.java.fields
        for (res in field) {
            if (res.name.startsWith(iconFileNames)){
                iconResources.add(res.getInt(null))
            }
        }
        return iconResources
    }

    fun showIconPickerDialog() {
        val icons = getAllIcons(this)
        val dialogView = layoutInflater.inflate(R.layout.icon_picker_dialog, null)
        val iconView = layoutInflater.inflate(R.layout.icon_item, null)
        val iconRecyclerView = dialogView.findViewById<RecyclerView>(R.id.iconRecyclerView)
        val imageView = iconView.findViewById<ImageView>(R.id.iconImageView)
        iconRecyclerView.layoutManager = GridLayoutManager(this, 3) // Adjust span count as needed
        iconRecyclerView.adapter = IconAdapter(icons) { selectedIconResId ->
            // Handle the selected icon here
            // For example, set it to an ImageView
            imageView.setImageResource(selectedIconResId)
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Select an Icon")
            .setPositiveButton("OK", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

}