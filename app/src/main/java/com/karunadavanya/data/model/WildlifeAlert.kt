package com.karunadavanya.data.model

data class WildlifeAlert(
    val id: String = "",
    val animalType: String = "",
    val description: String = "",
    val timestampMillis: Long = 0L,
    val imageUrl: String? = null,
    val locationText: String? = null,
    val reporterEmail: String = ""
)
