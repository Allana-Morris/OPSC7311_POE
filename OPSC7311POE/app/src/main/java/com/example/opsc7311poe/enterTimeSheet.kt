package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class enterTimeSheet : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_enter_time_sheet)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val spinnerId2 = findViewById<Spinner>(R.id.spinEntryTask)
        val task = arrayOf("dogs","cats","birds","Rhinos")
        val arrayAdp2 = ArrayAdapter(this@enterTimeSheet,android.R.layout.simple_spinner_dropdown_item,task)
        spinnerId2.adapter = arrayAdp2
        spinnerId2?.onItemClickListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@enterTimeSheet,"item is ${task[position]}",Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@enterTimeSheet,"nothing selected",Toast.LENGTH_LONG).show()
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@enterTimeSheet,"item is ${task[position]}",Toast.LENGTH_LONG).show()
            }}

        val spinnerId = findViewById<Spinner>(R.id.spinEntryCat)
        val category = arrayOf("dogs","cats","birds","Rhinos")
        val arrayAdp = ArrayAdapter(this@enterTimeSheet,android.R.layout.simple_spinner_dropdown_item,category)
        spinnerId.adapter = arrayAdp

        spinnerId?.onItemClickListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@enterTimeSheet,"item is ${category[position]}",Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(this@enterTimeSheet,"nothing selected",Toast.LENGTH_LONG).show()
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@enterTimeSheet,"item is ${category[position]}",Toast.LENGTH_LONG).show()
            }
        }
    }
}