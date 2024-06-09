package com.example.opsc7311poe

import Recording

//Object storing details about each task created by User
class Task @JvmOverloads constructor(
    var name: String = "",
    var description: String = "",
    var isRepeating: Boolean = false,
    var startTime: Double = 0.0,
    var endTime: Double = 0.0,
    val taskRecords: MutableList<Recording> = mutableListOf()
)