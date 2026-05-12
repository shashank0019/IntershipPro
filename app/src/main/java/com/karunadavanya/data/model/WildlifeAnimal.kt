package com.karunadavanya.data.model

data class WildlifeAnimal(
    val name: String = "",
    val description: String = "",
    val habitat: String = "",
    val behavior: String = "",
    val safetyTips: String = "",
    val imageName: String = "",
    /** 4 co-existence tips shown when the card is expanded */
    val tips: List<String> = emptyList()
)
