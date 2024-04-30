package com.example.opsc7311poe

class Category(var name: String) {
    // HashMap to store tasks for this category
    val tasks: HashMap<String, Task> = HashMap()
}