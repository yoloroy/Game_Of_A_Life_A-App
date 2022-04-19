package com.yoloroy.gameoflife.domain.model

data class Challenge(
    val id: String,
    val name: String,
    val description: String,
    val no: Int,
    val skills: List<Skill> = emptyList()
)
