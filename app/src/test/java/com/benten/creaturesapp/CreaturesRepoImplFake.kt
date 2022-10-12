package com.benten.creaturesapp

import com.benten.creaturesapp.model.Creature
import com.benten.creaturesapp.model.CreatureAttributes
import com.benten.creaturesapp.model.CreaturesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CreaturesRepoImplFake : CreaturesRepository {
    override fun insertCreature(creature: Creature, userId: String, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getAllCreatures(userId: String, onResult: (List<Creature>) -> Unit) {
        TODO("Not yet implemented")
    }

}