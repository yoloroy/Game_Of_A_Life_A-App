package com.yoloroy.gameoflife.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class ProfileFull(
    @Embedded
    val profile: Profile,

    @Relation(
        parentColumn = "profile_id",
        entityColumn = "skill_id",
        associateBy = Junction(ProfileSkillCrossRef::class)
    )
    val skills: List<Skill>,

    @Relation(
        parentColumn = "profile_id",
        entityColumn = "profile_id"
    )
    val dreamInfos: List<DreamInfoWithProgress>
)
