package com.benten.creaturesapp.model


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : UserRepository {
    override fun createUser(user: User, onResult: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(user.eMail!!, user.password!!)
            .addOnCompleteListener {
                onResult.invoke(it.isSuccessful)
            }
    }

    override fun logIn(user: User, onResult: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(user.eMail!!, user.password!!)
            .addOnCompleteListener {
                onResult.invoke(it.isSuccessful)
            }
    }

    override fun logOut(user: User, onResult: (Boolean) -> Unit) {
        firebaseAuth.signOut()
    }

    override fun getUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun saveUser(user: User, onResult: (Boolean) -> Unit) {
        firebaseDatabase.reference
            .child(USER_KEY)
            .child(user.userid ?: "")
            .setValue(user)
            .addOnCompleteListener {
                onResult.invoke(it.isSuccessful)
            }
    }

    override fun getUserInfo(userId: String, onResult: (String?) -> Unit) {
        firebaseDatabase.reference
            .child(USER_KEY)
            .child(userId ?: "")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    onResult.invoke(user?.fullName)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    companion object {
        const val USER_KEY = "user"
    }

}