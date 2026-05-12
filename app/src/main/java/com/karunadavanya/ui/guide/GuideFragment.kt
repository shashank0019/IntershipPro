package com.karunadavanya.ui.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karunadavanya.R
import com.karunadavanya.viewmodel.GuideViewModel

class GuideFragment : Fragment() {

    private lateinit var viewModel: GuideViewModel
    private lateinit var adapter: GuideAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[GuideViewModel::class.java]
        adapter = GuideAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.guideRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.loadItems()
    }
}
