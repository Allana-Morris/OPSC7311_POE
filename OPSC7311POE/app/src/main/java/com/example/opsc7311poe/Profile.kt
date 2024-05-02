package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
   // private val navBar = Navbar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val HomeOpenActivity = findViewById<TextView>(R.id.tv_Home)
        val ProfileOpenActivity = findViewById<TextView>(R.id.tv_Profile)
        val CalendarOpenActivity = findViewById<TextView>(R.id.tv_calendar)
        val TimerOpenActivity = findViewById<TextView>(R.id.tv_timer)

        val nameEdit : TextView = findViewById(R.id.edtFullNameEdit)
        val emailEdit : TextView = findViewById(R.id.edtEmailEdit)
        val userNameEdit : TextView = findViewById(R.id.edtUserNameEdit)
        val PassEdit : TextView = findViewById(R.id.edtPasswordEdit)

        val saveBut : Button = findViewById(R.id.btnSaveProfile)

        setContent()

        saveBut.setOnClickListener {
            val changedUsername = nameEdit.text.toString()
            val changedEmail = emailEdit.text.toString()
            val changedUserName = userNameEdit.text.toString()
            val changedPassword = PassEdit.text.toString()

            // Iterate through the UserList to find the user with the matching username
            UserList.users.forEach { user ->
                if (user.username == SessionUser.currentUser?.username) {
                    // Update the user object with the changed details
                    user.apply {
                        username = changedUsername
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


  /*      HomeOpenActivity.setOnClickListener()
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
        }

*/


    }

    fun setContent()
    {
        val nameEdit : TextView = findViewById(R.id.edtFullNameEdit)
        val emailEdit : TextView = findViewById(R.id.edtEmailEdit)
        val userNameEdit : TextView = findViewById(R.id.edtUserNameEdit)
        val PassEdit : TextView = findViewById(R.id.edtPasswordEdit)

        nameEdit.setText( SessionUser.currentUser?.fullName)
        emailEdit.setText(SessionUser.currentUser?.email)
        userNameEdit.setText(SessionUser.currentUser?.username)
        PassEdit.setText(SessionUser.currentUser?.password)
    }
}