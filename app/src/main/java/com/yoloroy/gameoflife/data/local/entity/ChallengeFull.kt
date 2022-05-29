package com.yoloroy.gameoflife.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ChallengeFull(
    @Embedded
    val challenge: Challenge,

    @Relation(
        parentColumn = "challenge_id",
        entityColumn = "skill_id",
        associateBy = Junction(ChallengeSkillCrossRef::class)
    )
    val skills: List<Skill>
)
