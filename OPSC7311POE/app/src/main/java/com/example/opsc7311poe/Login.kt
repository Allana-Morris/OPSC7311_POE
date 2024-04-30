package com.example.opsc7311poe


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    private lateinit var usernameTextView: TextView
    private lateinit var passwordTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        usernameTextView = findViewById(R.id.tv_Username)
        usernameTextView = findViewById(R.id.tv_Password)

        val buttonOpenActivity = findViewById<TextView>(R.id.tv_Open_Register)
        buttonOpenActivity.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        val buttonLogin = findViewById<Button>(R.id.btn_Login)
        buttonLogin.setOnClickListener {
            //Login code - Checks and Stuffs
            val userName = usernameTextView.text.toString()
            val password = passwordTextView.text.toString()
            if (authenticate(userName, password)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun authenticate(username: String, password: String): Boolean {
        return UserList.users.any { it.username == username && it.password == password }
    }
}