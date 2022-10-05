package com.benten.creaturesapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benten.creaturesapp.model.Creature
import kotlinx.coroutines.flow.Flow

interface CreaturesDao {
    fun insertCreature(creature: Creature)

    fun getAllCreatures(): Flow<List<Creature>>

    fun searchCreatures(query: String): Flow<List<Creature>>
}