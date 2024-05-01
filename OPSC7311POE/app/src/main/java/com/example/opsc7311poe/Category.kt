package com.example.opsc7311poe

public class Category(
    var name: String,
    var icon: Int,
    var colour: Int,
    var minHours: Double,
    var maxHours: Double,

    ) {
    // HashMap to store tasks for this category
    val tasks: HashMap<String, Task> = HashMap()

}