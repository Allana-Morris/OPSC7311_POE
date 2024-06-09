package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Profile_activity : AppCompatActivity() {
    lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        bottomNav = findViewById(R.id.bottomNav) as BottomNavigationView
        bottomNav.selectedItemId = R.id.profile
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

        nameEdit.hint = SessionUser.currentUser?.fullName
        emailEdit.hint = SessionUser.currentUser?.email
        userNameEdit.hint = SessionUser.currentUser?.username
        PassEdit.hint = SessionUser.currentUser?.password
    }
}