package com.example.opsc7311poe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
//Adaptor used to create Iconpicker popup
class IconAdapter(
    private val icons: List<Int>,
    private val onIconClickListener: (Int) -> Unit
    ) : RecyclerView.Adapter<IconAdapter.IconViewHolder>() {

        //Creates Viewholder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
            //Creates view
            val view = LayoutInflater.from(parent.context).inflate(R.layout.icon_item, parent, false)
            return IconViewHolder(view)
        }

    //Binds Viewholder
        override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
            //Variable of Icons position
            val iconResId = icons[position]
        //Sets Icon Image view to Icon Image
            holder.iconImageView.setImageResource(iconResId)
            holder.itemView.setOnClickListener { onIconClickListener(iconResId) }
        }

        //Counts number of Icons
        override fun getItemCount(): Int {
            return icons.size
        }

    //Icon View Holder!!
    class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Declaration of Imageview
        val iconImageView: ImageView = itemView.findViewById(R.id.iconImageView)
    }
    }