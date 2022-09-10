package com.benten.creaturesapp.model

import kotlinx.coroutines.flow.Flow

interface CreaturesRepository {
    fun insertCreature(creature: Creature)
    fun getAllCreatures(): Flow<List<Creature>>
    fun searchCreatures(query: String): Flow<List<Creature>>
}