package com.benten.creaturesapp.model.room

import com.benten.creaturesapp.model.Creature
import com.benten.creaturesapp.model.CreaturesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreaturesRepositoryImpl @Inject constructor(private val creaturesDao: CreaturesDao) :
    CreaturesRepository {

    override fun insertCreature(creature: Creature) {
        creaturesDao.insertCreature(creature)
    }

    override fun getAllCreatures(): Flow<List<Creature>> {
        return creaturesDao.getAllCreatures()
    }

    override fun searchCreatures(query: String): Flow<List<Creature>> {
        return creaturesDao.searchCreatures(query)
    }

}