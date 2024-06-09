package com.example.opsc7311poe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login_activity : AppCompatActivity() {
    private val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

    private val DbRef = database.getReference("user")

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
            val username = usernameTextView.text.toString()
            val password = passwordTextView.text.toString()

            val valid = true

            if (valid) {

                Toast.makeText(this, "check", Toast.LENGTH_SHORT)
                    .show()
                // Firebase authentication logic
                authenticate(username, password) { authenticated ->

                    if (authenticated) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "check2", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT)
                            .show()
                        Toast.makeText(this, "check3", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Toast.makeText(this, authenticated.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    // Authenticate using Firebase

    private fun authenticate(username: String, password: String, completion: (Boolean) -> Unit) {
        val usernameQuery = DbRef.orderByChild("username").equalTo(username)
        usernameQuery.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (userSnapshot in dataSnapshot.children) {
                        val username = userSnapshot.child("username").getValue(String::class.java)!!
                        val password = userSnapshot.child("password").getValue(String::class.java)!!
                        val fullName = userSnapshot.child("fullName").getValue(String::class.java)!!
                        val email = userSnapshot.child("email").getValue(String::class.java)!!

                        SessionUser.currentUser = User(username, password, fullName, email)

                        // Implement your authentication logic using these retrieved values
                        // (compare password with stored hash, etc.)
                        completion (true)
                    }
                } else {
                    // Handle case where username not found
                    completion (false)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle Database errors
                completion (false)

            }
        })
    }
}
