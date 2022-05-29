package com.yoloroy.gameoflife.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["challenge_id", "skill_id"])
data class ChallengeSkillCrossRef(
    @NonNull
    @ColumnInfo(name = "challenge_id")
    val challengeId: Int,

    @NonNull
    @ColumnInfo(name = "skill_id")
    val skillId: Int
)
