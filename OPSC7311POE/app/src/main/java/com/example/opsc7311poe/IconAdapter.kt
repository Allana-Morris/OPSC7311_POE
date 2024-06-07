package com.example.opsc7311poe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311poe.databinding.ItemIconBinding


class IconAdapter(private val icons: List<Int>, private val listener: OnIconClickListener) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    interface OnIconClickListener {
        fun onIconClick(iconResId: Int)
    }

    inner class IconViewHolder(private val binding: ItemIconBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(iconResId: Int) {
            binding.iconImageView.setImageResource(iconResId)
            binding.root.setOnClickListener {
                listener.onIconClick(iconResId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val binding = ItemIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IconViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.bind(icons[position])
    }

    override fun getItemCount(): Int = icons.size
}
