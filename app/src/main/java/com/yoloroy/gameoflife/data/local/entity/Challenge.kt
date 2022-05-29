package com.yoloroy.gameoflife.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Challenge(
    @PrimaryKey
    @ColumnInfo(name = "challenge_id")
    val challengeId: Int,

    @NonNull
    @ColumnInfo(name = "dream_id")
    val dreamId: Int,

    @NonNull
    @ColumnInfo(name = "name")
    val name: String,

    @NonNull
    @ColumnInfo(name = "description")
    val description: String,

    @NonNull
    @ColumnInfo(name = "no")
    val no: Int
)
