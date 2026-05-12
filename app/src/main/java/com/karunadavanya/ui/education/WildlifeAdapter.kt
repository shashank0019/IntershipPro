package com.karunadavanya.ui.education

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karunadavanya.R
import com.karunadavanya.data.model.WildlifeAnimal

class WildlifeAdapter : RecyclerView.Adapter<WildlifeAdapter.WildlifeViewHolder>() {

    private val items = mutableListOf<WildlifeAnimal>()

    /** Tracks which list positions are currently expanded */
    private val expandedPositions = mutableSetOf<Int>()

    fun submitList(list: List<WildlifeAnimal>) {
        items.clear()
        items.addAll(list)
        expandedPositions.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WildlifeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wildlife_card, parent, false)
        return WildlifeViewHolder(view)
    }

    override fun onBindViewHolder(holder: WildlifeViewHolder, position: Int) {
        holder.bind(
            item       = items[position],
            isExpanded = expandedPositions.contains(position),
            onToggle   = {
                if (expandedPositions.contains(position)) expandedPositions.remove(position)
                else expandedPositions.add(position)
                notifyItemChanged(position)
            }
        )
    }

    override fun getItemCount(): Int = items.size

    class WildlifeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val animalImage: ImageView     = itemView.findViewById(R.id.animalImage)
        private val animalName: TextView       = itemView.findViewById(R.id.animalName)
        private val animalDescription: TextView= itemView.findViewById(R.id.animalDescription)
        private val animalHabitat: TextView    = itemView.findViewById(R.id.animalHabitat)
        private val expandChevron: ImageView   = itemView.findViewById(R.id.expandChevron)
        private val tipsContainer: LinearLayout= itemView.findViewById(R.id.tipsContainer)
        private val tipsDivider: View          = itemView.findViewById(R.id.tipsDivider)
        private val tipsList: TextView         = itemView.findViewById(R.id.tipsList)

        fun bind(item: WildlifeAnimal, isExpanded: Boolean, onToggle: () -> Unit) {
            animalName.text        = item.name
            animalDescription.text = item.description
            animalHabitat.text     = item.habitat

            // Load AI-generated animal image from drawable by name
            val imageId = itemView.resources.getIdentifier(
                item.imageName, "drawable", itemView.context.packageName
            )
            if (imageId != 0) {
                animalImage.setImageResource(imageId)
                animalImage.scaleType = android.widget.ImageView.ScaleType.CENTER_CROP
            } else {
                // Fallback: use paw placeholder tinted green
                animalImage.setImageResource(R.drawable.ic_tiger_placeholder)
                animalImage.scaleType = android.widget.ImageView.ScaleType.CENTER_INSIDE
            }

            // Populate tips (4 bullet points)
            tipsList.text = if (item.tips.isNotEmpty()) {
                item.tips.joinToString("\n\n") { "▶  $it" }
            } else ""

            // Apply expanded / collapsed state
            val show = isExpanded && item.tips.isNotEmpty()
            tipsContainer.visibility = if (show) View.VISIBLE else View.GONE
            tipsDivider.visibility   = if (show) View.VISIBLE else View.GONE
            expandChevron.setImageResource(
                if (show) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down
            )

            // Toggle on chevron click
            expandChevron.setOnClickListener { onToggle() }
        }
    }
}
