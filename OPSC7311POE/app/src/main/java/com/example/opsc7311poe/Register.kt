package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val signUpButton: Button = findViewById(R.id.btnSignUp)
        val loginButton: Button = findViewById(R.id.btnLogin)

        signUpButton.setOnClickListener()
        {
            val username: EditText = findViewById(R.id.txtUserName)
            val fullName: EditText = findViewById(R.id.txtFullName)
            val email: EditText = findViewById(R.id.txtEmail)
            val password: EditText = findViewById(R.id.txtPassword)

            val user = User(username.text.toString(), fullName.text.toString(),
                password.text.toString(), email.text.toString());

            // val user = User("SexMaster", "Phil Collins", "12345", "hello@gmail.com");

            UserList.users.add(user);

            val message = "User signed up: ${user.username}"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        loginButton.setOnClickListener {
            // Create an Intent to navigate to ActivityLogin
            val intent = Intent(this, Login ::class.java)
            startActivity(intent)
        }
    }
}
