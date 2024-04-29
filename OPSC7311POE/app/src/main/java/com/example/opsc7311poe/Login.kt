package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        val buttonOpenActivity = findViewById<TextView>(R.id.tv_Open_Register)
        buttonOpenActivity.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        val buttonLogin = findViewById<Button>(R.id.btn_Login)
        buttonLogin.setOnClickListener {
            //Login code - Checks and Stuffs

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}