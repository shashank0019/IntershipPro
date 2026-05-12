package com.karunadavanya.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.karunadavanya.data.model.WildlifeAnimal
import java.io.IOException

object JsonAssetLoader {

    fun loadWildlifeAnimals(context: Context): List<WildlifeAnimal> {
        return try {
            val json = context.assets.open("wildlife_data.json").bufferedReader().use { it.readText() }
            val listType = object : TypeToken<List<WildlifeAnimal>>() {}.type
            Gson().fromJson(json, listType) ?: emptyList()
        } catch (exception: IOException) {
            emptyList()
        }
    }
}
