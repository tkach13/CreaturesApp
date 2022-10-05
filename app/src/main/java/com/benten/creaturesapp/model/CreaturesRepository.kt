package com.benten.creaturesapp.model


interface CreaturesRepository {
    fun insertCreature(creature: Creature, userId: String, onResult: (Boolean) -> Unit)
    fun getAllCreatures(userId: String, onResult: (List<Creature>) -> Unit)
}