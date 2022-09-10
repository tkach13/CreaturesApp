package com.benten.creaturesapp.di

import com.benten.creaturesapp.model.CreatureGenerator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CreaturesModule {

    @Provides
    @Singleton
    fun provideCreaturesGenerator(): CreatureGenerator {
        return CreatureGenerator()
    }
}
