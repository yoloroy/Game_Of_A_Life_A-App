package com.yoloroy.gameoflife.di

import com.yoloroy.gameoflife.data.fake.*
import com.yoloroy.gameoflife.domain.bad_repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FakesReposModule {

    @Provides
    fun provideDreamDetailsRepository(): DreamDetailsRepository = DreamDetailsFakeRepository

    @Provides
    fun provideDreamsLibraryRepository(): DreamsLibraryRepository = DreamsLibraryFakeRepository

    @Provides
    fun provideDreamsRepository(): DreamsRepository = DreamsRepositoryFakeImpl

    @Provides
    fun provideLoginRepository(): LoginRepository = LoginRepositoryFakeImpl

    @Provides
    fun provideProfileSettingsRepository(): ProfileSettingsRepository = ProfileSettingsFakeRepository

    @Provides
    fun provideProfileRepository(): ProfileRepository = ProfileRepositoryFakeImpl

    @Provides
    fun provideRegistrationRepository(): RegistrationRepository = RegistrationRepositoryFakeImpl

    @Provides
    fun provideSettingsListRepository(): SettingsListRepository = SettingsListRepositoryFakeImpl
}
