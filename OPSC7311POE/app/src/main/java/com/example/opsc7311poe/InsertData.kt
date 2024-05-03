package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InsertData : AppCompatActivity() {


    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        //Variables for each button on Navbar™
        val HomeOpenActivity = findViewById<ImageButton>(R.id.ib_Home)
        val ProfileOpenActivity = findViewById<ImageButton>(R.id.ib_Profile)
        val CalendarOpenActivity = findViewById<ImageButton>(R.id.ib_Calendar)
        val TimerOpenActivity = findViewById<ImageButton>(R.id.ib_Timer)

        //Intent to open Home Page
        HomeOpenActivity.setOnClickListener {
            val intent2 = Intent(this, MainActivity::class.java)
            startActivity(intent2)
        }

        //Intent to open Profile
        ProfileOpenActivity.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
        /*
                //Intent to open Calendar
                CalendarOpenActivity.setOnClickListener{
                    val intent3 = Intent(this, TaskCalendar::class.java)
                    startActivity(intent3)
                }
        */
        //Intent to Open Timer
        TimerOpenActivity.setOnClickListener {
            val intent4 = Intent(this, Timer::class.java)
            startActivity(intent4)
        }

        var catSpin: Spinner = findViewById(R.id.sp_Category)
        // Create a list to hold category names
        val categoryList = mutableListOf<String>()

        // If there are no categories, disable the task spinner and display "No categories"
        if (categoryList.isEmpty()) {
            catSpin.adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                listOf("No categories")
            )
        } else {
            // Iterate through the user's categories and add their names to the list
            SessionUser.currentUser?.categories?.forEach { (name, _) ->
                categoryList.add(name)
            }

            // Create an adapter for the category Spinner
            val categoryAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryList)
            categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            catSpin.adapter = categoryAdapter

            // Set a listener for category selection to update the task Spinner
            catSpin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    // Get the selected category name
                    val selectedCategoryName = categoryList[position]

                    // Get the selected category from the user's hashmap
                    val selectedCategory =
                        SessionUser.currentUser?.categories?.get(selectedCategoryName)

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                    val TaskAdd: TextView = findViewById(R.id.tvAddTask)
                    // If nothing is selected, disable btnSave
                    TaskAdd.isEnabled = false
                    Toast.makeText(this@InsertData, "Please choose a category", Toast.LENGTH_SHORT).show()
                }
            }
        }


        val AddTaskButton: TextView = findViewById(R.id.tvAddTask)

        //onClickListener when Add Button is clicked
        AddTaskButton.setOnClickListener()
        {
            val catTask = findViewById<Spinner>(R.id.sp_Category).selectedItem.toString()
            val taskName = findViewById<TextView?>(R.id.edtTaskName).text.toString()
            val repeatSwitch: Switch = findViewById(R.id.repeatSwitch)
            val startTime = findViewById<TextView?>(R.id.edtStart).text.toString()
            val endTime = findViewById<TextView?>(R.id.edtEnd).text.toString()
            val desc = findViewById<TextView?>(R.id.edtDescription).text.toString()


            val objCategory = Category()
            var selectCat = SessionUser.currentUser?.categories?.get(catTask)

            //adding the task to the catagory for user
            var CreatedTask = Task(
                taskName,
                desc,
                repeatSwitch.isEnabled,
                startTime.toDouble(),
                endTime.toDouble()
            )
            objCategory.tasks[CreatedTask.name] = CreatedTask

            val intent = Intent(this, ViewData::class.java)
            startActivity(intent)

        }


    }
}