package com.benten.creaturesapp.di

import com.benten.creaturesapp.model.UserRepoImpl
import com.benten.creaturesapp.model.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UsersModule {

    @Binds
    @Singleton
    abstract fun getUsersRepo(userRepoImpl: UserRepoImpl): UserRepository

    companion object {

        @Provides
        fun getUserRepoImpl(
            firebaseAuth: FirebaseAuth,
            firebaseDatabase: FirebaseDatabase
        ): UserRepoImpl = UserRepoImpl(firebaseAuth, firebaseDatabase)

        @Provides
        fun getFirebaseAuth() = Firebase.auth

        @Provides
        fun getFirebaseDatabase() = FirebaseDatabase.getInstance("https://creatures-app-148a3-default-rtdb.firebaseio.com/")
    }
}