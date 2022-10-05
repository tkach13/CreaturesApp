package com.benten.creaturesapp.di


import com.benten.creaturesapp.model.CreaturesRepository
import com.benten.creaturesapp.model.room.CreaturesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        fun provideCreaturesRepoImpl(
        ): CreaturesRepositoryImpl {
            return CreaturesRepositoryImpl()
        }

    }
}