package com.example.opsc7311poe

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


}