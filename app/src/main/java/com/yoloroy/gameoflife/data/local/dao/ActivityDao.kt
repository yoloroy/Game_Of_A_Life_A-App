package com.yoloroy.gameoflife.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.yoloroy.gameoflife.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Transaction
    @Query("""
        SELECT *
        FROM dream d
        INNER JOIN dream_status ds ON ds.dream_id = d.dream_id AND NOT ds.is_finished
        WHERE ds.profile_id = ${Profile.Default.localProfileId}
    """)
    fun getCurrentDreamInfos(): Flow<List<DreamInfo>>

    @Transaction
    @Query("""
        SELECT *
        FROM dream d
        INNER JOIN dream_status ds ON ds.dream_id = d.dream_id AND NOT ds.is_finished
        WHERE ds.profile_id = ${Profile.Default.localProfileId}
    """)
    fun getCurrentDreamsDetails(): Flow<List<DreamFull>>

    @Transaction
    @Query("""
        SELECT c.*
        FROM challenge c
        INNER JOIN dream_progress dp ON dp.dream_id = c.dream_id
        WHERE
            dp.profile_id = ${Profile.Default.localProfileId} AND 
            (dp.progress + 1) = c.`no`
        GROUP BY c.dream_id, dp.profile_id
    """)
    fun getCurrentChallengesWithDreamInfo(): Flow<List<ChallengeFullWithDream>>

    @Query("""
        SELECT *
        FROM dream_status
        WHERE
            profile_id = ${Profile.Default.localProfileId} AND
            dream_id = :dreamId
    """)
    fun getDreamStatusOrNull(dreamId: Int): Flow<DreamStatus?>

    @Query("""
        UPDATE dream_progress
        SET progress = progress + 1
        WHERE
            profile_id = ${Profile.Default.localProfileId} AND
            dream_id = (
                SELECT dream_id
                FROM challenge
                WHERE challenge_id = :challengeId
            )
    """)
    suspend fun completeChallenge(challengeId: Int)

    @Query("""
        INSERT INTO dream_progress (profile_id, dream_id, progress)
        VALUES (${Profile.Default.localProfileId}, :dreamId, 0)
    """)
    suspend fun subscribeOnDream(dreamId: Int)

    @Query("""
        DELETE FROM dream_progress
        WHERE
            profile_id = ${Profile.Default.localProfileId} AND
            dream_id = :dreamId
    """)
    suspend fun unsubscribeFromDream(dreamId: Int)
}
