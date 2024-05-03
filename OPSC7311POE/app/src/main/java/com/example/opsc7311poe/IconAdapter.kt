package com.example.opsc7311poe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class IconAdapter(
    private val icons: List<Int>,
    private val onIconClickListener: (Int) -> Unit
    ) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.icon_item, parent, false)
            return IconViewHolder(view)
        }

        override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
            val iconResId = icons[position]
            holder.iconImageView.setImageResource(iconResId)
            holder.itemView.setOnClickListener { onIconClickListener(iconResId) }
        }

        override fun getItemCount(): Int {
            return icons.size
        }
    class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
    }
    }