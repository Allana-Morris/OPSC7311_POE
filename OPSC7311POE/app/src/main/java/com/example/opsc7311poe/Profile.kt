package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {
   // private val navBar = Navbar()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
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