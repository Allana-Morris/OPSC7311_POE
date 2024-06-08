package com.example.opsc7311poe

import java.text.SimpleDateFormat
import java.util.Locale

object TaskRepository {
        val tasks = mutableListOf<Task>()
}

// Sample data for testing
fun initializeTasks() {
        val task1 = Task("Task 1", "Description", false, 8.0, 17.0).apply {
                taskRecords.add(
                        Recording(
                                RecDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse("2023-01-01").toString(),
                                StartTime = java.sql.Time.valueOf("08:00:00").toString(),
                                EndTime = java.sql.Time.valueOf("17:00:00").toString(),
                                Duration = "09:00:00",
                                image = null
                        )
                )
        }

        val task2 = Task("Task 2", "Description", false, 8.0, 17.0).apply {
                taskRecords.add(
                        Recording(
                                RecDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse("2023-01-02").toString(),
                                StartTime = java.sql.Time.valueOf("08:00:00").toString(),
                                EndTime = java.sql.Time.valueOf("17:00:00").toString(),
                                Duration = "09:00:00",
                                image = null
                        )
                )
        }

        TaskRepository.tasks.addAll(listOf(task1, task2))
}
