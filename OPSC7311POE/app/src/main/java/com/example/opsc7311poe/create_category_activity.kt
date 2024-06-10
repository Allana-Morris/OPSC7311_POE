package com.example.opsc7311poe
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import yuku.ambilwarna.AmbilWarnaDialog
import java.time.LocalTime

class create_category_activity : AppCompatActivity(), IconPickerDialogFragment.OnIconSelectedListener {
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")
    private val DbRef = database.getReference("user")
    private val vali = validation()
    private var mDefaultColour = 0
    private var catIcon = 0
    private lateinit var ivColourPicker: ImageView
    private lateinit var ivColourPreview: View
    private lateinit var backbutton: ImageView
    private lateinit var catMinHours: EditText
    private lateinit var catMaxHours: EditText
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_category)

        bottomNav = findViewById(R.id.bottomNav)
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

        // Back button
        backbutton = findViewById(R.id.iv_Back)
        backbutton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
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
            val cateName = catName.text.toString()
            val catColor = mDefaultColour
            val minHours = catMinHours.text.toString()
            val maxHours = catMaxHours.text.toString()

            if (vali.validateGoals(minHours, maxHours)) {
                val catMin = vali.parseTimeToHours(LocalTime.parse("$minHours:00"))
                val catMax = vali.parseTimeToHours(LocalTime.parse("$maxHours:00"))

                vali.isCategoryNameExists(cateName) { exists ->
                    if (exists) {
                        Toast.makeText(this, "Category Already Exists", Toast.LENGTH_SHORT).show()
                    } else {
                        val cate = Category(cateName, catIcon, catColor, catMin, catMax)

                        // Check the current user's username
                        val currentUsername = SessionUser.currentUser?.username

                        // Make sure the username is correctly retrieved
                        if (!vali.checkStringNullOrEmpty(currentUsername)) {
                            // Access user's categories reference using username
                            val userRef = DbRef.child(currentUsername!!).child("categories")

                            // Add category data under categories node with category name as key
                            userRef.child(cateName).setValue(cate).addOnSuccessListener {
                                // Add an empty tasks node under the category
                                userRef.child(cateName).child("tasks").setValue("").addOnSuccessListener {
                                    Toast.makeText(
                                        this,
                                        "Category and tasks node added successfully!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent = Intent(this, ViewTasks_activity::class.java)
                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed to add tasks node!", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            }.addOnFailureListener {
                                Toast.makeText(this, "Failed to add category!", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Invalid username. Cannot add category.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Invalid goals. Please ensure the max goal is greater than the min goal.", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupColorPicker()

        iconPicker.setOnClickListener {
            val icons = listOf(
                R.drawable.biphobia,
                R.drawable.bed,
                R.drawable.drink,
                R.drawable.laptop,
                R.drawable.gaming,
                R.drawable.gym,
                R.drawable.outdoors,
                R.drawable.school,
                R.drawable.shoppingcart,
                R.drawable.virtualdating,
                R.drawable.work,
                R.drawable.food
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



    private fun openColorPickerDialogue() {
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
