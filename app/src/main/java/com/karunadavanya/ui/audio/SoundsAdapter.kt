package com.karunadavanya.ui.audio

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.karunadavanya.R
import com.karunadavanya.data.model.SoundItem

class SoundsAdapter(private val listener: SoundActionListener) : RecyclerView.Adapter<SoundsAdapter.SoundViewHolder>() {

    interface SoundActionListener {
        fun onPlay(soundItem: SoundItem)
        fun onStopAudio()
    }

    private val items = mutableListOf<SoundItem>()
    private var playingIndex: Int = -1

    fun submitList(list: List<SoundItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoundViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sound_card, parent, false)
        return SoundViewHolder(view)
    }

    override fun onBindViewHolder(holder: SoundViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    fun setPlaying(position: Int) {
        val old = playingIndex
        playingIndex = position
        if (old != -1) notifyItemChanged(old)
        if (playingIndex != -1) notifyItemChanged(playingIndex)
    }

    inner class SoundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val soundTitle: TextView = itemView.findViewById(R.id.soundTitle)
        private val soundDescription: TextView = itemView.findViewById(R.id.soundDescription)
        private val playButton: ImageButton = itemView.findViewById(R.id.playButton)
        private val stopButton: ImageButton = itemView.findViewById(R.id.stopButton)

        fun bind(item: SoundItem, position: Int) {
            soundTitle.text = item.title
            soundDescription.text = item.description
            
            val isPlaying = position == playingIndex
            
            playButton.setImageResource(if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play)
            stopButton.visibility = if (isPlaying) View.VISIBLE else View.GONE

            playButton.setOnClickListener { 
                if (isPlaying) {
                    listener.onStopAudio() // Simplified toggle
                } else {
                    listener.onPlay(item)
                    setPlaying(position)
                }
            }
            
            stopButton.setOnClickListener { 
                listener.onStopAudio()
                setPlaying(-1)
            }
        }
    }
}
