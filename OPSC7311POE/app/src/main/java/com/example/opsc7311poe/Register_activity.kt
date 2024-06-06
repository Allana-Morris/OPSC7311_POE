package com.example.opsc7311poe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Register : AppCompatActivity() {
    val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

    val DbRef = database.getReference("user")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val signUpButton: Button = findViewById(R.id.btnSignUp)
        val loginButton: Button = findViewById(R.id.btnLogin)

        signUpButton.setOnClickListener()
        {
            //Declaration of input boxes
            val username: EditText = findViewById<EditText?>(R.id.txtUserName)
            val fullName: EditText = findViewById(R.id.txtFullName)
            val email: EditText = findViewById(R.id.txtEmail)
            val password: EditText = findViewById(R.id.txtPassword)

            val Validate = validation()
            var valid = true

            //Whole bunch of Validation if statements
            if (Validate.checkStringNullOrEmpty(username.text.toString())) {
                username.setText("Invalid input: Input can not be blank")
                username.setTextColor(Color.RED)
                valid = false
            }

            if (Validate.checkStringNullOrEmpty(fullName.text.toString())) {
                fullName.setText("Invalid input: Input can not be blank")
                fullName.setTextColor(Color.RED)
                valid = false
            }

            if (Validate.checkStringNullOrEmpty(email.text.toString())) {
                email.setText("Invalid input: Input can not be blank")
                email.setTextColor(Color.RED)
                valid = false
            }

            if (Validate.checkStringNullOrEmpty(password.text.toString())) {
                password.setText("Invalid input: Input can not be blank")
                password.setTextColor(Color.RED)
                valid = false
            }

            /*  if (Validate.checkExistingUserEmail(email.text.toString())) {
                email.setText("Account already exists")
                email.setTextColor(Color.RED)
                valid = false

            }*/


            /*    if (Validate.checkExistingUserUserName(username.text.toString())) {
                username.setText("Username is already in use, choose a different one")
                username.setTextColor(Color.RED)
                valid = false

            }*/



            //Big boss validation if statement
            if (valid) {
                checkUserEmail(email.text.toString()) { emailExists ->
                    if (emailExists) {
                        Toast.makeText(this, emailExists.toString(), Toast.LENGTH_SHORT).show()

                        email.setError("Account already exists")
                    } else {
                        checkUserName(username.text.toString()) { usernameExists ->
                            if (usernameExists) {
                                Toast.makeText(this, emailExists.toString(), Toast.LENGTH_SHORT).show()
                                username.setError("Username is already in use, choose a different one")
                            } else {
                                val user = User(username.text.toString(), fullName.text.toString(), password.text.toString(), email.text.toString())
                                DbRef.push().setValue(user)
                                Toast.makeText(this, "User signed up: ${user.username}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

        //Button that logs user in
        loginButton.setOnClickListener {
            // Create an Intent to navigate to ActivityLogin
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun checkUserEmail(email: String, completion: (Boolean) -> Unit) {
        val emailQuery = DbRef.orderByChild("email").equalTo(email)
        emailQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                completion(dataSnapshot.exists())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                completion(false)
            }
        })
    }

    private fun checkUserName(username: String, completion: (Boolean) -> Unit) {
        val usernameQuery = DbRef.orderByChild("username").equalTo(username)
        usernameQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                completion(dataSnapshot.exists())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                completion(false)
            }
        })
    }
}