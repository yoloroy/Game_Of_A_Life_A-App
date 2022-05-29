package com.yoloroy.gameoflife.di

import android.content.Context
import androidx.room.Room
import com.yoloroy.gameoflife.data.data_source.ActivityLocalDataSource
import com.yoloroy.gameoflife.data.data_source.DreamsLocalDataSource
import com.yoloroy.gameoflife.data.data_source.ProfileLocalDataSource
import com.yoloroy.gameoflife.data.local.GameOfLifeDatabase
import com.yoloroy.gameoflife.data.local.RoomActivityLocalDataSourceImpl
import com.yoloroy.gameoflife.data.local.RoomDreamsLocalDataSourceImpl
import com.yoloroy.gameoflife.data.local.RoomProfileLocalDataSourceImpl
import com.yoloroy.gameoflife.data.local.dao.ActivityDao
import com.yoloroy.gameoflife.data.local.dao.DreamsDao
import com.yoloroy.gameoflife.data.local.dao.ProfileDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideGameOfLifeDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        GameOfLifeDatabase::class.java,
        "app_database"
    ).build()

    @Provides
    fun provideActivityDao(db: GameOfLifeDatabase) = db.activityDao()

    @Provides
    fun provideProfileDao(db: GameOfLifeDatabase) = db.profileDao()

    @Provides
    fun provideDreamsDao(db: GameOfLifeDatabase) = db.dreamsDao()

    @Provides
    fun provideDreamsLocalDataSource(dreamsDao: DreamsDao): DreamsLocalDataSource {
        return RoomDreamsLocalDataSourceImpl(dreamsDao)
    }

    @Provides
    fun provideActivityLocalDataSource(activityDao: ActivityDao): ActivityLocalDataSource {
        return RoomActivityLocalDataSourceImpl(activityDao)
    }

    @Provides
    fun provideProfileLocalDataSource(profileDao: ProfileDao): ProfileLocalDataSource {
        return RoomProfileLocalDataSourceImpl(profileDao)
    }
}
