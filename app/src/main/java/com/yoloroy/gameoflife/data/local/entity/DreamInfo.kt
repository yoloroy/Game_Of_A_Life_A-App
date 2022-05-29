package com.yoloroy.gameoflife.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class DreamInfo(
    @Embedded
    val dream: Dream,

    @Relation(
        parentColumn = "dream_id",
        entityColumn = "dream_id"
    )
    val tags: List<Tag>
)
