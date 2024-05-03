package com.example.opsc7311poe

//Object storing User Details
class User(
    var username: String,
    var fullName: String,
    var password: String,
    var email: String
)
{
    //Hashmap containing all Categories created by User
    // HashMap to store categories for this user
    val categories: HashMap<String, Category> = HashMap()
}