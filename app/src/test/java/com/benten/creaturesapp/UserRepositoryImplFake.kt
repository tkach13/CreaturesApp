package com.benten.creaturesapp

import com.benten.creaturesapp.model.User
import com.benten.creaturesapp.model.UserRepository
import com.google.firebase.auth.FirebaseUser

class UserRepositoryImplFake:UserRepository {
    override fun createUser(user: User, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun logIn(user: User, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun logOut(user: User, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getUserInfo(userId: String, onResult: (String?) -> Unit) {
        TODO("Not yet implemented")
    }
}