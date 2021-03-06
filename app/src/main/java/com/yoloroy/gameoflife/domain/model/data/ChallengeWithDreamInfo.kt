package com.yoloroy.gameoflife.domain.model.data

data class ChallengeWithDreamInfo(
    val id: String,
    val name: String,
    val description: String,
    val no: Int,
    val skills: List<Skill> = emptyList(),
    val dreamId: String,
    val dreamName: String
) {
    constructor(challenge: Challenge, dream: Dream) : this(
        challenge.id,
        challenge.name,
        challenge.description,
        challenge.no,
        challenge.skills,
        dream.id,
        dream.name
    )
}
