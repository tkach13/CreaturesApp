package com.benten.creaturesapp.di

import android.content.Context
import androidx.room.Room
import com.benten.creaturesapp.model.CreaturesRepository
import com.benten.creaturesapp.model.room.CreatureDatabase
import com.benten.creaturesapp.model.room.CreaturesDao
import com.benten.creaturesapp.model.room.CreaturesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {


    @Binds
    @Singleton
    abstract fun provideCreaturesRepo(creaturesRepositoryImpl: CreaturesRepositoryImpl): CreaturesRepository


    companion object {

        @Provides
        fun provideCreaturesRepoImpl(creaturesDao: CreaturesDao): CreaturesRepositoryImpl {
            return CreaturesRepositoryImpl(creaturesDao)
        }

        @Provides
        @Singleton
        fun provideCreaturesDatabase(@ApplicationContext context: Context): CreatureDatabase {
            return Room.databaseBuilder(context, CreatureDatabase::class.java, "creatures_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }


        @Provides
        @Singleton
        fun provideCreaturesDao(creatureDatabase: CreatureDatabase): CreaturesDao =
            creatureDatabase.creaturesDao()
    }
}