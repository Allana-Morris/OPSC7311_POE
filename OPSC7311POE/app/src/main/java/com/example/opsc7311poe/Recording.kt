package com.example.opsc7311poe

import android.media.Image
import java.sql.Time
import java.util.Date

//Object string date for Timer
class Recording(
    var RecDate: Date,
    var StartTime: Time,
    var EndTime: Time,
    var Duration: String,
    var image: Image?,
)
