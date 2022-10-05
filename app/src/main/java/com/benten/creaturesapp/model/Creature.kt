package com.benten.creaturesapp.model


data class Creature(
    val attributes: CreatureAttributes = CreatureAttributes(),
    val hitPoints: Int = 0,
    val name: String? = null,
    val drawable: Int = 0
)
