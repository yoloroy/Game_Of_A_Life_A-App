package com.yoloroy.gameoflife.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import androidx.room.Transaction
import com.yoloroy.gameoflife.data.local.entity.Profile
import com.yoloroy.gameoflife.data.local.entity.ProfileFull
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert(onConflict = IGNORE)
    suspend fun insertProfile(profile: Profile = Profile.Default.profile)

    @Query("SELECT * FROM profile LIMIT 1")
    fun getProfile(): Flow<Profile>

    @Transaction
    @Query("SELECT * FROM profile LIMIT 1")
    fun getProfileFull(): Flow<ProfileFull>

    @Query("""
        UPDATE profile
        SET
            level = ${Profile.Default.level},
            exp = ${Profile.Default.exp},
            max_exp = ${Profile.Default.maxExp}
        WHERE
            profile_id = ${Profile.Default.localProfileId}
    """)
    fun resetProfileStats()

    @Query("""
        DELETE FROM dream_progress
        WHERE profile_id = ${Profile.Default.localProfileId}
    """)
    fun resetProfileDreams()

    @Query("""
        UPDATE profile
        SET name = :name
        WHERE profile_id = ${Profile.Default.localProfileId}
    """)
    fun updateName(name: String)


}
