package com.yoloroy.gameoflife.di

import com.yoloroy.gameoflife.data.data_source.ActivityLocalDataSource
import com.yoloroy.gameoflife.data.data_source.DreamsLocalDataSource
import com.yoloroy.gameoflife.data.data_source.ProfileLocalDataSource
import com.yoloroy.gameoflife.data.fake.AuthRepositoryFakeImpl
import com.yoloroy.gameoflife.data.impl.ActivityRepositoryImpl
import com.yoloroy.gameoflife.data.impl.DreamsRepositoryImpl
import com.yoloroy.gameoflife.data.impl.ProfileRepositoryImpl
import com.yoloroy.gameoflife.data.remote.DreamsRemoteSource
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
object RepositoriesModule {

    @Provides
    fun provideAuthRepository(): AuthRepository = AuthRepositoryFakeImpl

    @Provides
    fun provideActivityRepository(
        activityLocalDataSource: ActivityLocalDataSource
    ): ActivityRepository = ActivityRepositoryImpl(
        activityLocalDataSource
    )

    @Provides
    fun provideDreamsRepository(
        dreamsRemoteSource: DreamsRemoteSource,
        dreamsLocalDataSource: DreamsLocalDataSource
    ): DreamsRepository = DreamsRepositoryImpl(
        dreamsRemoteSource,
        dreamsLocalDataSource
    )

    @Provides
    fun provideProfileRepository(
        profileLocalDataSource: ProfileLocalDataSource
    ): ProfileRepository = ProfileRepositoryImpl(
        profileLocalDataSource
    )
}
