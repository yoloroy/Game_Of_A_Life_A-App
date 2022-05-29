package com.yoloroy.gameoflife.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["profile_id", "skill_id"])
data class ProfileSkillCrossRef(
    @NonNull
    @ColumnInfo(name = "profile_id")
    val profileId: Int,

    @NonNull
    @ColumnInfo(name = "skill_id")
    val skillId: Int
)
