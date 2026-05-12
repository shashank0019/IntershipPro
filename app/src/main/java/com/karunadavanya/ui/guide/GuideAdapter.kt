package com.karunadavanya.ui.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.karunadavanya.R
import com.karunadavanya.data.model.SafetyGuideItem

class GuideAdapter : RecyclerView.Adapter<GuideAdapter.GuideViewHolder>() {

    private val items = mutableListOf<SafetyGuideItem>()

    /** Track which positions are currently expanded */
    private val expandedPositions = mutableSetOf<Int>()

    fun submitList(list: List<SafetyGuideItem>) {
        items.clear()
        items.addAll(list)
        expandedPositions.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_guide_card, parent, false)
        return GuideViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
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

    class GuideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val animalImage: ImageView    = itemView.findViewById(R.id.guideAnimalImage)
        private val guideTitle: TextView      = itemView.findViewById(R.id.guideTitle)
        private val expandButton: MaterialCardView = itemView.findViewById(R.id.expandButton)
        private val expandIcon: ImageView     = itemView.findViewById(R.id.expandIcon)
        private val detailsContainer: LinearLayout = itemView.findViewById(R.id.detailsContainer)
        private val guideDetails: TextView    = itemView.findViewById(R.id.guideDetails)

        fun bind(item: SafetyGuideItem, isExpanded: Boolean, onToggle: () -> Unit) {
            guideTitle.text   = item.title
            guideDetails.text = item.details

            // Load AI animal image from drawable by name
            val imageId = itemView.resources.getIdentifier(
                item.imageName, "drawable", itemView.context.packageName
            )
            if (imageId != 0) {
                animalImage.setImageResource(imageId)
                animalImage.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                animalImage.setImageResource(R.drawable.ic_tiger_placeholder)
                animalImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }

            // Apply expanded / collapsed state
            detailsContainer.visibility = if (isExpanded) View.VISIBLE else View.GONE
            expandIcon.setImageResource(
                if (isExpanded) R.drawable.ic_chevron_up else R.drawable.ic_chevron_down
            )

            // Toggle expand on button click
            expandButton.setOnClickListener { onToggle() }
        }
    }
}
