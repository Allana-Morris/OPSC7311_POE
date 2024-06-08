package com.example.opsc7311poe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter(private val tasks: List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTaskName: TextView = itemView.findViewById(R.id.tvTaskName)
        val tvTaskTime: TextView = itemView.findViewById(R.id.tvTaskTime)
        val tvTaskDescription: TextView = itemView.findViewById(R.id.tvTaskDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.tvTaskName.text = task.name
        holder.tvTaskDescription.text = task.description

        val startTimeFormatted = formatTime(task.startTime)
        val endTimeFormatted = formatTime(task.endTime)
        holder.tvTaskTime.text = "$startTimeFormatted - $endTimeFormatted"
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    private fun formatTime(time: Double): String {
        val hours = time.toInt()
        val minutes = ((time - hours) * 60).toInt()
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
        }
        val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
        return sdf.format(calendar.time)
    }
}
