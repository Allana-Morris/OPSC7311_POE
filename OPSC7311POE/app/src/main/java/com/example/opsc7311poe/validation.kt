package com.example.opsc7311poe

import java.time.LocalTime

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

    //Checks if User Email exists inside the List of already Registered User Details
    fun checkExistingUserEmail(input: String?): Boolean {
        return UserList.users.any { it.email == input }
    }

    //Checks if Username User has entered as already been selected by another User
    fun checkExistingUserUserName(input: String?): Boolean {
        return UserList.users.any { it.username == input }
    }

    //Changes the Time input into a Double Format
    // eg. 3:30 becomes 3.5
    fun parseTimeToHours(enteredTime: LocalTime): Double {
        val hours = enteredTime.hour
        val minutes = enteredTime.minute
        require(hours >= 0 && minutes >= 0 && minutes < 60) { "Invalid time format" }
        return hours + minutes / 60.0
    }


}