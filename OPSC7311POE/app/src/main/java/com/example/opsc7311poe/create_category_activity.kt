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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.time.LocalTime

class create_category_activity : AppCompatActivity(), IconPickerDialogFragment.OnIconSelectedListener {
    val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

    val DbRef = database.getReference("user")

    private val vali = validation()

    @SuppressLint("MissingInflatedId")
    private var mDefaultColour = 0
    private lateinit var ivColourPicker: ImageView
    private lateinit var ivColourPreview: View
    private var catIcon = 0
    private lateinit var catMinHours: EditText
    private lateinit var catMaxHours: EditText
    lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_category)

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
                else -> false
            }
        }

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

                // Check the current user's username
                val currentUsername = SessionUser.currentUser!!.username
                // Make sure the username is correctly retrieved
                if (currentUsername != null && currentUsername.isNotEmpty()) {
                    // Access user's categories reference using username
                    val userRef = DbRef.child(currentUsername).child("categories")

                    // Add category data under categories node with category name as key
                    userRef.child(cateName).setValue(cate).addOnSuccessListener {
                        // Add an empty tasks node under the category
                        userRef.child(cateName).child("tasks").setValue("").addOnSuccessListener {
                            Toast.makeText(this, "Category and tasks node added successfully!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, ViewTasks_activity::class.java)
                            startActivity(intent)
                        }.addOnFailureListener {
                            Toast.makeText(this, "Failed to add tasks node!", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, "Failed to add category!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Invalid username. Cannot add category.", Toast.LENGTH_SHORT).show()
                }
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupColorPicker()

        iconPicker.setOnClickListener {
            val icons = listOf(
                R.drawable.bed,
                R.drawable.laptop,
                R.drawable.biphobia,
                R.drawable.gym,
                R.drawable.gaming,
                R.drawable.drink,
                R.drawable.outdoors,
                R.drawable.virtualdating,
                R.drawable.shoppingcart

                // Add your icon resource IDs here
            )
            val dialog = IconPickerDialogFragment.newInstance(icons, this)
            dialog.show(supportFragmentManager, "IconPickerDialogFragment")
        }
    }

    override fun onIconSelected(iconResId: Int) {
        catIcon = iconResId
        val iconPicker: ImageButton = findViewById(R.id.ib_Icon)
        iconPicker.setImageResource(catIcon)
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
            { _, selectedHour, selectedMinute ->
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
                ivColourPreview.setBackgroundColor(mDefaultColour)
            }
        })
        colorPickerDialogue.show()
    }
}
