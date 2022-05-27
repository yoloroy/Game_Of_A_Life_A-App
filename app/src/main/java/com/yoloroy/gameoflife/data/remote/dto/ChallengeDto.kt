package com.yoloroy.gameoflife.data.remote.dto

import com.yoloroy.gameoflife.domain.model.data.Challenge
import kotlinx.serialization.Serializable

@Serializable
data class ChallengeDto(
    val description: String,
    val id: String,
    val name: String,
    val no: Int
) {
    fun toModel() = Challenge(
        id = id,
        no = no,
        name = name,
        description = description
    )
}
