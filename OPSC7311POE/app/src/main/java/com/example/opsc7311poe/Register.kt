package com.example.opsc7311poe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val signUpButton: Button = findViewById(R.id.btnSignUp)
        val loginButton: Button = findViewById(R.id.btnLogin)

        signUpButton.setOnClickListener()
        {
            val username: EditText = findViewById<EditText?>(R.id.txtUserName)
            val fullName: EditText = findViewById(R.id.txtFullName)
            val email: EditText = findViewById(R.id.txtEmail)
            val password: EditText = findViewById(R.id.txtPassword)

            val Validate = validation();
            var valid = true;


            if (Validate.checkStringNullOrEmpty(username.text.toString()))
            {
                username.setText("please actually enter something")
                username.setTextColor(Color.RED)
                valid = false
            }

            if (Validate.checkStringNullOrEmpty(fullName.text.toString()))
            {
                fullName.setText("please actually enter something")
                fullName.setTextColor(Color.RED)
                valid = false
            }

            if (Validate.checkStringNullOrEmpty(email.text.toString()))
            {
                email.setText("please actually enter something")
                email.setTextColor(Color.RED)
                valid = false
            }

            if (Validate.checkStringNullOrEmpty(password.text.toString()))
            {
                password.setText("please actually enter something")
                password.setTextColor(Color.RED)
                valid = false
            }

            if (Validate.checkExistingUserEmail(email.text.toString()))
            {
                email.setText("Account already exists")
                email.setTextColor(Color.RED)
                valid = false;

            }

            if (Validate.checkExistingUserUserName(username.text.toString()))
            {
                username.setText("User Name taken")
                username.setTextColor(Color.RED)
                valid = false;

            }

            if (valid)
            {
                val user = User(username.text.toString(), fullName.text.toString(),
                    password.text.toString(), email.text.toString())

                UserList.users.add(user);

                val message = "User signed up: ${user.username}"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }

        }

        loginButton.setOnClickListener {
            // Create an Intent to navigate to ActivityLogin
            val intent = Intent(this, Login ::class.java)
            startActivity(intent)
        }
    }
}
