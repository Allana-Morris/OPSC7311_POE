package com.example.opsc7311poe
//Object used to store details about each category
class Category(
    var name: String,
    var icon: Int,
    var colour: Int,
    var minHours: Double,
    var maxHours: Double,

    ) {
    // HashMap to store tasks for this category
    val tasks: HashMap<String, Task> = HashMap()

    //Constructor used to reference Category Object
    constructor() : this("", 0, 0, 0.0, 0.0)
}