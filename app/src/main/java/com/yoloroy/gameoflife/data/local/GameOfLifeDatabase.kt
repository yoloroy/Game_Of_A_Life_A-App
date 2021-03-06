package com.yoloroy.gameoflife.data.local

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.yoloroy.gameoflife.data.local.dao.ActivityDao
import com.yoloroy.gameoflife.data.local.dao.DreamsDao
import com.yoloroy.gameoflife.data.local.dao.ProfileDao
import com.yoloroy.gameoflife.data.local.entity.*

@Database(
    entities = [
        Challenge::class,
        ChallengeSkillCrossRef::class,
        Dream::class,
        DreamProgress::class,
        Profile::class,
        ProfileSkillCrossRef::class,
        Skill::class,
        Tag::class
    ],
    views = [
        DreamInfoWithProgress::class,
        DreamStatus::class
    ],
    version = 3,
    autoMigrations = [
        AutoMigration (from = 1, to = 2),
        AutoMigration (from = 2, to = 3)
    ]
)
abstract class GameOfLifeDatabase : RoomDatabase() {

    abstract fun activityDao(): ActivityDao

    abstract fun profileDao(): ProfileDao

    abstract fun dreamsDao(): DreamsDao
}
