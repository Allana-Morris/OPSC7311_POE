package com.example.opsc7311poe

import java.time.LocalTime

class validation
{
    fun checkStringNullOrEmpty(input: String?): Boolean
    {
        var bFlag = false
        if (input.isNullOrBlank())
        {
            bFlag = true;
        }
        return bFlag;
    }

    fun checkExistingUserEmail(input: String?): Boolean
    {
        return UserList.users.any { it.email == input }
    }

    fun checkExistingUserUserName(input: String?): Boolean
    {
        return UserList.users.any { it.username == input }
    }

    fun parseTimeToHours(enteredTime: LocalTime): Double {
        val hours = enteredTime.hour
        val minutes = enteredTime.minute
        require(hours >= 0 && minutes >= 0 && minutes < 60) { "Invalid time format" }
        return hours + minutes / 60.0
    }



}