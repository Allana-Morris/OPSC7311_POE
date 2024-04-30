package com.example.opsc7311poe

class User(
    var username: String,
    var fullName: String,
    var password: String,
    var email: String
)
{
    // HashMap to store categories for this user
    val categories: HashMap<String, Category> = HashMap()
}