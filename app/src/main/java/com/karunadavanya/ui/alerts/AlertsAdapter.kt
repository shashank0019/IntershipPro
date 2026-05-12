package com.karunadavanya.ui.alerts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karunadavanya.R
import com.karunadavanya.data.model.WildlifeAlert
import com.karunadavanya.utils.TimeUtils

class AlertsAdapter : RecyclerView.Adapter<AlertsAdapter.AlertViewHolder>() {

    private val items = mutableListOf<WildlifeAlert>()

    fun submitList(list: List<WildlifeAlert>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alert_card, parent, false)
        return AlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlertViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class AlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val alertAnimal: TextView = itemView.findViewById(R.id.alertAnimal)
        private val alertDescription: TextView = itemView.findViewById(R.id.alertDescription)
        private val alertLocation: TextView = itemView.findViewById(R.id.alertLocation)
        private val alertLocationContainer: View = itemView.findViewById(R.id.alertLocationContainer)
        private val alertTimestamp: TextView = itemView.findViewById(R.id.alertTimestamp)

        fun bind(item: WildlifeAlert) {
            alertAnimal.text = item.animalType
            alertDescription.text = item.description.ifBlank { "No additional description provided." }
            alertTimestamp.text = TimeUtils.formatTimestamp(item.timestampMillis)
            
            if (!item.locationText.isNullOrBlank()) {
                alertLocation.text = item.locationText
                alertLocationContainer.visibility = View.VISIBLE
            } else {
                alertLocationContainer.visibility = View.GONE
            }
        }
    }
}
