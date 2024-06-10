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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Register_activity : AppCompatActivity() {
    val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

    val DbRef = database.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val signUpButton: Button = findViewById(R.id.btnSignUp)
        val loginButton: Button = findViewById(R.id.btnLogin)

        signUpButton.setOnClickListener {
            // Declaration of input boxes
            val username: EditText = findViewById(R.id.txtUserName)
            val fullName: EditText = findViewById(R.id.txtFullName)
            val email: EditText = findViewById(R.id.txtEmail)
            val password: EditText = findViewById(R.id.txtPassword)

            val Validate = validation()
            var valid = true



            // Whole bunch of validation if statements

            // Username validation
            if (Validate.checkStringNullOrEmpty(username.text.toString()) || !Validate.isValidUsername(username.text.toString())) {
                username.error = "Username must be at least 5 characters"
                valid = false
            }
            //Full name doesn't need validation except for that it can't be null
            if (Validate.checkStringNullOrEmpty(fullName.text.toString())) {
                fullName.setHint("Full Name field is required")
                valid = false
            }
            //Email Validation. It must have a @ in the email
            // Email validation
            if (Validate.checkStringNullOrEmpty(email.text.toString()) || !Validate.isValidEmail(email.text.toString())) {
                email.error = "Invalid email address, email must contain an (@) sign"
                valid = false
            }

            // Password validation
            if (Validate.checkStringNullOrEmpty(password.text.toString()) || !Validate.isValidPassword(password.text.toString())) {
                password.error = "Password must be characters 8 minimum, include a number and a special character. eg.(#$%)"
                valid = false
            }

            // Nest the asynchronous calls to ensure proper order
            if (valid) {
                checkUserEmail(email.text.toString()) { emailExists ->
                    if (emailExists) {
                        email.setHint("Account already exists")
                        valid = false
                    }

                    if (valid) {
                        checkUserName(username.text.toString()) { usernameExists ->
                            if (usernameExists) {
                                username.setHint("Username is already in use, choose a different one")
                                valid = false
                            }

                            if (valid) {
                                val user = User(
                                    username.text.toString(), fullName.text.toString(),
                                    password.text.toString(), email.text.toString()
                                )

                                // Save user with username as the node ID
                                val newUserRef = DbRef.child(username.text.toString())
                                newUserRef.setValue(user).addOnSuccessListener {
                                    // User data saved successfully
                                    val categoriesRef = newUserRef.child("categories") // Create categories node
                                    categoriesRef.setValue("") // Set empty value to mark its existence

                                    UserList.users.add(user)

                                    val message = "User signed up: ${user.username}"
                                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                                    startActivity(intent)
                                }.addOnFailureListener {
                                    Toast.makeText(this, "Failed to sign up user.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }

        // Button that logs user in
        loginButton.setOnClickListener {
            // Create an Intent to navigate to ActivityLogin
            val intent = Intent(this, Login_activity::class.java)
            startActivity(intent)
        }
    }

    fun checkUserEmail(email: String, completion: (Boolean) -> Unit) {
        val emailQuery = DbRef.orderByChild("email").equalTo(email)

        // Attach a single event listener for the query
        emailQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userExists = dataSnapshot.exists() && dataSnapshot.childrenCount > 0
                completion(userExists) // Call the completion callback with the result
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
                completion(false) // Assume error means user doesn't exist
            }
        })
    }

    fun checkUserName(userName: String, completion: (Boolean) -> Unit) {
        val usernameQuery = DbRef.orderByChild("username").equalTo(userName)

        usernameQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userExists = dataSnapshot.exists() && dataSnapshot.childrenCount > 0
                completion(userExists)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
                completion(false) // Assume error means user doesn't exist
            }
        })
    }
}
