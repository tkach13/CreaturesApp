package com.benten.creaturesapp.model

import com.google.firebase.auth.FirebaseUser


interface UserRepository {
    fun createUser(user: User, onResult: (Boolean) -> Unit)
    fun logIn(user: User, onResult: (Boolean) -> Unit)
    fun logOut(user: User, onResult: (Boolean) -> Unit)
    fun getUser(): FirebaseUser?
    fun saveUser(user: User, onResult: (Boolean) -> Unit)
    fun getUserInfo(userId: String, onResult: (String?) -> Unit)
}