package com.yoloroy.gameoflife.domain.model

data class Profile(
    val imageUrl: String? = null,
    val name: String,
    val level: Int = 0,
    val exp: Int = 0,
    val maxExp: Int,
    val skills: List<Skill> = emptyList(),
    val fulfilledDreams: List<Dream> = emptyList()
)
