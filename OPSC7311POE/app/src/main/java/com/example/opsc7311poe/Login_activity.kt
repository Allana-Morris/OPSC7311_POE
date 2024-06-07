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

class Login : AppCompatActivity() {
    val database: FirebaseDatabase =
        FirebaseDatabase.getInstance("https://atomic-affinity-421915-default-rtdb.europe-west1.firebasedatabase.app/")

    val DbRef = database.getReference("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        //Initialization of sign-in components
        val usernameTextView: TextView = findViewById(R.id.tv_Username)
        val passwordTextView: TextView = findViewById(R.id.tv_Password)
        val buttonOpenActivity = findViewById<TextView>(R.id.tv_Open_Register)
        val buttonLogin = findViewById<Button>(R.id.btn_Login)

        //onClickListener used to open Register Page
        buttonOpenActivity.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        //onClickListener used to Login
        buttonLogin.setOnClickListener {
            val username = usernameTextView.text.toString()
            val password = passwordTextView.text.toString()

            val valid = true

            if (valid) {


                // Firebase authentication logic
                authenticate(username, password) { authenticated ->

                    if (authenticated) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT)
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
                        val user = userSnapshot.getValue(User::class.java)
                        if (user != null && user.password == password) {
                            SessionUser.currentUser = user // Set the current user session
                            completion(true)
                            return
                        }
                    }
                }
                completion(dataSnapshot.exists())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                completion(false)
            }
        })
    }
}
