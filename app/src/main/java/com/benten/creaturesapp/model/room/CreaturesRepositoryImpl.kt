package com.benten.creaturesapp.model.room

import com.benten.creaturesapp.model.Creature
import com.benten.creaturesapp.model.CreaturesRepository
import com.benten.creaturesapp.model.UserRepoImpl
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class CreaturesRepositoryImpl @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    CreaturesRepository {
    override fun insertCreature(creature: Creature, userId: String, onResult: (Boolean) -> Unit) {
        firebaseDatabase
            .reference
            .child(UserRepoImpl.USER_KEY)
            .child(userId)
            .child(KEY_CREATURES)
            .push()
            .setValue(creature)
    }

    override fun getAllCreatures(userId: String, onResult: (List<Creature>) -> Unit) {
        firebaseDatabase
            .reference
            .child(UserRepoImpl.USER_KEY)
            .child(userId)
            .child(KEY_CREATURES)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.run {
                        val creatureList = children.mapNotNull { it.getValue(Creature::class.java) }
                        onResult.invoke(creatureList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    companion object {
        const val KEY_CREATURES = "creatures"
    }

}