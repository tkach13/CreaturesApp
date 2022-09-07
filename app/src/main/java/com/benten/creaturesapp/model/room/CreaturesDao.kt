package com.benten.creaturesapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benten.creaturesapp.model.Creature
import kotlinx.coroutines.flow.Flow

@Dao
interface CreaturesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCreature(creature: Creature)

    @Query("SELECT * FROM creatures")
    fun getAllCreatures(): Flow<List<Creature>>

    @Query("SELECT * FROM creatures WHERE name LIKE :query || '%'")
    fun searchCreatures(query: String): Flow<List<Creature>>
}