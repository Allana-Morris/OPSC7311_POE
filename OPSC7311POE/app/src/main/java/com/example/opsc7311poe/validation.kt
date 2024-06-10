package com.example.opsc7311poe

import java.time.LocalTime
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class validation {

    //Checks in Input is equal to null or is empty
    fun checkStringNullOrEmpty(input: String?): Boolean {
        var bFlag = false
        if (input.isNullOrBlank()) {
            bFlag = true
        }
        //returns true if empty/null and false if there is stuff inside
        return bFlag
    }

    //Changes the Time input into a Double Format
    // eg. 3:30 becomes 3.5
    fun parseTimeToHours(enteredTime: LocalTime): Double {
        val hours = enteredTime.hour
        val minutes = enteredTime.minute
        require(hours >= 0 && minutes >= 0 && minutes < 60) { "Invalid time format" }
        return hours + minutes / 60.0
    }

    fun isCategoryNameExists(userId: String, categoryName: String, callback: (Boolean) -> Unit) {
        val database = FirebaseDatabase.getInstance().reference.child("user").child(userId).child("categories")
        database.orderByChild("name").equalTo(categoryName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    callback(snapshot.exists())
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                    callback(false)
                }
            })
    }


    fun isTaskNameExists(categoryId: String, taskName: String, callback: (Boolean) -> Unit) {
        val database = FirebaseDatabase.getInstance().reference.child("categories").child(categoryId).child("tasks")
        database.orderByChild("name").equalTo(taskName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    callback(snapshot.exists())
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                    callback(false)
                }
            })
    }

     fun validateGoals(minHours: String, maxHours: String): Boolean {
        return try {
            val minTime = LocalTime.parse("$minHours:00")
            val maxTime = LocalTime.parse("$maxHours:00")
            minTime.isBefore(maxTime)
        } catch (e: Exception) {
            false
        }
    }

}