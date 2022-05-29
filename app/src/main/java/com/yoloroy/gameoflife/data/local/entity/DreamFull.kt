package com.yoloroy.gameoflife.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DreamFull(
    @Embedded
    val dreamInfo: DreamInfo,

    @Relation(
        parentColumn = "dream_id",
        entityColumn = "dream_id"
    )
    val challenges: List<Challenge>
)
