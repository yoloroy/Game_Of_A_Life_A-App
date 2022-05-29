package com.yoloroy.gameoflife.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "dream_progress", primaryKeys = ["profile_id", "dream_id"])
data class DreamProgress(
    @NonNull
    @ColumnInfo(name = "profile_id")
    val profileId: Int,

    @NonNull
    @ColumnInfo(name = "dream_id")
    val dreamId: Int,

    @NonNull
    @ColumnInfo(name = "progress", defaultValue = "0")
    val progress: Int
)
