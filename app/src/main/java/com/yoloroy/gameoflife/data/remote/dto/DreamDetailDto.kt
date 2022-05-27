package com.yoloroy.gameoflife.data.remote.dto

import com.yoloroy.gameoflife.domain.model.data.DreamDetail

data class DreamDetailDto(
    val challenges: List<ChallengeDto>,
    val description: String,
    val id: String,
    val name: String,
    val tags: List<String>
) {
    fun toModel() = DreamDetail(
        id = id,
        name = name,
        description = description,
        challenges = challenges.map { it.toModel() },
        tags = tags
    )
}
