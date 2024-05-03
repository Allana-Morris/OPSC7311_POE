package com.example.opsc7311poe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

//Adaptor used to create Iconpicker popup
class IconAdapter(
    private val icons: List<Int>,
    private val onIconClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

    //Creates Viewholder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.icon_item, parent, false)
        return IconViewHolder(view)
    }

    //Binds Viewholder
    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        val iconId = icons[position]
        holder.bind(iconId)
    }

    //Counts number of Icons
    override fun getItemCount(): Int {
        return icons.size
    }

    //Icon View Holder!!
    inner class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
        fun bind(iconId: Int): Int {
            var clickedIconId: Int = 0
            iconImageView.setImageResource(iconId)
            itemView.setOnClickListener {  // Get the position of the clicked item
                val position = adapterPosition
                // Check if the position is valid
                if (position != RecyclerView.NO_POSITION) {
                    // Retrieve the icon ID associated with the clicked item
                    clickedIconId = icons[position]
                    // Call onItemClick lambda function with the clicked icon ID

                }
            }
            return clickedIconId
        }
    }


}

