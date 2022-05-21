package com.yoloroy.gameoflife.domain.model.data

data class Challenge(
    val id: String,
    val name: String,
    val description: String,
    val no: Int,
    val skills: List<Skill> = emptyList()
)

fun Challenge.withDreamInfo(dream: Dream) = ChallengeWithDreamInfo(this, dream)

fun Challenge.withDreamInfo(dream: DreamDetail) = withDreamInfo(Dream(dream))
