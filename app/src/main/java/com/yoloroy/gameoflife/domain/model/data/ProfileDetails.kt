package com.yoloroy.gameoflife.domain.model.data

data class ProfileDetails(
    val name: String,
    val level: Int,
    val exp: Int,
    val maxExp: Int,
    val imageUrl: String? = null,
    val skills: List<Skill> = emptyList(),
    val fulfilledDreams: List<Dream> = emptyList(),
    val ongoingDreams: List<Dream> = emptyList()
)
