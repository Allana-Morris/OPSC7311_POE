package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Login_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        //Initialization of sign in components
        var usernameTextView: TextView = findViewById(R.id.tv_Username)
        var passwordTextView: TextView = findViewById(R.id.tv_Password)
        val buttonOpenActivity = findViewById<TextView>(R.id.tv_Open_Register)
        val buttonLogin = findViewById<Button>(R.id.btn_Login)

        //onClickListener used to open Register Page
        buttonOpenActivity.setOnClickListener {
            val intent = Intent(this, Register_activity::class.java)
            startActivity(intent)
        }

        //onClickListener used to Login
        buttonLogin.setOnClickListener {
            //Login code - Checks and Stuffs
            val userName = usernameTextView.text.toString()
            val password = passwordTextView.text.toString()
            //Checks if Username and Password exist in Userlist and is a Valid input
            if (authenticate(userName, password)) {
                var loggedUser = getUser(userName)
                SessionUser.currentUser = loggedUser
                val intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Authenticates input using Validation class
    private fun authenticate(username: String, password: String): Boolean {
        return UserList.users.any { it.username == username && it.password == password }
    }

    //Gets User Details from User List
    private fun getUser(uName: String): User? {
        for (user in UserList.users) {
            if (user.username == uName) {
                return user
            }
        }
        return null // Return null if no user with the specified email is found
    }
}