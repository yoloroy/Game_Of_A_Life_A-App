package com.yoloroy.gameoflife.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey
    @ColumnInfo(name = "dream_id")
    val dreamId: Int,

    @NonNull
    @ColumnInfo(name = "name")
    val name: String
)
