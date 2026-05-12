package com.karunadavanya.ui.audio

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karunadavanya.R
import com.karunadavanya.data.model.SoundItem

class SoundsFragment : Fragment(), SoundsAdapter.SoundActionListener {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var adapter: SoundsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_sounds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = SoundsAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.soundsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        adapter.submitList(
            listOf(
                SoundItem("Bird Sounds", "bird_sounds", "Gentle forest birds for calm listening."),
                SoundItem("Elephant Sounds", "elephant_sounds", "Educational elephant call audio."),
                SoundItem("Forest Ambience", "forest_ambience", "Relaxing background forest ambience.")
            )
        )
    }

    override fun onPlay(soundItem: SoundItem) {
        stopAudio()
        val resId = resources.getIdentifier(soundItem.rawName, "raw", requireContext().packageName)
        if (resId == 0) {
            return
        }
        try {
            mediaPlayer = MediaPlayer.create(requireContext(), resId).apply {
                start()
                setOnCompletionListener { 
                    stopAudio()
                    adapter.setPlaying(-1)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStopAudio() {
        stopAudio()
        adapter.setPlaying(-1)
    }

    private fun stopAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    override fun onDestroyView() {
        stopAudio()
        super.onDestroyView()
    }
}
