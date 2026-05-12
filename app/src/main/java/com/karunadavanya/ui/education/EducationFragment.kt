package com.karunadavanya.ui.education

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.karunadavanya.R
import com.karunadavanya.viewmodel.EducationViewModel

class EducationFragment : Fragment() {

    private lateinit var viewModel: EducationViewModel
    private lateinit var adapter: WildlifeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_education, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Use activity scope to share the same ViewModel instance if data is already loaded
        viewModel = ViewModelProvider(requireActivity())[EducationViewModel::class.java]
        adapter = WildlifeAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.wildlifeRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        viewModel.animals.observe(viewLifecycleOwner) { 
            if (it != null) {
                adapter.submitList(it)
            }
        }
        
        // Only load if data is not already there
        if (viewModel.animals.value == null) {
            viewModel.loadAnimals(requireContext())
        }
    }
}
