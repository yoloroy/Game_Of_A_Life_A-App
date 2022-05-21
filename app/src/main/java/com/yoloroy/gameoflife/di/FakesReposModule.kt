package com.yoloroy.gameoflife.di

import com.yoloroy.gameoflife.data.fake.ActivityRepositoryFakeImpl
import com.yoloroy.gameoflife.data.fake.AuthRepositoryFakeImpl
import com.yoloroy.gameoflife.data.fake.DreamsRepositoryFakeImpl
import com.yoloroy.gameoflife.data.fake.ProfileRepositoryFakeImpl
import com.yoloroy.gameoflife.domain.repository.ActivityRepository
import com.yoloroy.gameoflife.domain.repository.AuthRepository
import com.yoloroy.gameoflife.domain.repository.DreamsRepository
import com.yoloroy.gameoflife.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FakesReposModule {

    @Provides
    fun provideDreamDetailsRepository(): AuthRepository = AuthRepositoryFakeImpl

    @Provides
    fun provideDreamsLibraryRepository(): ActivityRepository = ActivityRepositoryFakeImpl

    @Provides
    fun provideDreamsRepository(): DreamsRepository = DreamsRepositoryFakeImpl

    @Provides
    fun provideProfileRepository(): ProfileRepository = ProfileRepositoryFakeImpl
}
