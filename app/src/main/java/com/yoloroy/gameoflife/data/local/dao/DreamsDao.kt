package com.yoloroy.gameoflife.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.yoloroy.gameoflife.data.local.entity.*

@Dao
interface DreamsDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertDream(dream: Dream)

    @Insert(onConflict = REPLACE)
    suspend fun insertTags(tags: List<Tag>)

    @Insert(onConflict = REPLACE)
    suspend fun insertChallenges(challenges: List<Challenge>)

    @Transaction
    @Query("""
        SELECT *
        FROM dream
        WHERE dream_id = :dreamId
    """)
    suspend fun getFullDream(dreamId: Int): DreamFull?
}

@Transaction
suspend fun DreamsDao.insertFullDream(dreamFull: DreamFull) {
    insertDreamInfo(dreamFull.dreamInfo)
    insertChallenges(dreamFull.challenges)
}

@Transaction
suspend fun DreamsDao.insertDreamInfo(dreamInfo: DreamInfo) {
    insertDream(dreamInfo.dream)
    insertTags(dreamInfo.tags)
}
