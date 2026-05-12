package com.karunadavanya.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karunadavanya.data.model.WildlifeAnimal
import com.karunadavanya.data.repository.EducationRepository

class EducationViewModel : ViewModel() {

    private val repository = EducationRepository()
    private val _animals = MutableLiveData<List<WildlifeAnimal>>()
    val animals: LiveData<List<WildlifeAnimal>> = _animals

    fun loadAnimals(context: Context) {
        _animals.value = repository.getWildlifeAnimals(context)
    }
}
