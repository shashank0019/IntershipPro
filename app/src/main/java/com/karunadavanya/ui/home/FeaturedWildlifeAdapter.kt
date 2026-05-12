package com.karunadavanya.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karunadavanya.R
import com.karunadavanya.data.model.WildlifeAnimal

class FeaturedWildlifeAdapter : RecyclerView.Adapter<FeaturedWildlifeAdapter.ViewHolder>() {

    private val items = mutableListOf<WildlifeAnimal>()

    fun submitList(list: List<WildlifeAnimal>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_featured_wildlife, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val cardBackground: View   = itemView.findViewById(R.id.cardBackground)
        private val animalIcon: ImageView  = itemView.findViewById(R.id.animalIcon)
        private val animalName: TextView   = itemView.findViewById(R.id.animalName)

        // Fixed logo mapping for Featured Wildlife cards.
        private fun logoFor(name: String): Int = when (name.lowercase()) {
            "tiger" -> R.drawable.animal_tiger
            "lion" -> R.drawable.animal_lion
            "elephant" -> R.drawable.animal_elephant
            "leopard" -> R.drawable.animal_leopard
            "bear", "sloth bear" -> R.drawable.animal_bear
            "gaur", "indian bison" -> R.drawable.animal_gaur
            "wild boar", "boar" -> R.drawable.animal_boar
            "crocodile", "mugger crocodile" -> R.drawable.animal_crocodile
            "snake", "king cobra" -> R.drawable.animal_snake
            "wolf", "indian wolf" -> R.drawable.animal_wolf
            "deer", "spotted deer", "spotted deer (chital)" -> R.drawable.animal_deer
            "peacock", "indian peacock" -> R.drawable.animal_peacock
            "monkey", "rhesus macaque" -> R.drawable.animal_monkey
            "fox" -> R.drawable.animal_fox
            "eagle" -> R.drawable.animal_eagle
            "bird", "birds" -> R.drawable.animal_bird
            else -> R.drawable.ic_tiger_placeholder
        }

        fun bind(item: WildlifeAnimal) {
            animalName.text = item.name
            animalIcon.setImageResource(logoFor(item.name))
            animalIcon.visibility = View.VISIBLE
            cardBackground.visibility = View.INVISIBLE
        }
    }
}
