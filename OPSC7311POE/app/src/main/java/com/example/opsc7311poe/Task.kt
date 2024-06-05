package com.example.opsc7311poe

//Object storing details about each task created by User
class Task(
    var name: String,
    var description: String,
    var isRepeating: Boolean,
    var startTime: Double,
    var endTime: Double,
    val taskRecords: MutableList<Recording> = mutableListOf(),
)