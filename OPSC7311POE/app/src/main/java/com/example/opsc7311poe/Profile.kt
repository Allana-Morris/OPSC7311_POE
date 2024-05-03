package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
    // private val navBar = Navbar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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

        //Initialisation of Inputs
        val nameEdit: TextView = findViewById(R.id.edtFullNameEdit)
        val emailEdit: TextView = findViewById(R.id.edtEmailEdit)
        val userNameEdit: TextView = findViewById(R.id.edtUserNameEdit)
        val PassEdit: TextView = findViewById(R.id.edtPasswordEdit)

        val saveBut: Button = findViewById(R.id.btnSaveProfile)

        setContent()

        //onClickListener for Save Button
        saveBut.setOnClickListener {
            val changedFullname = nameEdit.text.toString()
            val changedEmail = emailEdit.text.toString()
            val changedUserName = userNameEdit.text.toString()
            val changedPassword = PassEdit.text.toString()

            if (changedFullname.isEmpty() || changedEmail.isEmpty() || changedUserName.isEmpty() || changedPassword.isEmpty()) {
                // Show a Toast message indicating that all fields are required
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Iterate through the UserList to find the user with the matching username
            UserList.users.forEach { user ->
                if (user.username == SessionUser.currentUser?.username) {
                    // Update the user object with the changed details
                    user.apply {
                        username = changedFullname
                        email = changedEmail
                        // Assuming you want to update the username field
                        username = changedUserName
                        password = changedPassword
                    }
                    SessionUser.currentUser = user
                    // Exit the loop once the user is updated
                    return@setOnClickListener
                }
            }
            setContent()
        }


    }

    //Method to update Users data using captured inputs
    fun setContent() {
        val nameEdit: TextView = findViewById(R.id.edtFullNameEdit)
        val emailEdit: TextView = findViewById(R.id.edtEmailEdit)
        val userNameEdit: TextView = findViewById(R.id.edtUserNameEdit)
        val PassEdit: TextView = findViewById(R.id.edtPasswordEdit)

        nameEdit.setText(SessionUser.currentUser?.fullName)
        emailEdit.setText(SessionUser.currentUser?.email)
        userNameEdit.setText(SessionUser.currentUser?.username)
        PassEdit.setText(SessionUser.currentUser?.password)
    }
}