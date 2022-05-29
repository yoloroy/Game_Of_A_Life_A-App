package com.yoloroy.gameoflife.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ChallengeFullWithDream(
    @Embedded
    val challengeFull: ChallengeFull,

    @Relation(
        parentColumn = "dream_id",
        entityColumn = "dream_id"
    )
    val dream: Dream
)
