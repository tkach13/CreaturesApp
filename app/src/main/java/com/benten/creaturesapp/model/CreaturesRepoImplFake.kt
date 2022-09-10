package com.benten.creaturesapp.model

import com.benten.creaturesapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CreaturesRepoImplFake : CreaturesRepository {
    override fun insertCreature(creature: Creature) {

    }

    override fun getAllCreatures(): Flow<List<Creature>> {
        return flowOf(
            listOf(
                Creature(
                    CreatureAttributes(8, 4, 2),
                    89,
                    "Merabi",
                    R.drawable.creature_duck_yellow_1
                )
            )
        )
    }

    override fun searchCreatures(query: String): Flow<List<Creature>> {
        return flowOf(emptyList())
    }
}